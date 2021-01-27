package com.github.common;

/**
 * @author liweigao
 * @date 2021/1/25 上午11:56
 */
public interface SdkLbsService {

    /**
     * 逆地理解析
     *
     * @param params 参数
     * @return t entity
     */
    <T> T geoCoder(GeoCoderBaseParams params);
}
