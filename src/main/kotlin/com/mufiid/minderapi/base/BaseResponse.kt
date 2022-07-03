package com.mufiid.minderapi.base

data class BaseResponse<T>(
    var status: Boolean = true,
    var message: String = "Success",
    val data: T? = null
) {
    companion object {
        fun <T> success(data: T?): BaseResponse<T> {
            return BaseResponse(data = data)
        }

        fun <T> failure(message: String): BaseResponse<T> {
            return BaseResponse(
                status = false, message = message)
        }
    }
}
