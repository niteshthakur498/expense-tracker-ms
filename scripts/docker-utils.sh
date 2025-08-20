#!/bin/bash
set -e

SERVICE=$1
VERSION=${2:-latest}
DOCKER_USER="your-dockerhub-username"

if [ -z "$SERVICE" ]; then
  echo "Usage: ./docker-utils.sh <service-name> [version]"
  exit 1
fi

echo "Building Docker image for $SERVICE..."
docker build -t $DOCKER_USER/$SERVICE:$VERSION ./$SERVICE

echo "Pushing Docker image to DockerHub..."
docker push $DOCKER_USER/$SERVICE:$VERSION

echo "✅ Docker image $DOCKER_USER/$SERVICE:$VERSION pushed successfully."

