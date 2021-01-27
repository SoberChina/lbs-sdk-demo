package com.github.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 轮询
 *
 * @author liweigao
 * @date 2021/1/25 下午1:22
 */
public class PollingStrategy implements Strategy {

    /**
     * 秘钥集合
     */
    private List<SecretKey> secretKeys = new ArrayList<>();

    /**
     * 位置
     */
    private int position = 0;

    public PollingStrategy(List<SecretKey> secretKeys) {
        if (null == secretKeys || secretKeys.size() < 1)
            throw new RuntimeException("secret key is not empty!");
        this.secretKeys = secretKeys;
    }

    /**
     * 如果当前位置小于初始化key的长度-1 下次获取直接从position+1位置开始
     * 反之从其实位置开始
     *
     * @return str
     */
    @Override
    public SecretKey SecretKey() {

        SecretKey secretKey = secretKeys.get(position);
        if (secretKeys.size() == 1) {
            return secretKey;
        }

        if (position < (secretKeys.size() - 1)) {
            position++;
        } else {
            position = 0;
        }
        return secretKey;
    }
}
