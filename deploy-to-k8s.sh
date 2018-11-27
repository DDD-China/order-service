#!/bin/bash
export CIRCLE_BUILD_NUM_DOCKER=0.1.1
sed "s/BUILD_VERSION/$CIRCLE_BUILD_NUM_DOCKER/g" order-service-kube.yaml > build/order-service-kube.yaml
kubectl apply -f build/order-service-kube.yaml
