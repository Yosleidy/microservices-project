#!/bin/bash
# wait-for-it-parallel.sh: wait for multiple services to be ready before executing command

set -e

SERVICES=()
CMD=()
PARSE_CMD=false

# Separar servicios de comando
for ARG in "$@"; do
  if [ "$ARG" == "--" ]; then
    PARSE_CMD=true
    continue
  fi
  if [ "$PARSE_CMD" = false ]; then
    SERVICES+=("$ARG")
  else
    CMD+=("$ARG")
  fi
done

echo "Waiting for services: ${SERVICES[@]}"

for SERVICE in "${SERVICES[@]}"; do
  HOST=$(echo $SERVICE | cut -d: -f1)
  PORT=$(echo $SERVICE | cut -d: -f2)
  echo "Waiting for $HOST:$PORT ..."
  while ! nc -z $HOST $PORT; do
    sleep 1
  done
done

echo "All services are available. Starting command: ${CMD[@]}"
exec "${CMD[@]}"