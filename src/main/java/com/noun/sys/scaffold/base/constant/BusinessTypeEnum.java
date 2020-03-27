package com.noun.sys.scaffold.base.constant;

import org.apache.commons.lang3.StringUtils;

/**
 * @author:gaoxu
 * @create:2020-03-26 13:38
 **/
public enum BusinessTypeEnum implements Valuable, Labeled {
    GOODS(1, "商品业务"),
    ORDER(2, "订单业务"),
    STOCK(3, "库存业务");

    private int value;
    private String label;

    BusinessTypeEnum(int value, String label) {
        this.value = value;
        this.label = label;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getLabel() {
        return label;
    }

    public static BusinessTypeEnum getEnumByName(String enumName) {
        if (StringUtils.isEmpty(enumName)) {
            return null;
        }
        for (BusinessTypeEnum each : BusinessTypeEnum.values()) {
            if (enumName.equals(each.name())) {
                return each;
            }
        }
        return null;
    }
}
