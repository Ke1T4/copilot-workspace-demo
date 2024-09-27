# Spring Boot Application with Kotlin and WebFlux

This is a simple Spring Boot application written in Kotlin, now updated to use Spring WebFlux for reactive programming.

## Prerequisites

- JDK 11 or later
- Gradle
- Docker

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

### Setting up R2DBC

To set up R2DBC, follow these steps:

1. Add the following dependencies to your `build.gradle.kts` file:

```kotlin
implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
implementation("io.r2dbc:r2dbc-postgresql")
```

2. Configure R2DBC in your `DemoApplication.kt` file:

```kotlin
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import io.r2dbc.spi.ConnectionFactory
import org.springframework.boot.autoconfigure.r2dbc.ConnectionFactoryBuilder

@SpringBootApplication
@EnableR2dbcRepositories
class DemoApplication

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}

@Configuration
class R2dbcConfig {

    @Bean
    fun connectionFactory(): ConnectionFactory {
        return ConnectionFactoryBuilder.withUrl("r2dbc:postgresql://localhost:5432/mydatabase")
            .username("myuser")
            .password("mypassword")
            .build()
    }

    @Bean
    fun databaseClient(connectionFactory: ConnectionFactory): DatabaseClient {
        return DatabaseClient.create(connectionFactory)
    }

    @Bean
    fun r2dbcEntityTemplate(databaseClient: DatabaseClient): R2dbcEntityTemplate {
        return R2dbcEntityTemplate(databaseClient)
    }
}
```

## Running the Application and Database with Docker

To build and run the application and database using Docker, follow these steps:

1. Build the Docker images:

```
docker-compose build
```

2. Start the Docker containers:

```
docker-compose up
```

The application will be accessible at `http://localhost:8080` and the database will be accessible at `localhost:5432`.

### Setting up R2DBC with Docker

To set up R2DBC with Docker, follow these steps:

1. Update the `docker-compose.yml` file to include the R2DBC configuration for the `application` service:

```yaml
version: '3.8'

services:
  application:
    build:
      context: ./application
      dockerfile: Dockerfile
    ports:
      - "8080:8080"
    depends_on:
      - database
    environment:
      SPRING_DATASOURCE_URL: r2dbc:postgresql://database:5432/mydatabase

  database:
    build:
      context: ./database
      dockerfile: Dockerfile
    environment:
      POSTGRES_DB: mydatabase
      POSTGRES_USER: myuser
      POSTGRES_PASSWORD: mypassword
    ports:
      - "5432:5432"
```

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
