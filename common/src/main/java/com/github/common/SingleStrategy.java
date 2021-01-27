package com.github.common;

import java.util.Objects;

/**
 * @author liweigao
 * @date 2021/1/25 下午2:50
 */
public class SingleStrategy implements Strategy {

    private SecretKey secretKey;


    public SingleStrategy(SecretKey secretKey) {
        if (Objects.isNull(secretKey)) {
            throw new RuntimeException("secret key is not null!");
        }
        this.secretKey = secretKey;
    }

    @Override
    public SecretKey SecretKey() {
        return secretKey;
    }
}
