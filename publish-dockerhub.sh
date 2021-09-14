#!/bin/bash

VERSION=${1}
DOCKERHUB_USERNAME=${2}
DOCKERHUB_PASSWORD=${3}


AGENT=${DOCKERHUB_USERNAME}/nistagram-agent-service:${VERSION}

DOCKER_BUILDKIT=1 docker build -t ${AGENT} --no-cache .

docker login --username ${DOCKERHUB_USERNAME} --password ${DOCKERHUB_PASSWORD}

docker push ${AGENT}