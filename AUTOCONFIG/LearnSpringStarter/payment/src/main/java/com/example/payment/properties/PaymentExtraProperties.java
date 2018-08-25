package com.example.payment.properties;

/**
 * Created by liuzhuang on 2018/8/23.
 */
public class PaymentExtraProperties {

    private String address;

    private Integer port;

    public PaymentExtraProperties(String address, Integer port) {
        this.address = address;
        this.port = port;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
