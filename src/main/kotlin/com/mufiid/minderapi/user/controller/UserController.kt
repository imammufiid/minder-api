package com.mufiid.minderapi.user.controller

import com.mufiid.minderapi.base.BaseResponse
import com.mufiid.minderapi.user.model.UserRequest
import com.mufiid.minderapi.user.model.UserResponse
import com.mufiid.minderapi.user.service.UserService
import com.mufiid.minderapi.utils.asResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/users")
class UserController {
    @Autowired
    private lateinit var userService: UserService

    @PostMapping
    fun createUser(
        @RequestBody userRequest: UserRequest
    ): BaseResponse<UserResponse> {
        val result = userService.create(userRequest)
        return result.asResponse()
    }
}