package dev.denissajnar.chirp.infra.database.mappers

import dev.denissajnar.chirp.domain.model.User
import dev.denissajnar.chirp.infra.database.entities.UserEntity

fun UserEntity.toUser() =
    User(
        id = externalId,
        username = username,
        email = email,
        hasVerifiedEmail = hasVerifiedEmail,
    )
