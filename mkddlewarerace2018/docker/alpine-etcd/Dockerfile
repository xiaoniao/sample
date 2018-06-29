FROM alpine:3.7

ARG ETCD_VERSION=v3.3.5

ENV ETCDCTL_API=3

RUN set -ex \
 && apk add --update ca-certificates gnupg tar \
 && wget https://github.com/coreos/etcd/releases/download/${ETCD_VERSION}/etcd-${ETCD_VERSION}-linux-amd64.tar.gz -O /tmp/etcd.tar.gz \
 && wget https://github.com/coreos/etcd/releases/download/${ETCD_VERSION}/etcd-${ETCD_VERSION}-linux-amd64.tar.gz.asc -O /tmp/etcd.tar.gz.asc \
 && wget https://coreos.com/dist/pubkeys/app-signing-pubkey.gpg -O /tmp/key.gpg \
 && gpg --import /tmp/key.gpg \
 && gpg --verify /tmp/etcd.tar.gz.asc /tmp/etcd.tar.gz \
 && tar -xzf /tmp/etcd.tar.gz -C /tmp \
 && mv /tmp/etcd-${ETCD_VERSION}-linux-amd64/etcd* /usr/local/bin \
 && apk del --purge gnupg tar \
 && rm -rf /tmp/* \
 && rm -rf /var/cache/apk/*

ADD docker-entrypoint.sh /usr/local/bin
VOLUME /root/logs
EXPOSE 2379

ENTRYPOINT ["docker-entrypoint.sh"]
