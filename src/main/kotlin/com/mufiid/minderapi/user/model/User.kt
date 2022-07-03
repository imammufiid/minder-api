package com.mufiid.minderapi.user.model

import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
data class User(
    @Id
    var id: String? = UUID.randomUUID().toString(),

    @Column(name = "first_name")
    var firstName: String? = "",

    @Column(name = "last_name")
    var lastName: String? = "",

    @Column(name = "password")
    var password: String? = "",

    @Column(name = "created_at")
    var createdAt: String? = "",

    @Column(name = "updated_at")
    var updatedAt: String? = "",

)
