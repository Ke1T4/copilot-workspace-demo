package com.example.demo.usecase

import com.example.demo.gateway.UserGateway
import com.example.demo.model.User
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono

class GetUserUseCaseTest {

    private val userGateway = mockk<UserGateway>()
    private val getUserUseCase = GetUserUseCase(userGateway)

    @Test
    fun testGetUser() {
        val userId = 1
        val user = User(userId, "John Doe", 30, "123 Main St")
        every { userGateway.findById(userId) } returns Mono.just(user)

        val result = getUserUseCase.execute(userId).block()

        assertEquals(user, result)
        verify { userGateway.findById(userId) }
    }
}
