package com.han.bone.common.response

import org.springframework.http.HttpStatus

data class ApiResponse<T>(
    val success: Boolean,
    val status: Int,
    val data: T? = null,
    val message: String? = null,
) {

    companion object {

        fun <T> success(body: T) = ApiResponse(
            success = true,
            status = HttpStatus.OK.value(),
            data = body,
            message = null
        )


        fun failure(message: String, status: Int) = ApiResponse(
            success = false,
            status = status,
            data = null,
            message = message
        )

    }

}
