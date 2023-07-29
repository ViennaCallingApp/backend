#!/usr/bin/env bash
set -euo pipefail

IMAGE_TAG=$1
docker buildx build --push \
  --tag jajcoszek/viennacalling-backend:${IMAGE_TAG} \
  --tag jajcoszek/viennacalling-backend:latest \
  --platform linux/amd64,linux/arm64 \
  .

cd deploy
../kustomize edit set image jajcoszek/viennacalling-backend=jajcoszek/viennacalling-backend:${IMAGE_TAG}
