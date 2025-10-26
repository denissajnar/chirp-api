package dev.denissajnar.chirp.infra.database.repository

import dev.denissajnar.chirp.infra.database.entities.RefreshTokenEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RefreshTokenRepository : CoroutineCrudRepository<RefreshTokenEntity, Long> {
    suspend fun findByUserIdAndHashedToken(userId: Long, hashedToken: String): RefreshTokenEntity?
    suspend fun deleteByUserIdAndHashedToken(userId: Long, hashedToken: String)
    suspend fun deleteByUserId(userId: Long)
}
