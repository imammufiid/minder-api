package com.mufiid.minderapi.user.controller

import com.mufiid.minderapi.base.BaseResponse
import com.mufiid.minderapi.user.model.*
import com.mufiid.minderapi.user.service.UserService
import com.mufiid.minderapi.utils.asResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class UserController {
    @Autowired
    private lateinit var userService: UserService

    @PostMapping("/register")
    fun register(
        @RequestBody userRequest: UserRequest
    ): BaseResponse<UserResponse> {
        return userService.register(userRequest).asResponse()
    }

    @PostMapping("/login")
    fun login(
        @RequestBody loginRequest: UserLoginRequest
    ): BaseResponse<LoginResponse> {
        return userService.login(loginRequest).asResponse()
    }

    @PutMapping
    fun update(
        @RequestBody userRequest: UserRequest
    ): BaseResponse<UserResponse> {
        val userId = SecurityContextHolder.getContext().authentication.principal as String
        return userService.update(userId, userRequest).asResponse()
    }

    @GetMapping
    fun getAllUsers(): BaseResponse<List<UserResponse>> {
        return userService.getUsers().asResponse()
    }

    @GetMapping("/nearest")
    fun getNearestUsers(): BaseResponse<List<UserResponse>> {
        return userService.getNearestUser().asResponse()
    }

    @PostMapping("/like")
    fun likeUser(
        @RequestBody userLikedRequest: UserLikedRequest
    ): BaseResponse<UserLikedResponse> {
        val userId = SecurityContextHolder.getContext().authentication.principal as String
        return userService.setLikedUser(userId, userLikedRequest).asResponse()
    }
}