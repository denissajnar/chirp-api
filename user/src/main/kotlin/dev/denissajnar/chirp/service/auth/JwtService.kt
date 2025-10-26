package dev.denissajnar.chirp.service.auth

import dev.denissajnar.chirp.domain.exception.InvalidTokenException
import dev.denissajnar.chirp.domain.model.UserId
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*
import kotlin.io.encoding.Base64

@Service
class JwtService(
    @param:Value($$"${jwt.secret}")
    private val secret: String,
    @param:Value($$"${jwt.expiration.minutes}")
    private val expirationInMinutes: Long,
) {

    private val secretKey = Keys.hmacShaKeyFor(Base64.decode(secret))
    private val accessTokenValidityMs = expirationInMinutes * 60 * 1000
    val refreshTokenValidityMs = 30 * 24 * 60 * 60 * 1000L

    fun generateAccessToken(userId: UserId): String =
        generateToken(userId, "access", accessTokenValidityMs)

    fun generateRefreshToken(userId: UserId): String =
        generateToken(userId, "refresh", refreshTokenValidityMs)

    fun validateAccessToken(token: String): Boolean {
        val claims = parseAllClaims(token) ?: return false
        val tokenType = claims["type"] as? String ?: return false

        return tokenType == "access"
    }

    fun validateRefreshToken(token: String): Boolean {
        val claims = parseAllClaims(token) ?: return false
        val tokenType = claims["type"] as? String ?: return false

        return tokenType == "refresh"
    }

    fun getUserIdFromToken(token: String): UserId {
        val claims =
            parseAllClaims(token) ?: throw InvalidTokenException(message = "The attached JWT token is not valid")

        return UUID.fromString(claims.subject).let(::UserId)
    }

    private fun generateToken(userId: UserId, type: String, expiry: Long) =
        Date().let { now ->
            Jwts.builder()
                .subject(userId.toString())
                .claim("type", type)
                .issuedAt(now)
                .expiration(Date(now.time + expiry))
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact()
        }

    private fun parseAllClaims(token: String) =
        runCatching {
            val rawToken = if (token.startsWith("Bearer ")) {
                token.removePrefix("Bearer ")
            } else token

            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(rawToken).payload
        }.getOrNull()


}
