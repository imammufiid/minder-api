package com.mufiid.minderapi.user.service

import com.mufiid.minderapi.user.model.UserRequest
import com.mufiid.minderapi.user.model.UserResponse

interface UserService {

    fun create(userRequest: UserRequest): Result<UserResponse>
}