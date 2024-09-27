package com.example.demo.usecase

import com.example.demo.gateway.UserGateway
import com.example.demo.model.User
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono

class CreateUserUseCaseTest {

    private val userGateway = mockk<UserGateway>()
    private val createUserUseCase = CreateUserUseCase(userGateway)

    @Test
    fun testCreateUser() {
        val user = User(1, "John Doe", 30, "123 Main St")
        every { userGateway.createUser(user) } returns Mono.just(user)

        val result = createUserUseCase.execute(user).block()

        assertEquals(user, result)
        verify { userGateway.createUser(user) }
    }
}
