package com.teamsparta.reviewers.domain.post.service

import com.teamsparta.reviewers.domain.exception.ModelNotFoundException
import com.teamsparta.reviewers.domain.post.dto.request.CreatePostRequest
import com.teamsparta.reviewers.domain.post.dto.request.UpdatePostRequest
import com.teamsparta.reviewers.domain.post.dto.response.PostResponse
import com.teamsparta.reviewers.domain.post.model.PostEntity
import com.teamsparta.reviewers.domain.post.model.toResponse
import com.teamsparta.reviewers.domain.post.repository.PostRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PostServiceImpl(
    private val postRepository: PostRepository,
) : PostService {

    override fun createPost(request: CreatePostRequest): PostResponse {
        return postRepository.save(
            PostEntity(
                title = request.title,
                thumbnailUrl = request.thumbnailUrl,
                content = request.content,
                likes = null,
            )
        ).toResponse()
    }

    override fun getPostList(): List<PostResponse> {
        return postRepository.findAll().map { it.toResponse() }
    }

    override fun updatePost(postId: Long, request: UpdatePostRequest): PostResponse {
        val post = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException ("Post", postId)
        val (thumbnailUrl, title, content) = request

        post.title = title
        post.content = content
        post.thumbnailUrl = thumbnailUrl

        return postRepository.save(post).toResponse()

    }

    override fun deletePost(postId: Long) {
        val post = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException ("Post", postId)
        postRepository.delete(post)
    }
}