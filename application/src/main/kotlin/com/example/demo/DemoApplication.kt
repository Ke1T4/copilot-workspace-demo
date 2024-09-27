package com.example.demo

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
