package com.teamsparta.reviewers.domain.post.service

import com.teamsparta.reviewers.domain.post.dto.request.CreatePostRequest
import com.teamsparta.reviewers.domain.post.dto.response.PostResponse

interface PostService {

    fun createPost(request: CreatePostRequest) : PostResponse



}