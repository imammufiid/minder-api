package com.mufiid.minderapi.user.model

import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

data class UserRequest(

    @field:NotBlank
    val userName: String = "",

    @field:NotBlank
    val firstName: String = "",

    @field:NotBlank
    val lastName: String = "",

    @field:NotBlank
    @field:Min(value = 8)
    val password: String = ""
) {
    fun createUser(): User {
        return User(
            userName = userName,
            firstName = firstName,
            lastName = lastName,
            password = password,
            createdAt = System.currentTimeMillis().toString()
        )
    }
}
