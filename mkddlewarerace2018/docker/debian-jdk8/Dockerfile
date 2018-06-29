FROM debian:stretch

ARG DEBIAN_FRONTEND=noninteractive
ARG JAVA_VERSION=8
ARG JAVA_UPDATE=172
ARG JAVA_BUILD=11
ARG JAVA_PACKAGE=jdk
ARG JAVA_HASH=a58eab1ec242421181065cdc37240b08

ENV LANG C.UTF-8
ENV JAVA_HOME=/opt/jdk
ENV PATH=${PATH}:${JAVA_HOME}/bin

RUN set -ex \
 && apt-get update \
 && apt-get install -y ca-certificates curl gnupg net-tools procps unzip \
 && curl -L --header "Cookie: oraclelicense=accept-securebackup-cookie" \
         -o /tmp/java.tar.gz \
         http://download.oracle.com/otn-pub/java/jdk/${JAVA_VERSION}u${JAVA_UPDATE}-b${JAVA_BUILD}/${JAVA_HASH}/${JAVA_PACKAGE}-${JAVA_VERSION}u${JAVA_UPDATE}-linux-x64.tar.gz \
 && CHECKSUM=$(curl -L https://www.oracle.com/webfolder/s/digest/${JAVA_VERSION}u${JAVA_UPDATE}checksum.html | grep -E "${JAVA_PACKAGE}-${JAVA_VERSION}u${JAVA_UPDATE}-linux-x64\.tar\.gz" | grep -Eo '(sha256: )[^<]+' | cut -d: -f2 | xargs) \
 && echo "${CHECKSUM}  /tmp/java.tar.gz" | sha256sum -c \
 && mkdir ${JAVA_HOME} \
 && tar -xzf /tmp/java.tar.gz -C ${JAVA_HOME} --strip-components=1 \
 && curl -L --header "Cookie: oraclelicense=accept-securebackup-cookie;" \
         -o /tmp/jce_policy.zip \
         http://download.oracle.com/otn-pub/java/jce/${JAVA_VERSION}/jce_policy-${JAVA_VERSION}.zip \
 && unzip -jo -d ${JAVA_HOME}/jre/lib/security /tmp/jce_policy.zip \
 && rm -rf ${JAVA_HOME}/jar/lib/security/README.txt \
       /var/lib/apt/lists/* \
       /tmp/*
