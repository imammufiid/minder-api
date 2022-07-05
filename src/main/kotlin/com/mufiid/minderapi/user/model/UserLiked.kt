package com.mufiid.minderapi.user.model

import java.util.*
import javax.persistence.Column
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "users_liked")
data class UserLiked(
    @Id
    var id: String? = UUID.randomUUID().toString(),

    @Column(name = "user_id")
    var userId: String? = "",

    @Column(name = "user_id_liked")
    var userIdLiked: String? = "",

    @Column(name = "is_liked")
    var isLiked: Int? = 0,

    @Column(name = "created_at")
    var createdAt: String? = "",
)
