package com.mufiid.minderapi.user.repository

import com.mufiid.minderapi.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, String> {
}