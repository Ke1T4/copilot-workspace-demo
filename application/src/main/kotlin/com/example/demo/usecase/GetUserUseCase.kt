package com.example.demo.usecase

import com.example.demo.gateway.UserGateway
import com.example.demo.model.User
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class GetUserUseCase(private val userGateway: UserGateway) {

    fun execute(userId: Int): Mono<User> {
        return userGateway.findById(userId)
    }
}
