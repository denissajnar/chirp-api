package dev.denissajnar.chirp.infra.database.entities

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant

@Table(name = "refresh_tokens", schema = "user_service")
data class RefreshTokenEntity(
    @Id
    val id: Long? = null,
    val userId: Long,
    val expiresAt: Instant,
    val hashedToken: String,
    @CreatedDate
    val createdAt: Instant? = null,
    @Version
    val version: Long = 0L,
)
