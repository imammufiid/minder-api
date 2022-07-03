package com.mufiid.minderapi.user.model

object Mapper {
    fun mapEntityToResponse(user: User): UserResponse {
        return UserResponse(
            id = user.id,
            username = user.userName,
            firstName = user.firstName,
            lastName = user.lastName,
            createdAt = user.createdAt,
            updatedAt = user.updatedAt
        )
    }
}