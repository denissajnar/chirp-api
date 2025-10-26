package dev.denissajnar.chirp.domain.model

import dev.denissajnar.chirp.api.dto.UserResponse

data class AuthenticatedUserResponse(
    val user: UserResponse,
    val accessToken: String,
    val refreshToken: String,
)
