package com.mufiid.minderapi.user.service

import com.mufiid.minderapi.user.model.*

interface UserService {

    fun register(userRequest: UserRequest): Result<UserResponse>
    fun getByUsername(username: String): Result<User>
    fun update(id: String, userRequest: UserRequest): Result<UserResponse>

    fun login(userLoginRequest: UserLoginRequest): Result<LoginResponse>

    fun getUsers(): Result<List<UserResponse>>
}