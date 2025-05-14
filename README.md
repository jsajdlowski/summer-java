# Fund Box - Fundraising Management System

## 📝 Overview

A Spring Boot application for managing fundraising events and collection boxes with multi-currency support, developed as a recruitment task.

## 🛠️ Technical Stack

| Component          | Version/Details     |
| ------------------ | ------------------- |
| Java               | 21                  |
| Spring Boot        | 3.4.5               |
| Build Tool         | Maven               |
| Database           | H2 (embedded)       |
| API Documentation  | Swagger UI          |
| Exchange Rates API | exchangeratesapi.io |

## 📚 API Documentation

Interactive API documentation available at:  
👉 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## ⚙️ Configuration

1. Copy the provided environment file:
   ```bash
   cp .env.example .env
   ```
   _Note: A valid Exchange Rates API key is pre-configured in the example file._

## 🚀 Running the Application

```bash
# Install dependencies
mvn clean install

# Start the application
mvn spring-boot:run
```

## Access Points

    Application base URL: http://localhost:8080

    Swagger UI: http://localhost:8080/swagger-ui/index.html
