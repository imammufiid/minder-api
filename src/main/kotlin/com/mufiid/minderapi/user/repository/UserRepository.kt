package com.mufiid.minderapi.user.repository

import com.mufiid.minderapi.user.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface UserRepository : JpaRepository<User, String> {
    @Query(value = "SELECT * FROM users WHERE username = :username", nativeQuery = true)
    fun findBy(@Param("username") username: String?): User?
}