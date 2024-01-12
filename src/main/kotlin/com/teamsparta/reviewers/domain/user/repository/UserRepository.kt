package com.teamsparta.reviewers.domain.user.repository

import com.teamsparta.reviewers.domain.user.common.UserRole
import com.teamsparta.reviewers.domain.user.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository <UserEntity, Long> {
    fun findByEmail(email: String): UserEntity?
//    fun findByRole(type: UserRole): List<UserEntity>
}