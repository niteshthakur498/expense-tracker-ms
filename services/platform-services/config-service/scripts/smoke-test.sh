#!/bin/bash
set -e

echo "Running smoke test for Config Service..."

# Health check (service-specific)
if curl -s http://localhost:7001/actuator/info | grep -q "config-service"; then
  echo "✅ Config Service info endpoint is correct"
else
  echo "❌ Config Service info endpoint failed"
  exit 1
fi

# Try fetching config from Git repo
if curl -s http://localhost:7001/application/default | grep -q "spring"; then
  echo "✅ Config Service is serving properties"
else
  echo "❌ Config Service is not serving configs"
  exit 1
fi
