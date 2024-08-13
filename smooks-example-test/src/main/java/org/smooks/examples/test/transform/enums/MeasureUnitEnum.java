/* Copyright (c) 2022, TD SYNNEX Corporation. All rights reserved */

package org.smooks.examples.test.transform.enums;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @ Author     ：Jason.Yuan
 * @ Date       ：Created in 5:22 PM 8/7/2024
 * @ Description：
 */
public enum MeasureUnitEnum {
    POUND(Lists.newArrayList("lb")),
    KILOGRAM(Lists.newArrayList("kg")),


    INCH(Lists.newArrayList("inch")),
    CM(Lists.newArrayList("cm")),
    M(Lists.newArrayList("m")),
    ;

    private final List<String> codes;

    public List<String> getCodes() {
        return codes;
    }

    MeasureUnitEnum(List<String> codes) {
        this.codes = codes;
    }

    public static MeasureUnitEnum getUnitByCodeIgnoreCase(String code) {
        return Arrays.stream(MeasureUnitEnum.values())
                .filter(each -> each.getCodes().stream().anyMatch(enumCode -> StringUtils.equalsIgnoreCase(enumCode, code)))
                .findFirst()
                .orElse(null);
    }
}



