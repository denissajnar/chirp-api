package dev.denissajnar.chirp.infra.security

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class PasswordEncoder {

    private val bcrypt = BCryptPasswordEncoder()

    fun encode(rawPassword: String) = bcrypt.encode(rawPassword)

    fun matches(rawPassword: String, hashedPassword: String) = bcrypt.matches(rawPassword, hashedPassword)
}
