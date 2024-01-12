package com.teamsparta.reviewers.domain.post.dto.response

data class AddLikeResponse(
    val postid : Long,
    val likes : Int,
)