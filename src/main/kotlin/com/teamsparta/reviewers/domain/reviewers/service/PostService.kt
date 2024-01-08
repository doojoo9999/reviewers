package com.teamsparta.reviewers.domain.reviewers.service

import com.teamsparta.reviewers.domain.reviewers.dto.CreatePostRequest
import com.teamsparta.reviewers.domain.reviewers.dto.PostResponse

interface PostService {

    fun createPost(request: CreatePostRequest) : PostResponse



}