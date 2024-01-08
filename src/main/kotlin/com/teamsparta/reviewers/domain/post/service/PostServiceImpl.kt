package com.teamsparta.reviewers.domain.post.service

import com.teamsparta.reviewers.domain.post.dto.request.CreatePostRequest
import com.teamsparta.reviewers.domain.post.dto.response.PostResponse
import com.teamsparta.reviewers.domain.post.model.PostEntity
import com.teamsparta.reviewers.domain.post.repository.PostRepository

class PostServiceImpl(
    postRepository: PostRepository,
) : PostService {

    override fun createPost(
        request: CreatePostRequest
    ): PostResponse {
        return postRepository.save(
            PostEntity(
                title = request.title,
                thumbnailUrl = request.thumbnailUrl,
                content = request.content,
                likes = ?,
            )
        )
    }
}