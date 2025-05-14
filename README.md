# Fund Box Recruitment Task

## Overview
This is a Spring Boot application for managing fundraising events and collection boxes, developed as a recruitment task.

## Technical Stack
- **Java 21**
- **Spring Boot 3.4.5**
- **Maven** (build tool)
- **H2 Database** (embedded, for development)
- **Swagger** (API documentation)
- **Exchange Rates API** (for currency conversion)

## API Documentation
Interactive API documentation available at:  
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Configuration
Copy `.env.example` to `.env` (I've provided a valid Exhange Rates API key inside)

## Running the Application
```bash
mvn clean install
mvn spring-boot:run