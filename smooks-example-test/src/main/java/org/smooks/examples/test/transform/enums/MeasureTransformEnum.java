/* Copyright (c) 2022, TD SYNNEX Corporation. All rights reserved */

package org.smooks.examples.test.transform.enums;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.smooks.examples.test.transform.enums.MeasureUnitEnum.*;


/**
 * @ Author     ：Jason.Yuan
 * @ Date       ：Created in 5:22 PM 8/7/2024
 * @ Description：
 */
public enum MeasureTransformEnum {
    POUND_KILOGRAM(POUND, KILOGRAM, "0.45359237"),
    INCH_CM(INCH, CM, "2.54"),
    INCH_M(INCH, M, "0.0254"),
    ;


    private final MeasureUnitEnum source;
    private final MeasureUnitEnum target;
    private final BigDecimal baseFactor;


    public MeasureUnitEnum getSource() {
        return source;
    }

    public MeasureUnitEnum getTarget() {
        return target;
    }

    public BigDecimal getBaseFactor() {
        return baseFactor;
    }

    MeasureTransformEnum(MeasureUnitEnum source, MeasureUnitEnum target, String baseFactor) {
        this.source = source;
        this.target = target;
        this.baseFactor = new BigDecimal(baseFactor);
    }

    public static MeasureTransformEnum getMeasureTransformEnum(MeasureUnitEnum unit1, MeasureUnitEnum unit2) {
        if (unit1 == null || unit2 == null) {
            return null;
        }
        return Arrays.stream(MeasureTransformEnum.values())
                .filter(each -> (each.getSource() == unit1 && each.getTarget() == unit2)
                        || (each.getTarget() == unit1 && each.getSource() == unit2))
                .findFirst()
                .orElse(null);
    }
}



