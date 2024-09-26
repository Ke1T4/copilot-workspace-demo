# Spring Boot Application with Kotlin and WebFlux

This is a simple Spring Boot application written in Kotlin, now updated to use Spring WebFlux for reactive programming.

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

Returns a greeting message using WebFlux.

Example request:

```
GET /hello
```

Example response:

```
Hello, World!
```

## Reactive Programming Concepts

Spring WebFlux is a part of the Spring Framework that supports reactive programming. Reactive programming is a programming paradigm that deals with asynchronous data streams and the propagation of change. It is particularly useful for applications that require high concurrency and scalability.

In this project, we use `Mono` from the Reactor library to represent a single asynchronous value. The `hello()` method in the `HelloController` class returns a `Mono<String>`, which is a reactive type that emits a single value or an error.
