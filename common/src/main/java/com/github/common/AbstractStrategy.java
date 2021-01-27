package com.github.common;

/**
 * @author liweigao
 * @date 2021/1/25 下午2:31
 */
public abstract class AbstractStrategy {

    /**
     * 策略
     *
     * @return str
     */
    public abstract Strategy strategy();

    /**
     * 获取秘钥
     *
     * @return secretKey
     */
    public SecretKey getSecretKey() {
        return strategy().SecretKey();
    }
}
