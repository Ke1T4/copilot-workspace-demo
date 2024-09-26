package com.example.demo.controller

import com.example.demo.usecase.GetUserUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class UserController(private val getUserUseCase: GetUserUseCase) {

    @GetMapping("/users/{userId}")
    fun getUserById(@PathVariable userId: Int): Mono<UserResponse> {
        return getUserUseCase.execute(userId)
            .map { user -> UserResponse(user.userId, user.name, user.age, user.address) }
    }

    data class UserResponse(
        val userId: Int,
        val name: String,
        val age: Int,
        val address: String
    )
}
