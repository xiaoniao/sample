package com.example.value;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.AbstractEnvironment;

@ImportResource("classpath:beans.xml")
@SpringBootApplication
public class DiyApplication implements CommandLineRunner {

    @Value("${love}")
    private String love;

    @Value("${num}")
    private Integer num;

    @Value("${price}")
    private double price;

    @Value("${profile}")
    private String profile;

    @Value("${common.name}")
    private String commonName;

    @Value("${common.name2}")
    private String commonName2;

    @Value("${goods}")
    private Goods goods;

    @Autowired
    private AbstractEnvironment environment;

    public static void main(String[] args) {
        SpringApplication.run(DiyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(goods);


        System.out.println("*****************************run***************************************");
        System.out.println("profile : " + profile);
        System.out.println("common.name : " + commonName);
        System.out.println("common.name2 : " + commonName2);

        System.out.println(love);
        System.out.println(environment.resolvePlaceholders("${love}"));
        System.out.println(environment.resolveRequiredPlaceholders("${love}"));
        System.out.println(environment.getProperty("love"));

        System.out.println(num);
        System.out.println(price);

        List<String> list = environment.getProperty("list", List.class);
        System.out.println(list);

//        System.out.println(environment.getConversionService());
//        System.out.println(environment.getProperty("goods"));
//        Goods goods = environment.getProperty("goods", Goods.class);
//        System.out.println(goods);
    }
}
