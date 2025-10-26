package dev.denissajnar.chirp.api.exception.handler

import dev.denissajnar.chirp.api.dto.ErrorResponse
import dev.denissajnar.chirp.domain.exception.InvalidCredentialsException
import dev.denissajnar.chirp.domain.exception.InvalidTokenException
import dev.denissajnar.chirp.domain.exception.UserAlreadyExistsException
import dev.denissajnar.chirp.domain.exception.UserNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.bind.support.WebExchangeBindException
import java.time.Instant

@RestControllerAdvice
class AuthExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun onUserAlreadyExists(e: UserAlreadyExistsException) =
        ErrorResponse(
            timestamp = Instant.now(),
            status = HttpStatus.BAD_REQUEST.value(),
            code = "USER_EXISTS",
            message = e.message ?: "User already exists",
        )

    @ExceptionHandler(UserNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun onUserNotFound(e: UserNotFoundException) =
        ErrorResponse(
            timestamp = Instant.now(),
            status = HttpStatus.NOT_FOUND.value(),
            code = "USER_NOT_FOUND",
            message = e.message ?: "User not found",
        )

    @ExceptionHandler(InvalidCredentialsException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun onInvalidToken(e: InvalidCredentialsException) =
        ErrorResponse(
            timestamp = Instant.now(),
            status = HttpStatus.UNAUTHORIZED.value(),
            code = "INVALID_CREDENTIALS",
            message = e.message ?: "Invalid credentials",
        )

    @ExceptionHandler(InvalidTokenException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun onInvalidToken(e: InvalidTokenException) =
        ErrorResponse(
            timestamp = Instant.now(),
            status = HttpStatus.UNAUTHORIZED.value(),
            code = "INVALID_TOKEN",
            message = e.message ?: "Invalid token",
        )

    @ExceptionHandler(WebExchangeBindException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun onValidationException(e: WebExchangeBindException) =
        e.bindingResult.fieldErrors
            .associateBy({ it.field }, { it.defaultMessage ?: "Invalid value" }).let { errors ->
                ErrorResponse(
                    timestamp = Instant.now(),
                    status = HttpStatus.BAD_REQUEST.value(),
                    code = "VALIDATION_ERROR",
                    message = "Request validation failed",
                    errors = errors,
                )
            }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun onGeneralException(e: Exception) =
        ErrorResponse(
            timestamp = Instant.now(),
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            code = "GENERAL_ERROR",
            message = e.message ?: "Something went wrong",
        )
}
