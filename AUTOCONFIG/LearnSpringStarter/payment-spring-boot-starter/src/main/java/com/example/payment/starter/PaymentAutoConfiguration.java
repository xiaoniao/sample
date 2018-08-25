package com.example.payment.starter;

import com.example.payment.properties.PaymentExtraProperties;
import com.example.payment.service.PayService;
import com.example.payment.service.impl.PayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liuzz on 2018/04/02
 */
@Configuration
@ConditionalOnClass(PayService.class)
@EnableConfigurationProperties(PaymentAutoProperties.class)
public class PaymentAutoConfiguration {

    @Autowired
    private PaymentAutoProperties paymentAutoProperties;

    /**
     * 获取自定义的支付参数
     * @return
     */
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "pay", value = "enabled", havingValue = "true")
    @Bean
    public PaymentExtraProperties paymentExtraProperties() {
        return new PaymentExtraProperties(paymentAutoProperties.getAddress(), paymentAutoProperties.getPort());
    }

    /**
     * 实例化支付对象
     *
     * @return
     */
    @Bean
    public PayService payService() {
        return new PayServiceImpl();
    }
}
