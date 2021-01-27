package com.github.common;


import java.util.*;

/**
 * @author liweigao
 * @date 2021/1/25 下午1:45
 */
public class WeightStrategy implements Strategy {


    private Map<SecretKey, Integer> secretKeyIntegerMap = new LinkedHashMap<>();

    private int total;

    private Random random = new Random();

    public  WeightStrategy(Map<SecretKey, Integer> secretKeyIntegerMap) {

        secretKeyIntegerMap.forEach((k, v) -> {
            if (Objects.nonNull(k)) {
                if (Objects.nonNull(v) && v.intValue() < 0) {
                    v = 1;
                    secretKeyIntegerMap.put(k, v);
                    total += v;
                }
            }
        });
    }

    @Override
    public SecretKey SecretKey() {

        int w = random.nextInt(total) + 1;

        Iterator<Map.Entry<SecretKey, Integer>> entryIterator = secretKeyIntegerMap.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<SecretKey, Integer> entry = entryIterator.next();
            Integer v = entry.getValue();
            if (w <= v) {
                return entry.getKey();
            } else {
                w -= v;
            }
        }

        return null;
    }
}
