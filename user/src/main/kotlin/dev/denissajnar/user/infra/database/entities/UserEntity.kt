package dev.denissajnar.user.infra.database.entities

import dev.denissajnar.user.domain.model.UserId
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Table

@Table(
    name = "users",
    schema = "user_service",
)
data class UserEntity(
    @Id
    val id: Long? = null,
    val uuid: UserId? = null,
    val username: String,
    val hashedPassword: String,
    val email: String,
    val hasVerifiedEmail: Boolean = false,
    @CreatedDate
    val createdAt: Long = System.currentTimeMillis(),
    @LastModifiedDate
    val updatedAt: Long = System.currentTimeMillis(),
    @Version
    val version: Long = 0L,
)
