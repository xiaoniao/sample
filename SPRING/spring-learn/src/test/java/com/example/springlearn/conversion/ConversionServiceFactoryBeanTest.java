package com.example.springlearn.conversion;

import com.example.springlearn.SpringLearnApplication;
import com.example.springlearn.model.Product;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * ConversionServiceFactoryBean 添加自定义转换器
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringLearnApplication.class)
public class ConversionServiceFactoryBeanTest {

    @Autowired
    private AbstractEnvironment environment;

    @Value("${product}")
    private String productStr;

//    @Value("${product}")
//    private Product product;

    @Test
    public void printConversionService() {
        ConversionService conversionService = environment.getConversionService();
        System.out.println(conversionService);
    }

    @Test
    public void showProperties() {
        ConversionService conversionService = environment.getConversionService();
        Product product = conversionService.convert(productStr, Product.class);
        System.out.println(new Gson().toJson(product));
    }
}
