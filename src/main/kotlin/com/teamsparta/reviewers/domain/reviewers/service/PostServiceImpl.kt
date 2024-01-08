package com.teamsparta.reviewers.domain.reviewers.service

import com.teamsparta.reviewers.domain.reviewers.dto.CreatePostRequest
import com.teamsparta.reviewers.domain.reviewers.dto.PostResponse
import com.teamsparta.reviewers.domain.reviewers.model.PostEntity
import com.teamsparta.reviewers.domain.reviewers.repository.PostRepository

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