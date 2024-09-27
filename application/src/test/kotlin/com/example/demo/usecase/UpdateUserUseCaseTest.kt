package com.example.demo.usecase

import com.example.demo.gateway.UserGateway
import com.example.demo.model.User
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono

class UpdateUserUseCaseTest {

    private val userGateway = mockk<UserGateway>()
    private val updateUserUseCase = UpdateUserUseCase(userGateway)

    @Test
    fun testUpdateUser() {
        val userId = 1
        val user = User(userId, "John Doe", 30, "123 Main St")
        every { userGateway.updateUser(userId, user) } returns Mono.just(user)

        val result = updateUserUseCase.execute(userId, user).block()

        assertEquals(user, result)
        verify { userGateway.updateUser(userId, user) }
    }
}
