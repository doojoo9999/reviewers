package com.teamsparta.reviewers.domain.post.service

import com.teamsparta.reviewers.domain.post.dto.request.CreatePostRequest
import com.teamsparta.reviewers.domain.post.dto.request.UpdatePostRequest
import com.teamsparta.reviewers.domain.post.dto.response.PostResponse
import com.teamsparta.reviewers.domain.post.dto.response.AddLikeResponse
import com.teamsparta.reviewers.domain.user.model.UserEntity

interface PostService {

    fun getPostList() : List<PostResponse>

    fun createPost(request: CreatePostRequest) : PostResponse

    fun updatePost(postId:Long, request: UpdatePostRequest) : PostResponse

    fun deletePost(postId:Long)

    fun getPostById(postId:Long) : PostResponse

    fun addLike(email:String, postId:Long) : AddLikeResponse
}