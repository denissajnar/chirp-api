package dev.denissajnar.chirp.service.auth

import dev.denissajnar.chirp.domain.exception.UserAlreadyExistsException
import dev.denissajnar.chirp.domain.model.User
import dev.denissajnar.chirp.infra.database.entities.UserEntity
import dev.denissajnar.chirp.infra.database.mappers.toUser
import dev.denissajnar.chirp.infra.database.repository.UserRepository
import dev.denissajnar.chirp.infra.security.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) {

    suspend fun register(email: String, username: String, password: String): User {
        val trimmedEmail = email.trim()
        val trimmedUsername = username.trim()

        userRepository.findByEmailOrUsername(trimmedEmail, trimmedUsername)
            ?: throw UserAlreadyExistsException()

        val hashedPassword = passwordEncoder.encode(password)

        val newUser = UserEntity(
            username = trimmedUsername,
            email = trimmedEmail,
            hashedPassword = hashedPassword ?: error("Password hash is null"),
        )

        return userRepository.save(newUser).toUser()
    }
}
