#!/bin/bash

echo "Checking if Kafka is running..."
docker-compose ps kafka

echo -e "\nChecking Kafka logs..."
docker-compose logs kafka --tail=20

echo -e "\nListing Kafka topics..."
docker-compose exec kafka kafka-topics --bootstrap-server localhost:9092 --list

echo -e "\nTesting Kafka connection from host..."
# Try to list topics from host
kafkacat -L -b localhost:9092

if [ $? -eq 0 ]; then
    echo -e "\n Kafka is accessible from the host machine!"
else
    echo -e "\n Could not connect to Kafka from the host machine"
    echo "Make sure the Kafka container is running and ports 9092 and 29092 are not blocked"
fi
