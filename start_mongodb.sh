!/bin/bash

MONGO_IMAGE="mongo:latest"

CONTAINER_NAME="mongodb_container"

# data path for database data 
HOST_DATA_PATH="$(pwd)/mongo_data"

# port
HOST_PORT=27017

# make directory for database data
mkdir -p $HOST_DATA_PATH

# check if the container already running
if [ "$(docker ps -q -f name=$CONTAINER_NAME)" ]; then
    echo "MongoDB läuft bereits im Docker-Container $CONTAINER_NAME."
    exit 0
fi

# check if there is a container with the given name
if [ "$(docker ps -aq -f status=exited -f name=$CONTAINER_NAME)" ]; then
    # Entfernen des gestoppten Containers
    docker rm $CONTAINER_NAME
fi

# start new container
docker run -d \
    --name $CONTAINER_NAME \
    -p $HOST_PORT:27017 \
    -v $HOST_DATA_PATH:/data/db \
    $MONGO_IMAGE

# test if the container is already running
if [ $? -eq 0 ]; then
    echo "MongoDB wurde erfolgreich im Docker-Container $CONTAINER_NAME gestartet."
else
    echo "Fehler beim Starten von MongoDB im Docker-Container $CONTAINER_NAME."
fi
