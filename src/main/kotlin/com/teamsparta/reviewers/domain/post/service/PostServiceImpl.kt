package com.teamsparta.reviewers.domain.post.service

import com.teamsparta.reviewers.domain.exception.EmailNotFoundException
import com.teamsparta.reviewers.domain.exception.ModelNotFoundException
import com.teamsparta.reviewers.domain.post.dto.request.CreatePostRequest
import com.teamsparta.reviewers.domain.post.dto.request.GetPostByUseridRequest
import com.teamsparta.reviewers.domain.post.dto.request.UpdatePostRequest
import com.teamsparta.reviewers.domain.post.dto.response.AddLikeResponse
import com.teamsparta.reviewers.domain.post.dto.response.PostResponse
import com.teamsparta.reviewers.domain.post.model.PostEntity
import com.teamsparta.reviewers.domain.post.model.toAddLikeResponse
import com.teamsparta.reviewers.domain.post.model.toResponse
import com.teamsparta.reviewers.domain.post.repository.PostRepository
import com.teamsparta.reviewers.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class PostServiceImpl(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository,
) : PostService {

    override fun createPost(request: CreatePostRequest): PostResponse {

        val userid = userRepository.findByUserid(request.userid)
            ?: throw ModelNotFoundException("not found email", 1)

        return postRepository.save(
            PostEntity(
                title = request.title,
                thumbnailUrl = request.thumbnailUrl,
                content = request.content,
                likes = 0,
                user = userid
            )
        ).toResponse()
    }

    override fun getPostList(): List<PostResponse> {
        return postRepository.findAll().map { it.toResponse() }
    }

    override fun getPostsByUserId(userid: Long): List<PostResponse> {
        val user = userRepository.findByIdOrNull(userid) ?: throw ModelNotFoundException ("User", userid)
        val posts = postRepository.findByUser(user)
        return posts.map { it.toResponse() }
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

    override fun addLike(user: Long, postId: Long): AddLikeResponse {

        val authentication = SecurityContextHolder.getContext().authentication
        val userid = authentication.name

        if (user == null || user.toString() != userid) {
            throw IllegalArgumentException ("로그인이 필요합니다.")
        }

        val user = userRepository.findByUserid(user) ?: throw EmailNotFoundException("User", user)
        val post = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("Post", postId)

        if (user.likedPosts.contains(post)) {
            throw IllegalArgumentException ("따봉은 계정 당 1회만 가능합니다.")
        }
        post.likes += 1
        user.likedPosts.add(post)

        userRepository.save(user)
        return postRepository.save(post).toAddLikeResponse()
    }
}