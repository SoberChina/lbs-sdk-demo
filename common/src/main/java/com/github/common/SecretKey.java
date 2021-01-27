package com.github.common;

import java.util.Objects;

/**
 * @author liweigao
 * @date 2021/1/25 下午1:32
 */
public class SecretKey {

    /**
     * 开发秘钥key
     */
    private String key;

    public String getKey() {
        return key;
    }

    @Override
    public String toString() {
        return "SecretKey{" +
                "key='" + key + '\'' +
                '}';
    }

    public void setKey(String key) {
        this.key = key;
    }

    public SecretKey(String key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        SecretKey secretKey = (SecretKey) o;
        return Objects.equals(key, secretKey.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
