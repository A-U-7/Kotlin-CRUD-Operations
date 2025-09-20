# 🐳 Docker Commands for Kotlin CRUD Operations Application

<div align="center">
  <img src="https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white" alt="Docker">
  <img src="https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white" alt="Kotlin">
  <img src="https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot">
</div>

## 📋 Table of Contents
- [🚀 Quick Start](#-quick-start)
- [🔧 Docker Compose Commands](#-docker-compose-commands)
- [🐋 Basic Docker Commands](#-basic-docker-commands)
- [🖼️ Image Management](#️-image-management)
- [💾 Volume Management](#-volume-management)
- [🌐 Networking](#-networking)
- [🧹 Cleanup & Maintenance](#-cleanup--maintenance)
- [🔍 Troubleshooting](#-troubleshooting)
- [🏗️ Advanced Usage](#️-advanced-usage)

## 🚀 Quick Start

Get your Kotlin CRUD application up and running with just one command:

```bash
# Start all services defined in docker-compose.yml
docker-compose up -d --build
```

Access your application at: [http://localhost:8080](http://localhost:8080)  
H2 Console: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

---

## 🔧 Docker Compose Commands

| Command | Description |
|---------|-------------|
| `docker-compose up -d --build` | 🚀 Build and start all services in detached mode |
| `docker-compose down` | ⏹️ Stop and remove all containers, networks |
| `docker-compose logs -f` | 📜 Follow logs from all services |
| `docker-compose ps` | 📋 List all running services |
| `docker-compose exec <service> sh` | 💻 Open shell in a running service |
| `docker-compose restart <service>` | 🔄 Restart a specific service |

### Example: Rebuild and restart a specific service
```bash
docker-compose up -d --build <service_name>
```

---

## 🐋 Basic Docker Commands

### 🏗️ Build & Run
```bash
# Build an image from Dockerfile
docker build -t kotlin-crud-app .

# Run a container in detached mode
docker run -d -p 8080:8080 --name kotlin-crud-container kotlin-crud-app
```

### 🕵️ Container Management
```bash
# List running containers
docker ps

# List all containers (including stopped)
docker ps -a

# Stop a container
docker stop kotlin-crud-container

# Start a stopped container
docker start kotlin-crud-container

# Remove a container (must be stopped first)
docker rm kotlin-crud-container

# Force remove a running container
docker rm -f kotlin-crud-container
```

### 📜 Logs & Debugging
```bash
# View logs
docker logs kotlin-crud-container

# Follow logs in real-time
docker logs -f kotlin-crud-container

# Execute command in running container
docker exec -it kotlin-crud-container /bin/sh
```

---

## 🖼️ Image Management

```bash
# List all images
docker images

# Remove an image
docker rmi kotlin-crud-app

# Remove all unused images
docker image prune -a

# Build with a specific tag
docker build -t username/kotlin-crud-app:1.0.0 .

# Push to Docker Hub
docker push username/kotlin-crud-app:1.0.0
```

---

## 💾 Volume Management

```bash
# List all volumes
docker volume ls

# Inspect a volume
docker volume inspect <volume_name>

# Remove a volume
docker volume rm <volume_name>

# Remove all unused volumes
docker volume prune
```

---

## 🌐 Networking

```bash
# List all networks
docker network ls

# Inspect a network
docker network inspect <network_name>

# Create a new network
docker network create my-network

# Connect a container to a network
docker network connect my-network kotlin-crud-container
```

---

## 🧹 Cleanup & Maintenance

```bash
# Remove all stopped containers
docker container prune

# Remove all unused networks
docker network prune

# Remove all unused images, containers, and networks
docker system prune -a

# Remove everything (use with caution!)
docker system prune -a --volumes
```

---

## 🔍 Troubleshooting

### Port Already in Use
```bash
# Check what's using the port
netstat -ano | findstr :8080

# Or use a different port
docker run -d -p 8081:8080 --name kotlin-crud-container kotlin-crud-app
```

### Permission Issues
```bash
# Run container with current user
docker run -d -p 8080:8080 --user "$(id -u):$(id -g)" --name kotlin-crud-container kotlin-crud-app

# Fix volume permissions
docker run --rm -v /path/on/host:/path/in/container alpine chown -R 1000:1000 /path/in/container
```

### Resource Monitoring
```bash
# View container resource usage
docker stats

# View detailed container info
docker inspect kotlin-crud-container
```

---

## 🏗️ Advanced Usage

### Multi-stage Builds
```dockerfile
# Build stage
FROM gradle:jdk21 AS build
# ... build steps ...

# Runtime stage
FROM eclipse-temurin:21-jre-jammy
# ... copy artifacts and configure runtime ...
```

### Health Checks
```dockerfile
HEALTHCHECK --interval=30s --timeout=10s --start-period=5s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1
```

### Environment Variables
```bash
# Pass environment variables at runtime
docker run -d -p 8080:8080 -e SPRING_PROFILES_ACTIVE=prod --name kotlin-crud-container kotlin-crud-app

# Use an env file
docker run -d -p 8080:8080 --env-file .env --name kotlin-crud-container kotlin-crud-app
```

### Docker Compose Overrides
```yaml
# docker-compose.override.yml
version: '3.8'
services:
  app:
    environment:
      - SPRING_PROFILES_ACTIVE=dev
    ports:
      - "8081:8080"
```

---

<div align="center">
  <p>✨ <strong>Happy Dockering!</strong> 🐳✨</p>
  <p>Made with ❤️ by Amit Upadhyay for Kotlin & Docker</p>
</div>
