package com.teamsparta.reviewers.domain.post.repository

import com.teamsparta.reviewers.domain.post.model.LikeEntity
import com.teamsparta.reviewers.domain.post.model.PostEntity
import com.teamsparta.reviewers.domain.user.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface LikeRepository : JpaRepository <LikeEntity, Long> {
    fun existsByEmailAndPost(email: UserEntity, post: PostEntity) : Boolean

}