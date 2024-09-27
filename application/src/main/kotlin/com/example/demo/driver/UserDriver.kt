package com.example.demo.driver

import com.example.demo.gateway.UserGateway
import com.example.demo.model.User
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class UserDriver(private val r2dbcEntityTemplate: R2dbcEntityTemplate) : UserGateway {

    override fun findById(userId: Int): Mono<User> {
        return r2dbcEntityTemplate.databaseClient.sql("SELECT * FROM users WHERE user_id = :userId")
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

    override fun createUser(user: User): Mono<User> {
        return r2dbcEntityTemplate.databaseClient.sql("INSERT INTO users (name, age, address) VALUES (:name, :age, :address) RETURNING *")
            .bind("name", user.name)
            .bind("age", user.age)
            .bind("address", user.address)
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

    override fun updateUser(userId: Int, user: User): Mono<User> {
        return r2dbcEntityTemplate.databaseClient.sql("UPDATE users SET name = :name, age = :age, address = :address WHERE user_id = :userId RETURNING *")
            .bind("name", user.name)
            .bind("age", user.age)
            .bind("address", user.address)
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

    override fun deleteUser(userId: Int): Mono<Void> {
        return r2dbcEntityTemplate.databaseClient.sql("DELETE FROM users WHERE user_id = :userId")
            .bind("userId", userId)
            .then()
    }
}
