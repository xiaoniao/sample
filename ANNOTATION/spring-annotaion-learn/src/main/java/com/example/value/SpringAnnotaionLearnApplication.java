package com.example.value;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;


@SpringBootApplication
public class SpringAnnotaionLearnApplication implements CommandLineRunner {

    @Value("${love}")
    private String love;

    @Value("${num}")
    private Integer num;

    @Value("${price}")
    private double price;

    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(SpringAnnotaionLearnApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println(love);
        System.out.println(environment.resolvePlaceholders("${love}"));
        System.out.println(environment.resolveRequiredPlaceholders("${love}"));
        System.out.println(environment.getProperty("love"));

        System.out.println(num);
        System.out.println(price);

        List<String> list = environment.getProperty("list", List.class);
        System.out.println(list);

        /*Goods goods = environment.getProperty("goods", Goods.class);
        System.out.println(goods);*/

        // Circular placeholder reference 'placeholder' in property definitions
        // System.out.println(environment.resolvePlaceholders("${placeholder}"));
    }
}
