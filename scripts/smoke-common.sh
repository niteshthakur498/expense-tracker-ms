#!/bin/bash
set -e

# Common health endpoints to check
services=(
  "config-service:http://localhost:7001/actuator/health"
  "discovery-service:http://localhost:8761/actuator/health"
  "api-gateway:http://localhost:8080/actuator/health"
)

echo "Running common smoke tests..."

for service in "${services[@]}"; do
  name="${service%%:*}"
  url="${service#*:}"
  
  echo "Checking $name at $url ..."
  if curl -s "$url" | grep -q '"status":"UP"'; then
    echo "✅ $name is UP"
  else
    echo "❌ $name is DOWN"
    exit 1
  fi
done

