package com.teamsparta.reviewers.domain.reviewers.dto

data class CreatePostRequest(
    val thumbnailUrl: String,
    val title: String,
    val content: String,
    val userId: Long,
    val userName: String,
)
