# 🚀 Kotlin CRUD Operations with Spring Boot, Kafka, and MySQL

This is a Kotlin-based CRUD application built with Spring Boot, featuring:
- RESTful API endpoints
- MySQL database integration
- Apache Kafka for event-driven architecture
- Containerized with Docker
- Kubernetes deployment ready

## Prerequisites

- Docker and Docker Compose
- Kubernetes cluster (Docker Desktop, Minikube, or cloud-based)
- kubectl configured to communicate with your cluster
- JDK 21
- Gradle

## Local Development

### Using Docker Compose

1. Start the application with dependencies:
   ```bash
   docker-compose up -d
   ```

2. The application will be available at: http://localhost:8080

### Running Locally

1. Start the required services:
   ```bash
   docker-compose up -d mysql kafka
   ```

2. Run the application:
   ```bash
   ./gradlew bootRun
   ```

## Kubernetes Deployment

1. Make sure your Kubernetes cluster is running and kubectl is configured.

2. Deploy the application:
   ```bash
   chmod +x deploy.sh
   ./deploy.sh
   ```

3. Access the application:
   ```bash
   kubectl port-forward svc/kotlin-crud-service 8080:80 -n kotlin-crud
   ```
   Then open http://localhost:8080 in your browser

## API Documentation

Once the application is running, you can access:
- Swagger UI: http://localhost:8080/swagger-ui.html
- API Docs: http://localhost:8080/api-docs

## Architecture

- **Web Layer**: Spring MVC REST controllers
- **Service Layer**: Business logic and transaction management
- **Repository Layer**: JPA repositories for data access
- **Event-Driven**: Kafka for asynchronous event processing
- **Persistence**: MySQL for data storage

## Environment Variables

| Variable | Description | Default |
|----------|-------------|---------|
| SPRING_DATASOURCE_URL | MySQL JDBC URL | jdbc:mysql://mysql:3306/kotlin_db |
| SPRING_DATASOURCE_USERNAME | Database username | root |
| SPRING_DATASOURCE_PASSWORD | Database password | root |
| SPRING_KAFKA_BOOTSTRAP_SERVERS | Kafka bootstrap servers | kafka:9092 |

## Monitoring

The application includes Spring Boot Actuator endpoints for monitoring:
- Health: `/actuator/health`
- Metrics: `/actuator/metrics`
- Info: `/actuator/info` API

A robust RESTful API built with Spring Boot and Kotlin that demonstrates CRUD (Create, Read, Update, Delete) operations for a User management system.

## 🌟 Features

- **RESTful API** endpoints for user management
- **H2 In-Memory Database** for easy setup
- **Input Validation** for data integrity
- **Global Exception Handling** for clean error responses
- **JPA & Hibernate** for ORM
- **Kotlin** for concise and expressive code

## 🛠️ Tech Stack

- **Language**: Kotlin 1.9.25
- **Framework**: Spring Boot 3.5.6
- **Database**: H2 In-Memory Database
- **Build Tool**: Gradle (Kotlin DSL)
- **Validation**: Jakarta Validation API

## 🚀 Getting Started

### Prerequisites

- Java 21 or later
- Gradle 8.0 or later
- Your favorite IDE (IntelliJ IDEA recommended)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/kotlin-crud-operations.git
   cd kotlin-crud-operations
   ```

2. Build the project:
   ```bash
   ./gradlew build
   ```

3. Run the application:
   ```bash
   ./gradlew bootRun
   ```

The application will start on `http://localhost:8080`

## 🌐 API Endpoints

| Method | Endpoint          | Description                |
|--------|-------------------|----------------------------|
| GET    | /api/users        | Get all users              |
| GET    | /api/users/{id}   | Get user by ID             |
| POST   | /api/users        | Create a new user          |
| PUT    | /api/users/{id}   | Update an existing user    |
| DELETE | /api/users/{id}   | Delete a user              |

## 📝 API Examples

### Create a New User

**Request**
```http
POST /api/users
Content-Type: application/json

{
    "firstName": "Amit",
    "lastName": "Upadhyay",
    "email": "amit@yahoo.com",
    "age": 30
}
```

**Response**
```json
{
    "success": true,
    "message": "User created successfully",
    "data": {
        "id": 1,
        "firstName": "Amit",
        "lastName": "Upadhyay",
        "email": "amit@yahoo.com",
        "age": 30,
        "createdAt": "2025-09-19T19:13:30.7837096",
        "updatedAt": "2025-09-19T19:13:30.7837096"
    }
}
```

### Get All Users

**Request**
```http
GET /api/users
```

**Response**
```json
{
    "success": true,
    "message": "Users retrieved successfully",
    "data": [
        {
            "id": 1,
            "firstName": "Amit",
            "lastName": "Upadhyay",
            "email": "amit@yahoo.com",
            "age": 30,
            "createdAt": "2025-09-19T19:13:30.7837096",
            "updatedAt": "2025-09-19T19:13:30.7837096"
        }
    ]
}
```

## 🛡️ Validation Rules

- **First Name**: Required, 2-50 characters, letters, spaces, hyphens, and apostrophes only
- **Last Name**: Required, 2-50 characters, letters, spaces, hyphens, and apostrophes only
- **Email**: Required, valid email format, 5-100 characters, unique
- **Age**: Required, between 1 and 150

## 🔍 H2 Database Console

Access the H2 database console at: http://localhost:8080/h2-console

- **JDBC URL**: jdbc:h2:mem:kotlin_db
- **User Name**: sa
- **Password**: (leave empty)

## 🧪 Testing

Run the tests with:
```bash
./gradlew test
```

Built with ❤️ by [Amit Upadhyay]
This is a Kotlin-based Spring Boot application built with Gradle, demonstrating basic CRUD operations (Create, Read, Update, Delete). The project follows a layered architecture with Controller, Service, and Repository to keep the code structured and maintainable.
