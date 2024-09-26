package com.example.demo.usecase

import com.example.demo.gateway.UserGateway
import com.example.demo.model.User
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class CreateUserUseCase(private val userGateway: UserGateway) {

    fun execute(user: User): Mono<User> {
        return userGateway.createUser(user)
    }
}
