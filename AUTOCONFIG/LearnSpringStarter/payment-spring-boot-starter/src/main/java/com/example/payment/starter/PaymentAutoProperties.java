package com.example.payment.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by liuzz on 2018/04/02
 */
@ConfigurationProperties(prefix = "pay")
public class PaymentAutoProperties {

    private String address;

    private Integer port;

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
