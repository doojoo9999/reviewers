package com.teamsparta.reviewers.domain.post.dto.response

import com.teamsparta.reviewers.domain.user.model.UserEntity

data class AddLikeResponse(
    val email : String,
    val postid : Long,
    val likes : Int,
)