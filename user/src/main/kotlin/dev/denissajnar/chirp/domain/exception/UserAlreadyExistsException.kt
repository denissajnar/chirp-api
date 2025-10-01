package dev.denissajnar.chirp.domain.exception

class UserAlreadyExistsException : RuntimeException(
    "User with this email or username already exists",
)
