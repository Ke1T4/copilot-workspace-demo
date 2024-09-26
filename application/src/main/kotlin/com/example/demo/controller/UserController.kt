package com.example.demo.controller

import com.example.demo.model.User
import com.example.demo.usecase.GetUserUseCase
import com.example.demo.usecase.CreateUserUseCase
import com.example.demo.usecase.UpdateUserUseCase
import com.example.demo.usecase.DeleteUserUseCase
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
class UserController(
    private val getUserUseCase: GetUserUseCase,
    private val createUserUseCase: CreateUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase
) {

    @GetMapping("/users/{userId}")
    fun getUserById(@PathVariable userId: Int): Mono<UserResponse> {
        return getUserUseCase.execute(userId)
            .map { user -> UserResponse(user.userId, user.name, user.age, user.address) }
    }

    @PostMapping("/users")
    fun createUser(@RequestBody userRequest: UserRequest): Mono<UserResponse> {
        return createUserUseCase.execute(userRequest.toUser())
            .map { user -> UserResponse(user.userId, user.name, user.age, user.address) }
    }

    @PutMapping("/users/{userId}")
    fun updateUser(@PathVariable userId: Int, @RequestBody userRequest: UserRequest): Mono<UserResponse> {
        return updateUserUseCase.execute(userId, userRequest.toUser())
            .map { user -> UserResponse(user.userId, user.name, user.age, user.address) }
    }

    @DeleteMapping("/users/{userId}")
    fun deleteUser(@PathVariable userId: Int): Mono<Void> {
        return deleteUserUseCase.execute(userId)
    }

    data class UserResponse(
        val userId: Int,
        val name: String,
        val age: Int,
        val address: String
    )

    data class UserRequest(
        val name: String,
        val age: Int,
        val address: String
    ) {
        fun toUser(): User {
            return User(0, name, age, address)
        }
    }
}
