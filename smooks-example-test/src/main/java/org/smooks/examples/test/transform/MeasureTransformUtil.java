/* Copyright (c) 2022, TD SYNNEX Corporation. All rights reserved */

package org.smooks.examples.test.transform;

import lombok.extern.slf4j.Slf4j;
import org.smooks.examples.test.transform.enums.MeasureTransformEnum;
import org.smooks.examples.test.transform.enums.MeasureUnitEnum;

import java.math.BigDecimal;
import java.util.Optional;

import static java.math.RoundingMode.HALF_UP;

/**
 * @ Author     ：Jason.Yuan
 * @ Date       ：Created in 5:22 PM 8/7/2024
 * @ Description：
 */
@Slf4j
public class MeasureTransformUtil {

    public static final int DEFAULT_SCALE = 2;

    public static BigDecimal sourceMeasureValueToBigDecimal(Object sourceMeasureValue) {
        return sourceMeasureValueToBigDecimal(sourceMeasureValue, true);
    }

    public static BigDecimal sourceMeasureValueToBigDecimal(Object sourceMeasureValue, boolean nonNegative) {
        BigDecimal result = null;
        if (sourceMeasureValue == null) {
            return result;
        }
        if (sourceMeasureValue instanceof BigDecimal) {
            result = (BigDecimal) sourceMeasureValue;
        } else {
            try {
                result = new BigDecimal(sourceMeasureValue.toString());
            } catch (Exception e) {
                log.warn("sourceInput2BigDecimal exception, sourceMeasureValue:{}", sourceMeasureValue, e);
                return null;
            }
        }
        if (nonNegative && result.compareTo(BigDecimal.ZERO) < 0) {
            log.error("MeasureTransform sourceValue lessThan zero, unsupported transform,sourceMeasureValue:{}", sourceMeasureValue);
            return null;
        }
        return result;
    }

    public static String formatMeasureResultString(BigDecimal sourceMeasureValue) {
        return Optional.ofNullable(sourceMeasureValue).map(BigDecimal::toPlainString).orElse(null);
    }

    public static <T> BigDecimal executeTransform(T inputValue, Integer resultScale,
                                                  boolean transformDirect, MeasureTransformEnum measureTransformEnum) {
        if (inputValue == null || resultScale == null || measureTransformEnum == null) {
            return null;
        }
        return Optional.ofNullable(sourceMeasureValueToBigDecimal(inputValue))
                .map(result -> {
                    BigDecimal baseFactor = measureTransformEnum.getBaseFactor();

                    if (transformDirect) {
                        return result.multiply(baseFactor).setScale(resultScale, HALF_UP);
                    } else {
                        return result.divide(baseFactor, resultScale, HALF_UP);
                    }
                })
                .orElse(null);
    }

    public static <S, T, V> String measureUnitTransform(S sourceUnitCode, T targetUnitCode, V sourceMeasureValue) {
        return measureUnitTransform(sourceUnitCode, targetUnitCode, sourceMeasureValue, DEFAULT_SCALE);

    }

    /**
     * @param sourceUnitCode        sourceMeasureUnitCode, inch,kg,cm etc
     * @param targetUnitCode        targetMeasureUnitCode
     * @param sourceMeasureValueObj sourceMeasureUnitValue
     * @return targetMeasureUnitValueString
     */
    public static <S, T, V> String measureUnitTransform(S sourceUnitCode, T targetUnitCode, V sourceMeasureValueObj,
                                                        Integer resultScale) {
        if (sourceUnitCode == null || targetUnitCode == null || sourceMeasureValueObj == null) {
            return null;
        }
        BigDecimal sourceMeasureValue = sourceMeasureValueToBigDecimal(sourceMeasureValueObj);
        if (sourceMeasureValue == null) {
            return null;
        }
        MeasureUnitEnum sourceUnit = MeasureUnitEnum.getUnitByCodeIgnoreCase(sourceUnitCode.toString());
        MeasureUnitEnum targetUnit = MeasureUnitEnum.getUnitByCodeIgnoreCase(targetUnitCode.toString());
        if (sourceUnit == null || targetUnit == null) {
            log.error("unsupported measureUnitTransform, sourceUnitCode:{}, targetUnitCode:{}",
                    sourceUnitCode, targetUnitCode);
            return null;
        }
        if (sourceUnit == targetUnit) {
            return sourceMeasureValue.toString();
        }

        MeasureTransformEnum measureTransformEnum = MeasureTransformEnum.getMeasureTransformEnum(sourceUnit, targetUnit);
        if (measureTransformEnum == null) {
            log.error("unsupported measureUnitTransform, sourceUnitCode:{}, targetUnitCode:{}",
                    sourceUnitCode, targetUnitCode);
            return null;
        }

        return MeasureTransformUtil.formatMeasureResultString(
                executeTransform(sourceMeasureValue, resultScale, measureTransformEnum.getSource() == sourceUnit, measureTransformEnum));
    }

}

