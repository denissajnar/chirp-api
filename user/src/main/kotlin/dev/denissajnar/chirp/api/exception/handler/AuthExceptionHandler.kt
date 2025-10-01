package dev.denissajnar.chirp.api.exception.handler

import dev.denissajnar.chirp.domain.exception.UserAlreadyExistsException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class AuthExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun onUserAlreadyExists(e: UserAlreadyExistsException) =
        mapOf(
            "code" to "USER_ALREADY_EXISTS",
            "message" to e.message,
        )

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun onValidationException(e: MethodArgumentNotValidException) =
        e.bindingResult.fieldErrors
            .associateBy({ it.field }, { it.defaultMessage ?: "Invalid value" })
}
