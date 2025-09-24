#!/bin/bash

# Exit on error
set -e

echo "Building the application..."
./gradlew clean build -x test

# Build Docker image
echo "Building Docker image..."
docker build -t kotlin-crud:latest .

# Create namespace if it doesn't exist
echo "Setting up Kubernetes resources..."
kubectl create namespace kotlin-crud 2>/dev/null || true

# Deploy MySQL
echo "Deploying MySQL..."
kubectl apply -f k8s/mysql.yaml -n kotlin-crud

# Wait for MySQL to be ready
echo "Waiting for MySQL to be ready..."
kubectl wait --for=condition=ready pod -l app=mysql --timeout=300s -n kotlin-crud

# Deploy Kafka and Zookeeper
echo "Deploying Kafka and Zookeeper..."
kubectl apply -f k8s/kafka.yaml -n kotlin-crud

# Wait for Kafka to be ready
echo "Waiting for Kafka to be ready..."
kubectl wait --for=condition=ready pod -l app=kafka --timeout=300s -n kotlin-crud

# Deploy the application
echo "Deploying the application..."
kubectl apply -f k8s/deployment.yaml -n kotlin-crud

echo "Deployment complete!"
echo "To access the application, use the following command:"
echo "kubectl port-forward svc/kotlin-crud-service 8080:80 -n kotlin-crud"
echo "Then open http://localhost:8080 in your browser"
