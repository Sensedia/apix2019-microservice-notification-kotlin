#!/bin/bash

. ./variables.sh

docker-compose up -d --no-deps --build apix2019-microservice-notification-kotlin
