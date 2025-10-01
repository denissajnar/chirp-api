package dev.denissajnar.chirp.infra.database.entities

import dev.denissajnar.chirp.domain.model.UserId
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant

@Table(name = "users", schema = "user_service")
data class UserEntity(
    @Id
    val id: Long? = null,
    val externalId: UserId? = null,
    val username: String,
    val hashedPassword: String,
    val email: String,
    val hasVerifiedEmail: Boolean = false,
    @CreatedDate
    val createdAt: Instant = Instant.now(),
    @LastModifiedDate
    val updatedAt: Instant = Instant.now(),
    @Version
    val version: Long = 0L,
)
