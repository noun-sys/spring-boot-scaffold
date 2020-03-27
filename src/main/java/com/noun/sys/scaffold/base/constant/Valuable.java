package com.noun.sys.scaffold.base.constant;

/**
 * @author:gaoxu
 * @create:2020-03-25 9:56
 **/
public interface Valuable {
    /**
     * 获取枚举自定义值
     * @return 枚举自定义值
     */
    int getValue();


    /**
     * 判断枚举是否与当前值匹配
     * @param value
     * @return 是否匹配
     */
    default boolean match(Integer value) {
        return value != null && getValue() == value.intValue();
    }
}
