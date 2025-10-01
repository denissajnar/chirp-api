package dev.denissajnar.chirp.api.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Pattern
import org.hibernate.validator.constraints.Length

@Schema(description = "Register request")
data class RegisterRequest(

    @field:Schema(description = "User email", example = "johndoe@example.com")
    @field:Email(message = "Email must be valid")
    val email: String,

    @field:Schema(description = "User username", example = "johndoe")
    @field:Length(min = 3, max = 20, message = "Username must be between 3 and 20 characters long")
    val username: String,

    @field:Schema(description = "User password", example = "AsecurePassword123!")
    @field:Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
        message = "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character",
    )
    val password: String,
)
