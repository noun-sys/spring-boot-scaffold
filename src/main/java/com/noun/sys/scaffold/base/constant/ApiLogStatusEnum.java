package com.noun.sys.scaffold.base.constant;

/**
 * @author:gaoxu
 * @create:2020-03-26 15:24
 **/
public enum ApiLogStatusEnum implements Valuable, Labeled {
    UNCOMMITTED(1, "未处理"),
    FINISHED(2, "处理完"),
    ERROR(3, "无法解析");

    private int value;
    private String label;

    ApiLogStatusEnum(int value, String label) {
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
}
