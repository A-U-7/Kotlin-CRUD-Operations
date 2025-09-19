# 🚀 Kotlin CRUD Operations API

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

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

Built with ❤️ by [Amit Upadhyay]
This is a Kotlin-based Spring Boot application built with Gradle, demonstrating basic CRUD operations (Create, Read, Update, Delete). The project follows a layered architecture with Controller, Service, and Repository to keep the code structured and maintainable.
