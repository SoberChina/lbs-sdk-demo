package com.github.sober.tencent.lbs.sdk.model;

/**
 *
 * @see <a>https://lbs.qq.com/service/webService/webServiceGuide/webServiceGcoder</a>
 * @author liweigao
 * @date 2021/1/25 下午2:54
 */
public class GeoCoderDto {


   private String address;

   /**
    * 其他属性忽略... 具体需要可自行添加
    */

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }
}
