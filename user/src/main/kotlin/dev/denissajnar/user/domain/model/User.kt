package dev.denissajnar.user.domain.model

data class User(
    val id: UserId,
    val username: String,
    val email: String,
    val hasVerifiedEmail: Boolean = false,
)
