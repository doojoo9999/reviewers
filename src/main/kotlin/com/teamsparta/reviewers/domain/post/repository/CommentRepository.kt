package com.teamsparta.reviewers.domain.post.repository

import com.teamsparta.reviewers.domain.post.model.CommentEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository: JpaRepository <CommentEntity, Long> {
    fun findByCommentId (postId: Long): List<CommentEntity>
}