package com.teamsparta.reviewers.domain.user.repository

import com.teamsparta.reviewers.domain.user.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository <UserEntity, Long> {
    fun findByUserid(userid: Long): UserEntity?

    fun findByEmail(email: String): UserEntity?


}