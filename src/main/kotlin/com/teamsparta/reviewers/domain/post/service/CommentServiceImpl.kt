package com.teamsparta.reviewers.domain.post.service

import com.teamsparta.reviewers.domain.exception.ModelNotFoundException
import com.teamsparta.reviewers.domain.post.dto.request.CreateCommentRequest
import com.teamsparta.reviewers.domain.post.dto.request.UpdateCommentRequest
import com.teamsparta.reviewers.domain.post.dto.response.CommentResponse
import com.teamsparta.reviewers.domain.post.model.CommentEntity
import com.teamsparta.reviewers.domain.post.model.toResponse
import com.teamsparta.reviewers.domain.post.repository.CommentRepository
import com.teamsparta.reviewers.domain.post.repository.PostRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentServiceImpl (
    private val postRepository: PostRepository,
    private val commentRepository: CommentRepository
) : CommentService {

    @Transactional
    override fun createComment(
        postId: Long,
        request: CreateCommentRequest
    ): CommentResponse {
        val post = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("Post", postId)
        return commentRepository.save(
            CommentEntity(
                content = request.content,
                userId = request.userId,
                userName = request.userName,
                post = post
            )
        ).toResponse()
    }

    @Transactional
    override fun updateComment(postId: Long, commentId: Long, request: UpdateCommentRequest): CommentResponse {
        val card = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("Card", postId)
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment", commentId)
    //비밀번호 검증

        return comment.toResponse()
    }
}