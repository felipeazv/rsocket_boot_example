#!/usr/bin/env bash

## app info
APP="rsocket-boot"
PROFILE="docker"
RSOCKET_SERVER_ADDRESS="rsocket-boot-server"
RSOCKET_SERVER_PORT=9999
RSOCKET_SERVER_TRANSPORT="tcp"

## k8s info
NAMESPACE=${APP}
K8S_CONTEXT="docker-desktop"

##docker info
DOCKER_REPOSITORY="docker.io/"
DOCKER_TAG="1.0-SNAPSHOT"

DOCKER_REGISTRY="docker-registry"
DOCKER_REGISTRY_SERVER="index.docker.io"
DOCKER_REGISTRY_USERNAME=""
DOCKER_REGISTRY_PASSWORD=""

##clean up
kubectl delete ns ${NAMESPACE}
helm del --purge ${APP}

docker login $DOCKER_REGISTRY_SERVER --username=$DOCKER_REGISTRY_USERNAME --password=$DOCKER_REGISTRY_PASSWORD

declare -a IMAGES=("rsocket-boot-server" "rsocket-boot-client")
for IMAGE in "${IMAGES[@]}"
do
    DOCKER_IMAGE="${DOCKER_REPOSITORY}${IMAGE}:${DOCKER_TAG}"
    echo pushing image ${DOCKER_IMAGE} ...
#    docker push ${DOCKER_IMAGE}
done

kubectl config use-context ${K8S_CONTEXT}
kubectl create ns ${NAMESPACE}
kubectl create secret docker-registry ${DOCKER_REGISTRY} --docker-server=$DOCKER_REGISTRY_SERVER --docker-username=$DOCKER_REGISTRY_USERNAME --docker-password=$DOCKER_REGISTRY_PASSWORD --namespace ${NAMESPACE}

helm install rsocket-boot \
    --name-template ${APP} \
    --namespace ${NAMESPACE} \
    --set-string rsocket-boot-server.config.rsocket.server.address=${RSOCKET_SERVER_ADDRESS} \
    --set-string rsocket-boot-server.config.rsocket.server.port=${RSOCKET_SERVER_PORT} \
    --set-string rsocket-boot-server.config.rsocket.server.transport=${RSOCKET_SERVER_TRANSPORT} \
    --set-string rsocket-boot-client.config.rsocket.client.address=${RSOCKET_SERVER_ADDRESS} \
    --set-string rsocket-boot-client.config.rsocket.client.port=${RSOCKET_SERVER_PORT} \
    --set-string global.config.profile=${PROFILE} \
    --set-string global.image.repository=${DOCKER_REPOSITORY} \
    --set-string global.image.tag=${DOCKER_TAG} \
    --set-string global.image.pullSecret=${DOCKER_REGISTRY}