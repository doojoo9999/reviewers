package com.teamsparta.reviewers.domain.reviewers.repository

import com.teamsparta.reviewers.domain.reviewers.model.PostEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository <PostEntity, Long>{
}