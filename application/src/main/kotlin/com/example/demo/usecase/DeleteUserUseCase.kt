package com.example.demo.usecase

import com.example.demo.gateway.UserGateway
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class DeleteUserUseCase(private val userGateway: UserGateway) {

    fun execute(userId: Int): Mono<Void> {
        return userGateway.deleteUser(userId)
    }
}
