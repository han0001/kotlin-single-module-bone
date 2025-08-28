package com.han.bone.config

import com.han.bone.common.response.ApiResponse
import org.apache.coyote.BadRequestException
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(ex: NoSuchElementException): ApiResponse<Nothing> {

        return ApiResponse.failure(
            message = ex.message ?: "Resource not found",
            status = NOT_FOUND.value()
        )
    }


    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(ex: IllegalArgumentException): ApiResponse<Nothing> {

        return ApiResponse.failure(
            message = ex.message ?: "Resource not found",
            status = BAD_REQUEST.value()
        )
    }
}