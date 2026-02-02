# Course Service

## Overview

Course Service is a Spring Boot microservice for the Learning Management System (LMS) that handles course management operations. It provides RESTful APIs for creating, retrieving, and managing courses with their associated modules and lessons.

## Technology Stack

- **Language:** Java 17
- **Framework:** Spring Boot 3.2.0
- **Database:** MongoDB
- **Build Tool:** Maven
- **Validation:** Spring Validation

## Project Structure

```
course-service/
├── pom.xml
└── src/
    └── main/
        ├── java/com/lms/course/
        │   ├── CourseServiceApplication.java
        │   ├── controller/
        │   │   └── CourseController.java
        │   ├── dto/
        │   │   ├── CourseCreateRequest.java
        │   │   ├── CourseResponse.java
        │   │   ├── CourseModuleRequest.java
        │   │   ├── CourseModuleResponse.java
        │   │   ├── LessonRequest.java
        │   │   └── LessonResponse.java
        │   ├── entity/
        │   │   ├── Course.java
        │   │   ├── CourseModule.java
        │   │   └── Lesson.java
        │   ├── exception/
        │   │   ├── CourseNotFoundException.java
        │   │   └── GlobalExceptionHandler.java
        │   ├── mapper/
        │   │   └── CourseMapper.java
        │   ├── repository/
        │   │   └── CourseRepository.java
        │   └── service/
        │       ├── CourseService.java
        │       └── impl/
        │           └── CourseServiceImpl.java
        └── resources/
            └── application.properties
```

## Architecture

### Layered Architecture

1. **Controller Layer** - Handles HTTP requests and responses
2. **Service Layer** - Business logic implementation
3. **Repository Layer** - Data access layer (MongoDB)
4. **Entity Layer** - Domain models
5. **DTO Layer** - Data Transfer Objects for API requests/responses

## Data Model

### Course Entity

```java
- String id (MongoDB _id)
- int courseId (Business ID)
- String title
- List<CourseModule> modules
- LocalDateTime createdAt
- LocalDateTime updatedAt
```

### CourseModule Entity

```java
- String title
- List<Lesson> lessons
```

### Lesson Entity

```java
- String title
- String type (Lesson type: video, text, quiz, etc.)
- String mediaId (Reference to media content)
```

## API Endpoints

### 1. Create a New Course

**Endpoint:** `POST /api/courses`

**Request Body:**

```json
{
  "courseId": 1,
  "title": "Introduction to Programming",
  "modules": [
    {
      "title": "Module 1",
      "lessons": [
        {
          "title": "Getting Started",
          "type": "video",
          "mediaId": "media-001"
        }
      ]
    }
  ]
}
```

**Response:** `201 Created`

```json
{
    "courseId": 1,
    "title": "Introduction to Programming",
    "modules": [...],
    "createdAt": "2024-01-15T10:30:00",
    "updatedAt": "2024-01-15T10:30:00"
}
```

### 2. Get All Courses

**Endpoint:** `GET /api/courses`

**Response:** `200 OK`

```json
[
    {
        "courseId": 1,
        "title": "Introduction to Programming",
        "modules": [...],
        "createdAt": "2024-01-15T10:30:00",
        "updatedAt": "2024-01-15T10:30:00"
    }
]
```

### 3. Get Course by ID

**Endpoint:** `GET /api/courses/{courseId}`

**Response:** `200 OK` (if found) or `404 Not Found`

## Configuration

The service is configured via `application.properties`:

```properties
# Application
spring.application.name=course-service

# MongoDB Configuration
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=course_service

# Server Configuration
server.port=8081
```

## Dependencies

- **spring-boot-starter-web** - RESTful web services
- **spring-boot-starter-data-mongodb** - MongoDB support
- **spring-boot-starter-validation** - Request validation
- **spring-boot-starter-test** - Testing framework

## Building the Service

```bash
# Navigate to project directory
cd course-service

# Build the project
./mvnw clean install

# Run tests
./mvnw test
```

## Running the Service

```bash
# Run the application
./mvnw spring-boot:run

# Or run the packaged JAR
java -jar target/course-service-1.0.0.jar
```

## Prerequisites

- Java 17 or higher
- MongoDB 4.4+ (running on localhost:27017)
- Maven 3.6+

## MongoDB Setup

Ensure MongoDB is running and the database is accessible:

```bash
# Start MongoDB (if installed locally)
mongod --dbpath /path/to/data/directory

# Or using Docker
docker run -d -p 27017:27017 --name mongodb mongo:latest
```

## Error Handling

The service includes global exception handling:

- `CourseNotFoundException` - Returns 404 when course is not found
- General exceptions - Returns 500 Internal Server Error

## Service Ports

| Service        | Port  |
| -------------- | ----- |
| Course Service | 8081  |
| MongoDB        | 27017 |

## Future Enhancements

- Add update and delete course endpoints
- Implement course search and filtering
- Add pagination for course listing
- Implement authentication and authorization
- Add unit and integration tests
- Add API documentation with Swagger/OpenAPI
- Implement course versioning
- Add module and lesson management endpoints

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is part of the DLMS (Learning Management System).
