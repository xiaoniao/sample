#!/bin/sh

CLIENT_URLS=http://0.0.0.0:2379

ETCD_CMD="etcd --data-dir=/data --listen-client-urls=${CLIENT_URLS} --advertise-client-urls=${CLIENT_URLS}"
echo -e "Running '$ETCD_CMD'"

exec $ETCD_CMD > /root/logs/etcd.log 2>&1
