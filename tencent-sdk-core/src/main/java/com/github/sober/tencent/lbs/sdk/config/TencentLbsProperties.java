package com.github.sober.tencent.lbs.sdk.config;

import java.util.List;

/**
 * @author liweigao
 * @date 2021/1/25 下午2:37
 */
public class TencentLbsProperties {

    private SecretKey secretKey;

    private List<SecretKey> pool;


    public SecretKey getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    public List<SecretKey> getPool() {
        return pool;
    }

    public void setPool(List<SecretKey> pool) {
        this.pool = pool;
    }

    public TencentLbsProperties() {
    }

    public TencentLbsProperties(SecretKey secretKey, List<SecretKey> pool) {
        this.secretKey = secretKey;
        this.pool = pool;
    }

    public static class SecretKey {

        private String key;

        private Integer weight;

        public SecretKey(String key, Integer weight) {
            this.key = key;
            this.weight = weight;
        }

        public SecretKey(String key) {
            this.key = key;
        }

        public SecretKey() {
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }
    }

}
