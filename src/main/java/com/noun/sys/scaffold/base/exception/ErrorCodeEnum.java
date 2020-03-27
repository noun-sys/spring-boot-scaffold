package com.noun.sys.scaffold.base.exception;

import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 *
 * @author gaoxu
 * @date 2019/07/04 18:54
 */
public enum ErrorCodeEnum {
    Exception("未知异常", -1),
    None("默认", 0),
    INVALID_PARAM("无效的参数", 100),
    PARAM_NULL_ERROR("参数为空", 101),
    BAD_REQUEST("输入参数错误", 400),
    UNAUTHORIZED_ERROR("用户未登录授权", 401),
    SERVER_COMMON_ERROR("服务器错误", 500);

    private String errorMessage;

    private int errorCode;

    private static final Map<Integer, ErrorCodeEnum> map = new HashMap<>();
    private static final ResourceBundleMessageSource messageSource;
    private static final String SEP = "_";

    static {
        messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");

        for (ErrorCodeEnum error : ErrorCodeEnum.values()) {
            map.put(error.getErrorCode(), error);
        }
    }

    ErrorCodeEnum(String errorMessage, int errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public static ErrorCodeEnum getErrorEnumByErrorCode(int errorCode) {
        if (map.containsKey(errorCode)) {
            return map.get(errorCode);
        }
        return ErrorCodeEnum.SERVER_COMMON_ERROR;
    }
}
