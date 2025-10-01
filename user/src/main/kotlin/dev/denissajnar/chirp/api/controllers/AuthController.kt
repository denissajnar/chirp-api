package dev.denissajnar.chirp.api.controllers

import dev.denissajnar.chirp.api.dto.ErrorResponse
import dev.denissajnar.chirp.api.dto.RegisterRequest
import dev.denissajnar.chirp.api.dto.UserResponse
import dev.denissajnar.chirp.api.mappers.toUserDto
import dev.denissajnar.chirp.service.auth.AuthService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Endpoints for user authentication")
class AuthController(
    private val authService: AuthService,
) {

    @Operation(
        summary = "Register a new user",
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201",
                description = "User registered successfully",
                content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = UserResponse::class),
                    ),
                ],
            ),
            ApiResponse(
                responseCode = "400",
                description = "Invalid request body",
                content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = ErrorResponse::class),
                        examples = [
                            ExampleObject(
                                name = "Validation Error",
                                value = """{"timestamp":"2025-10-01T10:30:00Z","status":400,"error":"Bad Request","message":"Validation failed","errors":{"email":"must be a valid email address","password":"must be at least 8 characters"}}""",
                            ),
                        ],
                    ),
                ],
            ),
            ApiResponse(
                responseCode = "409",
                description = "User already exists",
                content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = ErrorResponse::class),
                        examples = [
                            ExampleObject(
                                name = "User Conflict",
                                value = """{"timestamp":"2025-10-01T10:30:00Z","status":409,"error":"Conflict","message":"User with this email or username already exists"}""",
                            ),
                        ],
                    ),
                ],
            ),
        ],
    )
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    suspend fun register(
        @Valid
        @RequestBody
        @Parameter(description = "User registration details", required = true)
        body: RegisterRequest,
    ): UserResponse =
        authService.register(body.email, body.username, body.password).toUserDto()
}
