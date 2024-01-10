package com.teamsparta.reviewers.domain.post.service

import com.teamsparta.reviewers.domain.post.dto.request.CreatePostRequest
import com.teamsparta.reviewers.domain.post.dto.request.UpdateCommentRequest
import com.teamsparta.reviewers.domain.post.dto.request.UpdatePostRequest
import com.teamsparta.reviewers.domain.post.dto.response.CommentResponse
import com.teamsparta.reviewers.domain.post.dto.response.PostResponse

interface PostService {

    fun getPostList() : List<PostResponse>

    fun createPost(request: CreatePostRequest) : PostResponse

    fun updatePost(postId:Long, request: UpdatePostRequest) : PostResponse

    fun deletePost(postId:Long)

}