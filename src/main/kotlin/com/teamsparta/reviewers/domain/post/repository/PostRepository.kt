package com.teamsparta.reviewers.domain.post.repository

import com.teamsparta.reviewers.domain.post.model.PostEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository <PostEntity, Long>{
}