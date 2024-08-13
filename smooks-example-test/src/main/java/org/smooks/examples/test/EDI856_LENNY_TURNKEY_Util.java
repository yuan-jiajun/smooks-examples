/* Copyright (c) 2022, TD SYNNEX Corporation. All rights reserved */

package org.smooks.examples.test;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.smooks.examples.test.transform.MeasureTransformUtil;

import java.util.*;


/**
 * @ Author     ：Jason.Yuan
 * @ Date       ：Created in 12:02 PM 8/8/2024
 * @ Description：
 */
public class EDI856_LENNY_TURNKEY_Util {
    public static Map<String, Object> processDetails(List<Map<Object, Object>> details, Map<Object, Object> headerSpecPathValueMapping) {
        Map result = new LinkedHashMap();
        List units = new ArrayList<>();
        List lines = new ArrayList<>();
        result.put("handlingUnitDetails", units);
        result.put("purchaseOrderLines", lines);

        if (CollectionUtils.isEmpty(details)) {
            return result;
        }

        Integer levelNumber = 3;
        String targetWeightUnit = "Kg";
        for (Map detail : details) {

            Map cartonUnit = new LinkedHashMap();
            units.add(cartonUnit);

            String palletId = (String) detail.get("palletId");
            String cartonNo = (String) detail.get("cartonNo");
            Object cartonGrossWeight = detail.get("cartonGrossWeight");
            Object cartonWeightUnit = detail.get("cartonWeightUnit");

            boolean cartonUnderPallet = StringUtils.isNotBlank(palletId);
            Integer cartonParentHandleUnitId = null;
            if (cartonUnderPallet) {
                Boolean hasRecord = false;
                for (Object handlingUnitDetail : units) {
                    Map each = (Map) handlingUnitDetail;
                    if (Objects.equals(each.get("handlingUnitId"), palletId)) {
                        hasRecord = true;
                        cartonParentHandleUnitId = (Integer) each.get("handlingUnitLevel");
                    }
                }
                if (!hasRecord) {
                    Map palletUnit = new LinkedHashMap();
                    units.add(palletUnit);
                    palletUnit.put("handlingUnitLevel", levelNumber++);
                    palletUnit.put("parentHandlingUnitLevel", 2);
                    palletUnit.put("handlingUnitId", palletId);
                    palletUnit.put("handlingUnitType", "Pallet");

                    Map palletGrossWeight = new LinkedHashMap();
                    Map palletGrossWeightValue = new LinkedHashMap();
                    palletGrossWeight.put("value", palletGrossWeightValue);
                    palletGrossWeight.put("unitOfMeasure", targetWeightUnit);
                    palletGrossWeightValue.put("units", "0");
                    palletUnit.put("grossWeight", palletGrossWeight);
                }
            }

            cartonUnit.put("cartonUnitLevel", levelNumber++);
            cartonUnit.put("handlingUnitId", cartonNo);
            cartonUnit.put("handlingUnitType", "Carton");
            cartonUnit.put("parentHandlingUnitLevel", cartonParentHandleUnitId != null ? cartonParentHandleUnitId : levelNumber - 1);
            cartonUnit.put("parentHandlingUnitId", palletId);

            Map grossWeight = new LinkedHashMap();
            Map grossWeightValue = new LinkedHashMap();
            grossWeight.put("value", grossWeightValue);
            grossWeight.put("unitOfMeasure", targetWeightUnit);
            grossWeightValue.put("units", MeasureTransformUtil.measureUnitTransform(cartonWeightUnit, targetWeightUnit, cartonGrossWeight));
            cartonUnit.put("grossWeight", grossWeight);

            List items = (List) detail.get("items");

            if (CollectionUtils.isEmpty(items)) {
                continue;
            }

            Integer cartonHandleUnitLevel = levelNumber - 1;
            for (Object eachItem : items) {
                Map item = (Map) eachItem;

                Map line = null;
                for (Object each : lines) {
                    Map hasRecordLine = (Map) each;
                    if (line == null
                            &&
                            Objects.equals(hasRecordLine.get("purchaseOrderNumber"),
                                    headerSpecPathValueMapping.get("endUserPo"))
                            &&
                            Objects.equals(hasRecordLine.get("purchaseOrderLineNumber"), item.get("custPoLineNo"))
                            &&
                            Objects.equals(hasRecordLine.get("gpn"), item.get("custPartNo"))
                            &&
                            Objects.equals(hasRecordLine.get("manufacturerPartNumber"), item.get("mfgPartNo"))) {
                        line = hasRecordLine;
                    }
                }
                if (line == null) {
                    line = new LinkedHashMap();
                    lines.add(line);
                    List shipmentDetails = new ArrayList();
                    line.put("shipmentDetails", shipmentDetails);
                }

                line.put("purchaseOrderNumber", headerSpecPathValueMapping.get("endUserPo"));
                line.put("purchaseOrderLineNumber", item.get("custPoLineNo"));
                line.put("gpn", item.get("custPartNo"));
                line.put("manufacturerPartNumber", item.get("mfgPartNo"));

                List shipmentDetails = (List) line.get("shipmentDetails");
                Map shipmentDetail = new LinkedHashMap();
                shipmentDetails.add(shipmentDetail);

                shipmentDetail.put("handlingUnitId", detail.get("cartonNo"));
                shipmentDetail.put("regionCode", item.get("originalCountry"));

                Map shippedQuantity = new LinkedHashMap();
                Map shippedQuantityValue = new LinkedHashMap();
                shippedQuantity.put("value", shippedQuantityValue);
                shippedQuantityValue.put("units", item.get("cartonItemQty"));
                String unitOfMeasure = "EA";
                shippedQuantity.put("unitOfMeasure", unitOfMeasure);
                shipmentDetail.put("shippedQuantity", shippedQuantity);


                shipmentDetail.put("handlingUnitLevel", cartonHandleUnitLevel);
                shipmentDetail.put("itemLevelNumber", levelNumber++);
                System.out.println();
            }
        }
        return result;
    }


}

