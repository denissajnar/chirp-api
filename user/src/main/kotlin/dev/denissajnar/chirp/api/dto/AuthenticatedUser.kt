package dev.denissajnar.chirp.api.dto

import dev.denissajnar.chirp.domain.model.User

data class AuthenticatedUser(
    val user: User,
    val accessToken: String,
    val refreshToken: String,
)
