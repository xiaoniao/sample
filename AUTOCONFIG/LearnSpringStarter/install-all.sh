#!/usr/bin/env bash

cd payment
mvn clean install -Dmaven.test.skip=true

cd ..
cd payment-spring-boot-starter
mvn clean install -Dmaven.test.skip=true

cd ..
cd spring-boot-sample
mvn clean install