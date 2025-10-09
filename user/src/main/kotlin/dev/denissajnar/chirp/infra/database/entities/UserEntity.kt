package dev.denissajnar.chirp.infra.database.entities

import com.github.f4b6a3.uuid.UuidCreator
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
    val externalId: UserId = UserId(UuidCreator.getTimeOrderedEpoch()),
    val username: String,
    val hashedPassword: String,
    val email: String,
    val hasVerifiedEmail: Boolean = false,
    @CreatedDate
    val createdAt: Instant? = null,
    @LastModifiedDate
    val updatedAt: Instant? = null,
    @Version
    val version: Long = 0L,
)
