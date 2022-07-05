package com.mufiid.minderapi.user.repository

import com.mufiid.minderapi.user.model.UserLiked
import org.springframework.data.jpa.repository.JpaRepository

interface UserLikedRepository: JpaRepository<UserLiked, String> {
}