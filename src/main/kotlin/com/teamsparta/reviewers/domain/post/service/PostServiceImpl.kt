package com.teamsparta.reviewers.domain.post.service

import com.teamsparta.reviewers.domain.exception.ModelNotFoundException
import com.teamsparta.reviewers.domain.post.dto.request.CreatePostRequest
import com.teamsparta.reviewers.domain.post.dto.request.UpdatePostRequest
import com.teamsparta.reviewers.domain.post.dto.response.PostResponse
import com.teamsparta.reviewers.domain.post.dto.response.AddLikeResponse
import com.teamsparta.reviewers.domain.post.model.LikeEntity
import com.teamsparta.reviewers.domain.post.model.PostEntity
import com.teamsparta.reviewers.domain.post.model.toAddLikeResponse
import com.teamsparta.reviewers.domain.post.model.toResponse
import com.teamsparta.reviewers.domain.post.repository.LikeRepository
import com.teamsparta.reviewers.domain.post.repository.PostRepository
import com.teamsparta.reviewers.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PostServiceImpl(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository,
    private val likeRepository: LikeRepository,
) : PostService {

    override fun createPost(request: CreatePostRequest): PostResponse {
        return postRepository.save(
            PostEntity(
                title = request.title,
                thumbnailUrl = request.thumbnailUrl,
                content = request.content,
                likes = 0
            )
        ).toResponse()
    }

    override fun getPostList(): List<PostResponse> {
        return postRepository.findAll().map { it.toResponse() }
    }

    override fun getPostById(postId: Long): PostResponse {
        val post = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException ("Post", postId)
        return post.toResponse()
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

    override fun addLike(userId: Long, postId: Long): AddLikeResponse {
        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException ("User", userId)
        val post = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException ("Post", postId)

        if (likeRepository.existsByUserAndPost(user, post)) {
            throw IllegalArgumentException ("따봉은 계정 당 1회만 가능합니다.")
        }
        post.likes += 1
        likeRepository.save(LikeEntity(user, post))

        return postRepository.save(post).toAddLikeResponse()
    }
}