package com.noun.sys.scaffold.base.constant;

import org.apache.commons.lang3.StringUtils;

/**
 * @author:gaoxu
 * @create:2020-03-25 9:56
 **/
public enum SystemEnvEnum {
    SIT,
    PRE,
    PRO;

    public static SystemEnvEnum fromString(String value) {
        if (StringUtils.isNotEmpty(value)) {
            for (SystemEnvEnum env : SystemEnvEnum.values()) {
                if (value.equalsIgnoreCase(env.name())) {
                    return env;
                }
            }
        }
        return null;
    }
}
