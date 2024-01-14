package com.teamsparta.reviewers.domain.post.repository

import com.teamsparta.reviewers.domain.post.model.PostEntity
import com.teamsparta.reviewers.domain.user.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository <PostEntity, Long>{

    fun findByLikes(email: UserEntity): List<PostEntity>

}