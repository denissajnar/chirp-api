package dev.denissajnar.chirp.domain.model

import dev.denissajnar.chirp.api.dto.UserDto

data class AuthenticatedUserDto(
    val user: UserDto,
    val accessToken: String,
    val refreshToken: String,
)
