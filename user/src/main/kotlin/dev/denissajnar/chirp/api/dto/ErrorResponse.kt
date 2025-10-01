package dev.denissajnar.chirp.api.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.time.Instant

@Schema(description = "Error response")
data class ErrorResponse(

    @Schema(description = "Timestamp of when the error occurred")
    val timestamp: Instant,

    @Schema(description = "HTTP status code")
    val status: Int,

    @Schema(description = "Error type")
    val error: String,

    @Schema(description = "Detailed error message")
    val message: String,

    @Schema(description = "Field validation errors, if applicable")
    val errors: Map<String, String>? = null,
)
