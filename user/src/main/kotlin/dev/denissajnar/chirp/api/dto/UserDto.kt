package dev.denissajnar.chirp.api.dto

import dev.denissajnar.chirp.domain.model.UserId

data class UserDto(
    val id: UserId,
    val email: String,
    val username: String,
    val hasVerifiedEmail: Boolean,
)
