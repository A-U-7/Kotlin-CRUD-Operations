#!/bin/bash

# Exit on error
set -e

echo "Starting development environment..."

# Start MySQL and Kafka using Docker Compose
echo "Starting MySQL, Kafka, and Zookeeper..."
docker-compose up -d mysql zookeeper kafka

# Wait for Kafka to be ready
echo "Waiting for Kafka to be ready..."
until docker-compose exec kafka kafka-topics --bootstrap-server localhost:9092 --list >/dev/null 2>&1; do
  echo "Waiting for Kafka..."
  sleep 5
done

# Export Kafka bootstrap servers for the application
export KAFKA_BOOTSTRAP_SERVERS=localhost:9092

# Wait for MySQL to be ready
echo "Waiting for MySQL to be ready..."
until docker-compose exec -T mysql mysql -uroot -proot -e "SELECT 1" >/dev/null 2>&1; do
  echo "Waiting for MySQL..."
  sleep 5
done

# Create the database if it doesn't exist
echo "Setting up database..."
docker-compose exec -T mysql mysql -uroot -proot -e "CREATE DATABASE IF NOT EXISTS kotlin_db;"

# Wait for Kafka to be ready
echo "Waiting for Kafka to be ready..."
until docker-compose exec -T kafka kafka-topics --bootstrap-server localhost:9092 --list >/dev/null 2>&1; do
  echo "Waiting for Kafka..."
  sleep 5
done

# Build and start the application
echo "Building and starting the application..."
./gradlew clean build -x test
java -jar build/libs/*.jar --spring.profiles.active=dev

# Clean up on exit
trap "echo 'Stopping services...' && docker-compose down" EXIT
