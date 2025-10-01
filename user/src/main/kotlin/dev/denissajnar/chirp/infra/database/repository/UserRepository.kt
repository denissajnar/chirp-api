package dev.denissajnar.chirp.infra.database.repository

import dev.denissajnar.chirp.infra.database.entities.UserEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CoroutineCrudRepository<UserEntity, Long> {
    suspend fun findByEmail(email: String): UserEntity?
    suspend fun findByEmailOrUsername(email: String, username: String): UserEntity?
}
