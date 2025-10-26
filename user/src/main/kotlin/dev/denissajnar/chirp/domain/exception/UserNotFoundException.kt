package dev.denissajnar.chirp.domain.exception

class UserNotFoundException : RuntimeException(
    "User not found",
)
