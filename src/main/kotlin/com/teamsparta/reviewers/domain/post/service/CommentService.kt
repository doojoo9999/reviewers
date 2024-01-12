package com.teamsparta.reviewers.domain.post.service

import com.teamsparta.reviewers.domain.post.dto.request.CreateCommentRequest
import com.teamsparta.reviewers.domain.post.dto.request.DeleteCommentRequest
import com.teamsparta.reviewers.domain.post.dto.request.UpdateCommentRequest
import com.teamsparta.reviewers.domain.post.dto.response.CommentResponse

interface CommentService {

    fun createComment(postId: Long, userId: Long, request: CreateCommentRequest): CommentResponse

    fun deleteComment(postId : Long, commentId : Long, userId: Long, request: DeleteCommentRequest) : CommentResponse

    fun updateComment(postId: Long, commentId: Long, userId: Long, request: UpdateCommentRequest): CommentResponse

    fun getCommentByPostId(postId: Long,commentId: Long): List<CommentResponse>
    fun getCommentByCommentId(commentId: Long): List<CommentResponse>
    fun getCommentByUserId(userId: Long): List<CommentResponse>
}