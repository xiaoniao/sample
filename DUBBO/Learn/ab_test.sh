#!/usr/bin/env bash
ab -n 4 -c 2 http://localhost:8080/available

ab -n 4 -c 2 http://localhost:8080/broadcast

ab -n 4 -c 2 http://localhost:8080/failback

ab -n 4 -c 2 http://localhost:8080/failfast

ab -n 4 -c 2 http://localhost:8080/failOver

ab -n 4 -c 2 http://localhost:8080/failSafe

ab -n 4 -c 2 http://localhost:8080/forking

ab -n 4 -c 2 http://localhost:8080/mergeable