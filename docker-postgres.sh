#!/bin/bash

docker run --detach \
  --name postgres-db \
  --env POSTGRES_PASSWORD=password \
  --env POSTGRES_USER=postgres \
  --publish 5432:5432 \
  postgres:9.4.4