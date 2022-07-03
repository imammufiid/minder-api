package com.mufiid.minderapi.user.model

object Mapper {
    fun mapEntityToResponse(user: User): UserResponse {
        return UserResponse(
            id = user.id,
            firstName = user.firstName,
            lastName = user.lastName,
            createdAt = user.createdAt,
            updatedAt = user.updatedAt
        )
    }
}