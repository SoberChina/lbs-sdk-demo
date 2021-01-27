package com.github.sober.tencent.lbs.sdk.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.common.*;
import com.github.sober.tencent.lbs.sdk.config.TencentLbsProperties;
import com.github.sober.tencent.lbs.sdk.model.BaseResult;
import com.github.sober.tencent.lbs.sdk.model.GeoCoderDto;
import com.github.sober.tencent.lbs.sdk.model.GeoCoderParams;
import com.github.sober.tencent.lbs.sdk.util.HttpClientTool;
import com.sun.istack.internal.NotNull;

import java.util.*;

/**
 * @author liweigao
 * @date 2021/1/25 下午2:46
 */
public class TencentLbsServiceImpl extends AbstractSdkLbsService {


    private Strategy strategy;

    public TencentLbsServiceImpl(TencentLbsProperties tencentLbsProperties) {

        TencentLbsProperties.SecretKey secretKey;
        List<TencentLbsProperties.SecretKey> secretKeys = tencentLbsProperties.getPool();
        if (Objects.isNull(secretKey = tencentLbsProperties.getSecretKey()) && (Objects.isNull(secretKeys) || secretKeys.size() < 1)) {
            throw new RuntimeException("tencent lbs config is null");
        }
        if (Objects.nonNull(secretKeys) && secretKeys.size() > 0) {

            List<SecretKey> skeys = new ArrayList<>();
            for (TencentLbsProperties.SecretKey skey : secretKeys) {
                skeys.add(new SecretKey(skey.getKey()));
            }
            this.strategy = new PollingStrategy(skeys);
        } else {
            strategy = new SingleStrategy(new SecretKey(secretKey.getKey()));
        }

    }

    public TencentLbsServiceImpl(TencentLbsProperties tencentLbsProperties, @NotNull StrategyType strategyType) {

        List<TencentLbsProperties.SecretKey> secretKeys;
        switch (strategyType) {
            case SINGLE:

                TencentLbsProperties.SecretKey secretKey;
                if (Objects.isNull(secretKey = tencentLbsProperties.getSecretKey())) {
                    throw new RuntimeException("tencent lbs config is null");
                }
                this.strategy = new SingleStrategy(new SecretKey(secretKey.getKey()));
                break;
            case POLLING:
                secretKeys = tencentLbsProperties.getPool();
                if (Objects.isNull(secretKeys) || secretKeys.size() < 1) {
                    throw new RuntimeException("tencent lbs poll config is null");
                }
                List<SecretKey> skeys = new ArrayList<>();
                for (TencentLbsProperties.SecretKey skey : secretKeys) {
                    skeys.add(new SecretKey(skey.getKey()));
                }
                this.strategy = new PollingStrategy(skeys);
                break;
            case WEIGHT:
                secretKeys = tencentLbsProperties.getPool();
                if (Objects.isNull(secretKeys) || secretKeys.size() < 1) {
                    throw new RuntimeException("tencent lbs poll config is null");
                }
                Map<SecretKey, Integer> secretKeyIntegerMap = new HashMap<>();
                for (TencentLbsProperties.SecretKey skey : secretKeys) {
                    secretKeyIntegerMap.put(new SecretKey(skey.getKey()), skey.getWeight());
                }
                this.strategy = new WeightStrategy(secretKeyIntegerMap);
                break;
            default:
                throw new RuntimeException("strategy type is no realization!");
        }

    }

    @Override
    public Strategy strategy() {
        return strategy;
    }

    @Override
    public BaseResult geoCoder(GeoCoderBaseParams params) {

        GeoCoderParams geoCoderParams = (GeoCoderParams) params;


        StringBuilder urlSb =
                new StringBuilder("https://apis.map.qq.com/ws/geocoder/v1/?location=").append(geoCoderParams.getLocation().toString());
        urlSb.append("&get_poi=").append(geoCoderParams.getPoi());
        if (Objects.nonNull(geoCoderParams.getOutput())) {
            urlSb.append("&output=").append(geoCoderParams.getOutput());
        }
        urlSb.append("&key=").append(getSecretKey().getKey());


        String response = HttpClientTool.doGet(urlSb.toString(), null);

        if (null != response) {
            return JSON.parseObject(response, new TypeReference<BaseResult<GeoCoderDto>>() {
            });
        }
        return null;

        //        HttpGet httpGet = new HttpGet(urlSb.toString());
        //
        //
        //        RequestConfig requestConfig =  RequestConfig.custom().
        //                setConnectionRequestTimeout(1000).setSocketTimeout(1000).setConnectTimeout(1000).build();
        //
        //        httpGet.setConfig(requestConfig);
        //        CloseableHttpClient httpClient = createDefault();

        //
        //        try (CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet)) {
        //            HttpEntity httpEntity = closeableHttpResponse.getEntity();
        //            int statusCode;
        //            if ((statusCode = closeableHttpResponse.getStatusLine().getStatusCode()) >= 300) {
        //                throw new RuntimeException("api is error status:" + statusCode);
        //            }
        //            String response = httpEntity == null ? null : EntityUtils.toString(httpEntity, Consts.UTF_8);
        //            if (null != response) {
        //                return JSON.parseObject(response, new TypeReference<BaseResult<GeoCoderDto>>() {
        //                });
        //            }
        //            return null;
        //        } catch (IOException e) {
        //            throw new RuntimeException("http connection is error" + e.getMessage());
        //        }
    }

    public static void main(String[] args) {


        TencentLbsProperties properties = new TencentLbsProperties();
        List<TencentLbsProperties.SecretKey> keys = new ArrayList<>();
        keys.add(new TencentLbsProperties.SecretKey("xxxx"));
        keys.add(new TencentLbsProperties.SecretKey("xxxx"));
        properties.setPool(keys);
        TencentLbsServiceImpl tencentLbsService = new TencentLbsServiceImpl(properties);

        GeoCoderParams geoCoderParams = new GeoCoderParams();

        geoCoderParams.setLocation(new Location("40.584757", "115.872803"));

        for (int i = 0; i < 10000; i++) {

            try{
                tencentLbsService.geoCoder(geoCoderParams);
            } catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(i);
        }

    }

}
