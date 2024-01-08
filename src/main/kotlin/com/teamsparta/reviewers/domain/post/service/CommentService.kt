package com.teamsparta.reviewers.domain.post.service

import com.teamsparta.reviewers.domain.post.dto.request.CreateCommentRequest
import com.teamsparta.reviewers.domain.post.dto.request.DeleteCommentRequest
import com.teamsparta.reviewers.domain.post.dto.response.CommentResponse

interface CommentService {

    fun createComment(postId: Long, request: CreateCommentRequest): CommentResponse

    fun deleteComment(postId : Long, commentId : Long, request: DeleteCommentRequest) : CommentResponse
}