在Spring Boot中使用Docker


## 正常启动项目：

mvn clean package

java -jar ./target/springboot-0.0.1-SNAPSHOT.jar

## 容器化

1. 编写Dockerfile文件

----
FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
----

2. 使用Maven plugin 构建Docker镜像

----
mvn clean install dockerfile:build

----

运行
docker run -p 8899:8899 -t springio/springboot:latest

查查运行中的进程
docker ps

停止
docker stop 64030421234c

启动
docker start 64030421234c

添加参数启动
docker run -e "SPRING_PROFILES_ACTIVE=dev" -p 8899:8899 -t springio/springboot:latest




Debugging the application in a Docker container ？











