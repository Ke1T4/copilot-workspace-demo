package com.example.demo.driver

import com.example.demo.gateway.UserGateway
import com.example.demo.model.User
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class UserDriver(private val databaseClient: DatabaseClient) : UserGateway {

    override fun findById(userId: Int): Mono<User> {
        return databaseClient.sql("SELECT * FROM users WHERE user_id = :userId")
            .bind("userId", userId)
            .map { row, _ ->
                User(
                    userId = row.get("user_id", Integer::class.java)!!,
                    name = row.get("name", String::class.java)!!,
                    age = row.get("age", Integer::class.java)!!,
                    address = row.get("address", String::class.java)!!
                )
            }
            .one()
    }
}
