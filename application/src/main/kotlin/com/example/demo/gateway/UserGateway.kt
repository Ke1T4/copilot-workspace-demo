package com.example.demo.gateway

import com.example.demo.model.User
import reactor.core.publisher.Mono

interface UserGateway {
    fun findById(userId: Int): Mono<User>
}
