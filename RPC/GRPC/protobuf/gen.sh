#!/usr/bin/env bash

protoc -I ./ --java_out=src/main/java ./proto/other.proto

protoc -I ./ --java_out=src/main/java  -I ./proto ./proto/first.proto

protoc -I ./ --java_out=src/main/java  -I ./proto ./proto/addressbook.proto


#生成model
#protoc --proto_path=./ --java_out=src/main/java ./proto/first.proto

#protoc --plugin=protoc-gen-grpc-java=/Users/xiezx/.m2/repository/io/grpc/protoc-gen-grpc-java/1.13.1/protoc-gen-grpc-java-1.13.1-osx-x86_64.exe -I ./ --grpc-java_out=src/main/java  -I ./proto ./proto/first.proto