# Spring Boot Application with Kotlin

This is a simple Spring Boot application written in Kotlin.

## Prerequisites

- JDK 11 or later
- Gradle

## Building the Project

To build the project, run the following command:

```
./gradlew build
```

## Running the Application

To run the application, use the following command:

```
./gradlew bootRun
```

The application will start and be accessible at `http://localhost:8080`.

## API Endpoints

### GET /hello

Returns a greeting message.

Example request:

```
GET /hello
```

Example response:

```
Hello, World!
```
