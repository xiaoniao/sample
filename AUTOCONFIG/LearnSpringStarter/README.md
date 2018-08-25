
## 概述
payment:第三方支付开发包。

payment-spring-boot-starter:自动依赖注入payment项目,实例化payment,并配置自定义property参数。

spring-boot-sample:实际项目，在项目中引入payment jar包，通过payment-spring-boot-starter自动实例化 payment 组件。


## 技术

1.自动配置
2.条件注解


## 自动配置 指定自动配置类(@Configuration)
resources/META-INF/spring.factories
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
com.example.payment.starter.PaymentAutoConfiguration

## 条件注解
@ConditionalOnClass(PayService.class)
@ConditionalOnProperty(prefix = "pay", value = "enabled", havingValue = "true")
@ConditionalOnMissingBean
等等

## 自定义属性配置，实例化属性配置
@ConfigurationProperties(prefix = "pay")
@EnableConfigurationProperties(PaymentAutoProperties.class)

## TODO
spring-boot-configuration-processor


## 依赖
[INFO] com.example:payment-spring-boot-starter:jar:0.0.1-SNAPSHOT
[INFO] +- org.springframework.boot:spring-boot-autoconfigure:jar:2.0.0.RELEASE:compile
[INFO] |  \- org.springframework.boot:spring-boot:jar:2.0.0.RELEASE:compile
[INFO] |     +- org.springframework:spring-core:jar:5.0.4.RELEASE:compile
[INFO] |     |  \- org.springframework:spring-jcl:jar:5.0.4.RELEASE:compile
[INFO] |     \- org.springframework:spring-context:jar:5.0.4.RELEASE:compile
[INFO] |        +- org.springframework:spring-aop:jar:5.0.4.RELEASE:compile
[INFO] |        +- org.springframework:spring-beans:jar:5.0.4.RELEASE:compile
[INFO] |        \- org.springframework:spring-expression:jar:5.0.4.RELEASE:compile
[INFO] \- com.example:payment:jar:0.0.1-SNAPSHOT:compile (optional) 
[INFO]    \- org.springframework.boot:spring-boot-starter:jar:2.0.0.RELEASE:compile
[INFO]       +- org.springframework.boot:spring-boot-starter-logging:jar:2.0.0.RELEASE:compile
[INFO]       |  +- ch.qos.logback:logback-classic:jar:1.2.3:compile
[INFO]       |  |  +- ch.qos.logback:logback-core:jar:1.2.3:compile
[INFO]       |  |  \- org.slf4j:slf4j-api:jar:1.7.25:compile
[INFO]       |  +- org.apache.logging.log4j:log4j-to-slf4j:jar:2.10.0:compile
[INFO]       |  |  \- org.apache.logging.log4j:log4j-api:jar:2.10.0:compile
[INFO]       |  \- org.slf4j:jul-to-slf4j:jar:1.7.25:compile
[INFO]       +- javax.annotation:javax.annotation-api:jar:1.3.2:compile
[INFO]       \- org.yaml:snakeyaml:jar:1.19:runtime
