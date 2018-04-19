package com.example.value;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;


@SpringBootApplication
public class SpringAnnotaionLearnApplication implements CommandLineRunner {

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

    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(SpringAnnotaionLearnApplication.class, args);
    }

    @Bean
    public StandardEnvironment standardEnvironment(StandardEnvironment standardEnvironment) {
        System.out.println("********************************************************************");
        System.out.println(standardEnvironment);
        standardEnvironment.getConversionService().addConverter(new DiyGenericConverter());
        return standardEnvironment;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("*****************************run***************************************");
        System.out.println(environment);
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

        Goods goods = environment.getProperty("goods", Goods.class);
        System.out.println(goods);
    }
}
