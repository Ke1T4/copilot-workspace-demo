package com.example.demo.usecase

import com.example.demo.gateway.UserGateway
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono

class DeleteUserUseCaseTest {

    private val userGateway = mockk<UserGateway>()
    private val deleteUserUseCase = DeleteUserUseCase(userGateway)

    @Test
    fun testDeleteUser() {
        val userId = 1
        every { userGateway.deleteUser(userId) } returns Mono.empty()

        val result = deleteUserUseCase.execute(userId).block()

        assertEquals(null, result)
        verify { userGateway.deleteUser(userId) }
    }
}
