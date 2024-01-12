package com.teamsparta.reviewers.domain.post.service

import com.teamsparta.reviewers.domain.post.dto.request.*
import com.teamsparta.reviewers.domain.post.dto.response.CommentReplyResponse
import com.teamsparta.reviewers.domain.post.dto.response.CommentResponse

interface CommentService {

    fun createComment(postId: Long, userId: Long, request: CreateCommentRequest): CommentResponse

    fun deleteComment(postId : Long, commentId : Long, userId: Long, request: DeleteCommentRequest) : CommentResponse

    fun createReply(postId: Long, userId: Long, parentcommentId : Long, request : CreateReplyRequest) : CommentReplyResponse

    fun updateReply(postId: Long, userId: Long, parentcommentId: Long, request : UpdateReplyRequest) : CommentReplyResponse

    fun deleteReply(postId: Long, userId: Long, parentcommentId: Long, request : DeleteReplyRequest)
}