package dev.denissajnar.chirp.service.auth

import dev.denissajnar.chirp.api.dto.AuthenticatedUser
import dev.denissajnar.chirp.domain.exception.InvalidCredentialsException
import dev.denissajnar.chirp.domain.exception.UserAlreadyExistsException
import dev.denissajnar.chirp.domain.exception.UserNotFoundException
import dev.denissajnar.chirp.domain.model.User
import dev.denissajnar.chirp.infra.database.entities.RefreshTokenEntity
import dev.denissajnar.chirp.infra.database.entities.UserEntity
import dev.denissajnar.chirp.infra.database.mappers.toUser
import dev.denissajnar.chirp.infra.database.repository.RefreshTokenRepository
import dev.denissajnar.chirp.infra.database.repository.UserRepository
import dev.denissajnar.chirp.infra.security.PasswordEncoder
import org.springframework.stereotype.Service
import java.security.MessageDigest
import java.time.Instant
import java.util.*

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService,
    private val refreshTokenRepository: RefreshTokenRepository,
) {

    suspend fun register(email: String, username: String, password: String): User {
        val trimmedEmail = email.trim()
        val trimmedUsername = username.trim()

        userRepository.findByEmailOrUsername(trimmedEmail, trimmedUsername)
            ?.let { throw UserAlreadyExistsException() }

        val hashedPassword = passwordEncoder.encode(password)!!

        val newUser = UserEntity(
            username = trimmedUsername,
            email = trimmedEmail,
            hashedPassword = hashedPassword,
        )

        return userRepository.save(newUser).toUser()
    }

    suspend fun login(email: String, password: String): AuthenticatedUser {
        val user = userRepository.findByEmail(email.trim()) ?: throw InvalidCredentialsException()

        if (!passwordEncoder.matches(password, user.hashedPassword)) {
            throw InvalidCredentialsException()
        }

        // TODO: Check for email verification

        return user.externalId.let { userId ->
            val accessToken = jwtService.generateAccessToken(userId)
            val refreshToken = jwtService.generateRefreshToken(userId)

            user.id?.let { storeRefreshToken(it, refreshToken) } ?: throw UserNotFoundException()

            AuthenticatedUser(
                user = user.toUser(),
                accessToken = accessToken,
                refreshToken = refreshToken,
            )
        }
    }

    private suspend fun storeRefreshToken(userId: Long, token: String) {
        val hashed = hashToken(token)
        val expiryMs = jwtService.refreshTokenValidityMs
        val expiresAt = Instant.now().plusMillis(expiryMs)

        refreshTokenRepository.save(RefreshTokenEntity(userId = userId, expiresAt = expiresAt, hashedToken = hashed))
    }

    private suspend fun hashToken(token: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hashBytes = digest.digest(token.encodeToByteArray())

        return Base64.getEncoder().encodeToString(hashBytes)
    }
}
