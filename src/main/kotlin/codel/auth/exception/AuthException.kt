package codel.auth.exception

import org.springframework.http.HttpStatus

class AuthException(
    val statusCode: HttpStatus,
    override val message: String,
    override val cause: Throwable? = null,
) : RuntimeException(message, cause)
