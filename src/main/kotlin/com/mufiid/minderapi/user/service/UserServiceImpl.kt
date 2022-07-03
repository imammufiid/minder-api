package com.mufiid.minderapi.user.service

import com.mufiid.minderapi.user.model.*
import com.mufiid.minderapi.user.repository.UserRepository
import com.mufiid.minderapi.utils.MinderException
import com.mufiid.minderapi.utils.ValidationUtils
import com.mufiid.minderapi.utils.toResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    @Autowired
    private val userRepository: UserRepository,

    @Autowired
    private val validationUtils: ValidationUtils
) : UserService {

    override fun register(userRequest: UserRequest): Result<UserResponse> {
        validationUtils.validate(userRequest)

        val existingUser = getByUsername(userRequest.userName)
        if (existingUser.isSuccess) {
            throw MinderException("User is exist!")
        }

        val result =  userRepository.save(userRequest.createUser())
        return Mapper.mapEntityToResponse(result).toResult()
    }

    override fun getByUsername(username: String): Result<User> {
        return userRepository.findByUsername(username).toResult()
    }

    override fun update(id: String, userRequest: UserRequest): Result<UserResponse> {
        validationUtils.validate(userRequest)

        val existingUser = userRepository.findById(id).orElseThrow {
            MinderException("User not exist!")
        }

        existingUser.userName = userRequest.userName
        existingUser.firstName = userRequest.firstName
        existingUser.lastName = userRequest.lastName
        existingUser.updatedAt = System.currentTimeMillis().toString()

        val result =  userRepository.save(existingUser)
        return Mapper.mapEntityToResponse(result).toResult()
    }

    override fun login(userLoginRequest: UserLoginRequest): Result<LoginResponse> {
        validationUtils.validate(userLoginRequest)

        val existingUser = getByUsername(userLoginRequest.username)
        val result = existingUser.getOrNull()
            if (existingUser.isSuccess) {
            if (userLoginRequest.password == result?.password) {
                return LoginResponse(token = "my_token").toResult()
            } else {
                throw MinderException("Password invalid!")
            }
        } else {
            throw MinderException("User not found!")
        }
    }

    override fun getUsers(): Result<List<UserResponse>> {
        return userRepository.findAll().map {
            Mapper.mapEntityToResponse(it)
        }.toResult()
    }
}