FROM registry.cn-hangzhou.aliyuncs.com/aliware2018/debian-jdk8

ARG MAVEN_VERSION=3.5.3

ENV MAVEN_HOME=/opt/maven
ENV PATH=${PATH}:${MAVEN_HOME}/bin

RUN set -ex \
 && apt-get update \
 # Install Maven
 && curl -L -o /tmp/maven.tar.gz \
         http://mirrors.shu.edu.cn/apache/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
 && CHECKSUM=$(curl https://www.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz.sha1) \
 && echo "${CHECKSUM}  /tmp/maven.tar.gz" | sha1sum -c \
 && gpg --keyserver pgp.mit.edu --recv-key B620D787 \
 && curl -o /tmp/maven.tar.gz.asc \
         https://www.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz.asc \
 && gpg --verify /tmp/maven.tar.gz.asc /tmp/maven.tar.gz  \
 && mkdir ${MAVEN_HOME} \
 && tar -xzf /tmp/maven.tar.gz -C ${MAVEN_HOME} --strip-components=1 \
 # Cleanup
 && rm -rf /var/lib/apt/lists/* \
       /tmp/*
