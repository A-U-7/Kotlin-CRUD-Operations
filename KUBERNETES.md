<style>
  :root {
    --bg-dark: #1a1a1a;
    --bg-darker: #121212;
    --text-light: #e0e0e0;
    --text-lighter: #ffffff;
    --accent-blue: #64b5f6;
    --accent-green: #81c784;
    --accent-orange: #ffb74d;
    --accent-red: #e57373;
    --accent-purple: #ba68c8;
    --border-radius: 8px;
  }
  
  body {
    background-color: var(--bg-dark);
    color: var(--text-light);
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    line-height: 1.6;
    max-width: 1200px;
    margin: 0 auto;
    padding: 20px;
  }
  
  .highlight {
    background-color: rgba(255, 255, 255, 0.05);
    border-radius: var(--border-radius);
    padding: 20px;
    margin: 20px 0;
    border-left: 4px solid;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  }
  
  code, pre {
    background-color: rgba(0, 0, 0, 0.3);
    color: #f8f8f2;
    border-radius: 4px;
    padding: 2px 6px;
    font-family: 'Fira Code', 'Courier New', monospace;
  }
  
  pre {
    padding: 15px;
    overflow-x: auto;
  }
  
  a {
    color: var(--accent-blue);
    text-decoration: none;
  }
  
  a:hover {
    text-decoration: underline;
  }
  
  h1, h2, h3, h4, h5, h6 {
    margin-top: 1.5em;
    margin-bottom: 0.8em;
  }
  
  details {
    margin: 15px 0;
    background-color: rgba(255, 255, 255, 0.03);
    border-radius: var(--border-radius);
    padding: 10px 15px;
  }
  
  summary {
    cursor: pointer;
    font-weight: bold;
  }
  
  table {
    width: 100%;
    border-collapse: collapse;
    margin: 20px 0;
    background-color: rgba(255, 255, 255, 0.03);
    border-radius: var(--border-radius);
    overflow: hidden;
  }
  
  th, td {
    padding: 12px 15px;
    text-align: left;
    border-bottom: 1px solid rgba(255, 255, 255, 0.1);
  }
  
  th {
    background-color: rgba(0, 0, 0, 0.2);
    font-weight: 600;
  }
  
  tr:hover {
    background-color: rgba(255, 255, 255, 0.05);
  }
</style>

<div align="center">
  <h1 style="font-size: 2.5em; margin-bottom: 0.5em; background: linear-gradient(90deg, #4facfe 0%, #00f2fe 100%); -webkit-background-clip: text; -webkit-text-fill-color: transparent;">🚀 Kubernetes Deployment Guide</h1>
  
  <p style="font-size: 1.2em; color: var(--text-light); max-width: 800px; margin: 0 auto 2em;">
    A comprehensive guide to deploying the Kotlin CRUD Operations application on Kubernetes
  </p>
  
  <div style="display: flex; justify-content: center; gap: 15px; flex-wrap: wrap; margin-bottom: 2em;">
    <a href="https://kubernetes.io/" target="_blank">
      <img src="https://img.shields.io/badge/kubernetes-326CE5?style=for-the-badge&logo=kubernetes&logoColor=white" alt="Kubernetes">
    </a>
    <a href="https://www.docker.com/" target="_blank">
      <img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white" alt="Docker">
    </a>
    <a href="https://redis.io/" target="_blank">
      <img src="https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=redis&logoColor=white" alt="Redis">
    </a>
    <a href="https://kafka.apache.org/" target="_blank">
      <img src="https://img.shields.io/badge/Apache_Kafka-231F20?style=for-the-badge&logo=apache-kafka&logoColor=white" alt="Kafka">
    </a>
    <a href="https://www.mysql.com/" target="_blank">
      <img src="https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL">
    </a>
  </div>
</div>

## 📋 Table of Contents

<details>
<summary>🔍 Click to expand</summary>

- [🏗️ <span style="background: linear-gradient(90deg, #8e2de2 0%, #4a00e0 100%); -webkit-background-clip: text; -webkit-text-fill-color: transparent;">Architecture Overview</span>](#️-architecture-overview)
- [🎨 Components](#-components)
  - [📱 <span style="color: #4dabf7;">Application</span>](#-application)
  - [🗃️ <span style="color: #51cf66;">MySQL Database</span>](#️-mysql)
  - [📡 <span style="color: #ff9900;">Kafka</span>](#-kafka)
  - [🔴 <span style="color: #ff3737;">Redis</span>](#-redis)
- [🚀 <span style="background: linear-gradient(90deg, #6a11cb 0%, #2575fc 100%); -webkit-background-clip: text; -webkit-text-fill-color: transparent;">Deployment</span>](#-deployment)
- [📈 <span style="background: linear-gradient(90deg, #f46b45 0%, #eea849 100%); -webkit-background-clip: text; -webkit-text-fill-color: transparent;">Scaling</span>](#-scaling)
- [📊 <span style="background: linear-gradient(90deg, #11998e 0%, #38ef7d 100%); -webkit-background-clip: text; -webkit-text-fill-color: transparent;">Monitoring</span>](#-monitoring)
- [🔧 <span style="background: linear-gradient(90deg, #ff416c 0%, #ff4b2b 100%); -webkit-background-clip: text; -webkit-text-fill-color: transparent;">Troubleshooting</span>](#-troubleshooting)

## Architecture Overview

<div style="background: rgba(0, 0, 0, 0.3); padding: 20px; border-radius: 8px; margin: 20px 0;">

    A[👤 Client] -->|🌐 HTTP/HTTPS| B[📱 Kotlin CRUD App]
    B -->|💾 Read/Write| C[(🗃️ MySQL)]
    B -->|📨 Publish/Subscribe| D[📡 Kafka]
    B -->|⚡ Cache| E[(🔴 Redis)]
    D -->|📥 Consume| B
    
</div>

## Components

### 📱 <span style="color: #64b5f6;">Application</span>

<div class="highlight" style="border-left-color: #64b5f6;">

**File**: `k8s/deployment.yaml`

**Purpose**:
- 🚀 Deploys the Kotlin CRUD application
- ⚙️ Configures environment variables for all dependencies
- 🛡️ Sets up health checks and resource limits

**Key Features**:
- **Replicas**: 1 (configurable)
- **Port**: 8080
- **Health Checks**:
  - ✅ Liveness probe at `/actuator/health`
  - ✅ Readiness probe at `/actuator/health`
- **Environment Variables**:
  - 🌐 Database connection details
  - 📡 Kafka bootstrap servers
  - 🔴 Redis host and port

### 🗃️ <span style="color: #81c784;">MySQL Database</span>

<div class="highlight" style="border-left-color: #81c784;">

**File**: `k8s/mysql.yaml`

**Purpose**:
- 💾 Persistent relational database for application data
- 🔐 Secure credential management using Kubernetes Secrets

**Key Features**:
- **Image**: `mysql:8.0`
- **Storage**: 1Gi Persistent Volume
- **Security**:
  - 🔑 Root credentials stored in Kubernetes Secrets
  - 🔗 In-cluster service name: `mysql`

### 📡 <span style="color: #ffb74d;">Apache Kafka</span>

<div class="highlight" style="border-left-color: #ffb74d;">

**File**: `k8s/kafka.yaml`

**Purpose**:
- 📨 Message broker for event-driven architecture
- 🔄 Used for asynchronous communication between services

**Key Features**:
- **ZooKeeper**: Single node for coordination
- **Kafka Brokers**: 1 replica
- **Persistence**: 1Gi per broker
- **Service**: Exposed as `kafka-service:9092`

### 🔴 <span style="color: #e57373;">Redis Cache</span>

<div class="highlight" style="border-left-color: #e57373;">

**File**: `k8s/redis.yaml`

**Purpose**:
- ⚡ In-memory data store for caching
- 🚀 Improves application performance by reducing database load

**Key Features**:
- **Image**: `redis:7.2`
- **Persistence**: 1Gi Persistent Volume
- **High Availability**: Single node setup (can be scaled)
- **Health Checks**: Built-in liveness and readiness probes

## Deployment

### Prerequisites
- Kubernetes cluster (Minikube, EKS, GKE, or AKS)
- `kubectl` configured to communicate with your cluster

### Steps

1. **Build and Push Docker Image**
   ```bash
   docker build -t your-username/kotlin-crud:latest .
   docker push your-username/kotlin-crud:latest
   ```

2. **Deploy Dependencies**
   ```bash
   # Deploy MySQL
   kubectl apply -f k8s/mysql.yaml
   
   # Deploy Kafka
   kubectl apply -f k8s/kafka.yaml
   
   # Deploy Redis
   kubectl apply -f k8s/redis.yaml
   
   # Wait for all pods to be ready
   kubectl get pods --watch
   ```

3. **Deploy Application**
   ```bash
   kubectl apply -f k8s/deployment.yaml
   ```

4. **Verify Deployment**
   ```bash
   # Check all resources
   kubectl get all
   
   # View application logs
   kubectl logs -l app=kotlin-crud
   ```

## Scaling

### Scale Application
```bash
# Scale to 3 replicas
kubectl scale deployment/kotlin-crud --replicas=3
```

### Scale Kafka
Edit `k8s/kafka.yaml` to increase the number of Kafka brokers and apply changes.

## Monitoring

### Built-in Monitoring
- Application metrics available at `/actuator/metrics`
- Health check at `/actuator/health`

### Kubernetes Dashboard
```bash
kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.7.0/aio/deploy/recommended.yaml
kubectl proxy
```
Access at: http://localhost:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/

## Troubleshooting

### Common Issues

1. **Pods not starting**
   ```bash
   # Check pod status
   kubectl describe pod <pod-name>
   
   # Check logs
   kubectl logs <pod-name>
   ```

2. **Database connection issues**
   ```bash
   # Check MySQL logs
   kubectl logs -l app=mysql
   
   # Check service endpoints
   kubectl get endpoints
   ```

3. **Kafka not reachable**
   ```bash
   # Check Kafka logs
   kubectl logs -l app=kafka
   
   # Check Kafka topics
   kubectl exec -it <kafka-pod> -- kafka-topics --list --bootstrap-server localhost:9092
   ```

## Cleanup

To delete all resources:
```bash
kubectl delete -f k8s/
```

---

## Project Information

**GitHub Repository**: [Kotlin-CRUD-Operations](https://github.com/A-U-7/Kotlin-CRUD-Operations)

**Maintained by**: [Amit Upadhyay](https://github.com/A-U-7)

---

<div align="center">
  <p>Thank you for using this project! If you find it useful, please consider giving it a ⭐️ on GitHub.</p>
  <p>For any issues or feature requests, please open an issue on the GitHub repository.</p>
</div>
