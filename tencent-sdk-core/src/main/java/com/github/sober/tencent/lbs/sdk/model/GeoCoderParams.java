package com.github.sober.tencent.lbs.sdk.model;

import com.github.common.GeoCoderBaseParams;

/**
 * @author liweigao
 * @date 2021/1/25 下午3:44
 */
public class GeoCoderParams extends GeoCoderBaseParams {


    private int poi;

    private String output;

    public int getPoi() {
        return poi;
    }

    public void setPoi(int poi) {
        this.poi = poi;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}

