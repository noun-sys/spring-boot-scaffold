package com.noun.sys.scaffold.base.constant;

/**
 * @author:gaoxu
 * @create:2020-03-25 9:53
 **/
public class RedisKeyConstant {
    private final static String SEPARATOR = ":";
    private final static String PREFIX = "mp-suppler-paas" + SEPARATOR;

    /**
     * 获取验证限制次数缓存key
     * @param content 手机号或者Email
     * @return
     */
    public static String getValidateTimesLimitCacheKey(String content, String tenantCode) {
        return String.format("%sValidateTimesLimit_%s_%s", PREFIX, content, tenantCode);
    }

    /**
     * 获取接口频次限制缓存key
     * @param appName
     * @param methodName
     * @param uid
     * @return
     */
    public static String getInterfaceFrequencyCacheKey(String appName, String methodName, String uid) {
        StringBuilder cacheKey = new StringBuilder(200);
        cacheKey.append("InterfaceFrequency").append(SEPARATOR);
        cacheKey.append(appName).append(SEPARATOR);
        cacheKey.append(methodName).append(SEPARATOR);
        cacheKey.append(uid);
        return PREFIX + cacheKey.toString();
    }

    public static String getUserLoginFailedCountKey(Long userId) {
        StringBuffer cacheKey = new StringBuffer(PREFIX + "user:login:failed:count:");
        cacheKey.append("userId").append(SEPARATOR).append(userId);
        return cacheKey.toString();
    }
}
