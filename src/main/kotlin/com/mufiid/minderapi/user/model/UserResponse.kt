package com.mufiid.minderapi.user.model

import java.util.*

data class UserResponse(
    var id: String? = UUID.randomUUID().toString(),
    var firstName: String? = "",
    var lastName: String? = "",
    var createdAt: String? = "",
    var updatedAt: String? = "",
)
