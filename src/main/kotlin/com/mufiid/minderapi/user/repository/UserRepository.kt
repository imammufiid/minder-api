package com.mufiid.minderapi.user.repository

import com.mufiid.minderapi.user.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface UserRepository : JpaRepository<User, String> {
    @Query(value = "SELECT * FROM users WHERE username = :username LIMIT 1", nativeQuery = true)
    fun findByUsername(@Param("username") username: String?): User?
}