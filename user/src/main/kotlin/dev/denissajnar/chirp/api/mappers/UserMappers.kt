package dev.denissajnar.chirp.api.mappers

import dev.denissajnar.chirp.api.dto.AuthenticatedUser
import dev.denissajnar.chirp.api.dto.UserDto
import dev.denissajnar.chirp.domain.model.AuthenticatedUserDto
import dev.denissajnar.chirp.domain.model.User

fun AuthenticatedUser.toAuthenticatedUserDto() =
    AuthenticatedUserDto(
        user = user.toUserDto(),
        accessToken = accessToken,
        refreshToken = refreshToken,
    )

fun User.toUserDto() =
    UserDto(
        id = id,
        email = email,
        username = username,
        hasVerifiedEmail = hasVerifiedEmail,
    )
