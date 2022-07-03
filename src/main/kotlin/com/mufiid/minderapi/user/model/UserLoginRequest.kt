package com.mufiid.minderapi.user.model

import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

data class UserLoginRequest(
    @field:NotBlank
    val username: String = "",

    @field:NotBlank
    @field:Min(value = 8)
    val password: String = ""
)
