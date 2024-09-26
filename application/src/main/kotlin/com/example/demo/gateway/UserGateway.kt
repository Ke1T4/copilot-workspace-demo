package com.example.demo.gateway

import com.example.demo.model.User
import reactor.core.publisher.Mono

interface UserGateway {
    fun findById(userId: Int): Mono<User>
    fun createUser(user: User): Mono<User>
    fun updateUser(userId: Int, user: User): Mono<User>
    fun deleteUser(userId: Int): Mono<Void>
}
