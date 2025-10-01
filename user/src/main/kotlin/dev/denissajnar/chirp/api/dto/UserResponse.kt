package dev.denissajnar.chirp.api.dto

import dev.denissajnar.chirp.domain.model.UserId
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "User response")
data class UserResponse(

    @Schema(description = "User ID", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    val id: UserId,

    @Schema(description = "User email", example = "johndoe@example.com")
    val email: String,

    @Schema(description = "User username", example = "johndoe")
    val username: String,

    @Schema(description = "User has verified email", example = "false")
    val hasVerifiedEmail: Boolean,
)
