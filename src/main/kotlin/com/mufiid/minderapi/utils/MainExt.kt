package com.mufiid.minderapi.utils

import com.mufiid.minderapi.base.BaseResponse

inline fun <reified T> T?.toResult(
    message: String = "${T::class.simpleName} is null"
): Result<T> {
    return if (this != null) {
        Result.success(this)
    } else {
        Result.failure(MinderException(message))
    }
}

fun <T> Result<T>.asResponse(): BaseResponse<T> {
    return if (this.isFailure) {
        throw MinderException(this.exceptionOrNull()?.message ?: "Failure")
    } else {
        BaseResponse.success(this.getOrNull())
    }
}