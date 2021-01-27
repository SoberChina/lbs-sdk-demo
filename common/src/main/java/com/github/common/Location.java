package com.github.common;

/**
 * @author liweigao
 * @date 2021/1/25 下午1:05
 */
public class Location {

    /**
     * 维度
     */
    private String lat;

    /**
     * 精度
     */
    private String lng;

    public Location(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return this.lat + "," + this.lng;
    }
}
