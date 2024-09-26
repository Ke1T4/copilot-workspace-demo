package com.example.demo.usecase

import com.example.demo.gateway.UserGateway
import com.example.demo.model.User
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class UpdateUserUseCase(private val userGateway: UserGateway) {

    fun execute(userId: Int, user: User): Mono<User> {
        return userGateway.updateUser(userId, user)
    }
}
