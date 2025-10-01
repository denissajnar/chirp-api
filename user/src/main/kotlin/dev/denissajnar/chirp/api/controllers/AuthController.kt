package dev.denissajnar.chirp.api.controllers

import dev.denissajnar.chirp.api.dto.RegisterRequest
import dev.denissajnar.chirp.api.dto.UserDto
import dev.denissajnar.chirp.api.mappers.toUserDto
import dev.denissajnar.chirp.service.auth.AuthService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authService: AuthService,
) {

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    suspend fun register(
        @Valid
        @RequestBody
        body: RegisterRequest,
    ): UserDto =
        authService.register(body.email, body.username, body.password).toUserDto()
}
