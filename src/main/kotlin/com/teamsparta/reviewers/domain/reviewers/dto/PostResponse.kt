package com.teamsparta.reviewers.domain.reviewers.dto

data class PostResponse (
    val thumbnailUrl: String,
    val title: String,
    val content: String,
    val likes: Int,
)