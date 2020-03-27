package com.noun.sys.scaffold.base.apiversion;

import java.lang.annotation.*;

/**
 * @Author gaoxu
 * @Date 2019-05-28 16:57
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiVersion {
    /**
     * 版本号
     * @return
     */
    int value() default 1;
}
