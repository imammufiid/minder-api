package com.mufiid.minderapi.utils

import com.mufiid.minderapi.base.BaseResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import javax.validation.ConstraintViolationException


class MinderException(message: String): Throwable(message)

@ControllerAdvice
class MinderExceptionHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [MinderException::class])
    fun handleThrowable(throwable: MinderException): ResponseEntity<BaseResponse<Empty>> {
        return ResponseEntity(BaseResponse.failure(throwable.message ?: "Failure"), HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun handleThrowableConstraintViolation(throwable: ConstraintViolationException): ResponseEntity<BaseResponse<Empty>> {
        return ResponseEntity(BaseResponse.failure(throwable.message ?: "Bad Request!"), HttpStatus.BAD_REQUEST)
    }
}

object Empty