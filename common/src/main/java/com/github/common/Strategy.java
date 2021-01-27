package com.github.common;

/**
 * 获取key策略
 *
 * @author liweigao
 * @date 2021/1/25 下午1:16
 */
public interface Strategy {

    /**
     * 获取秘钥key
     * @return
     */
     SecretKey SecretKey();

}
