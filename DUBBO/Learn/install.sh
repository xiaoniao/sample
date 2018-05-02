#!/usr/bin/env bash
cd learn-tourist-facade
mvn clean install -Dmaven.test.skip=true

cd ..

cd learn-tourist
mvn clean install -Dmaven.test.skip=true

cd ..

cd learn-gateway 
mvn clean install -Dmaven.test.skip=true

