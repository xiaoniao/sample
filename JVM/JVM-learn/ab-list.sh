#!/usr/bin/env bash


ab -n 10000 -c 100 -p list.json -T application/json http://localhost:8080//knowledge/list