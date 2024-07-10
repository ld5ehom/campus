#!/bin/bash

# Docker image build (each module)
docker build -t ucla-discovery:latest -f ucla-discovery/Dockerfile .
docker build -t ucla-gateway:latest -f ucla-gateway/Dockerfile .

# none-gRPC
docker build -t ucla-course-service:latest -f ucla-course-service/Dockerfile .
docker build -t ucla-file-manage-service:latest -f ucla-file-manage-service/Dockerfile .
docker build -t ucla-user-service:latest -f ucla-user-service/Dockerfile .

#gRPC
docker build -t ucla-enrollment-service:latest -f ucla-enrollment-service/Dockerfile .
docker build -t ucla-playback-service:latest -f ucla-playback-service/Dockerfile .
docker build -t ucla-graphql:latest -f ucla-graphql/Dockerfile .