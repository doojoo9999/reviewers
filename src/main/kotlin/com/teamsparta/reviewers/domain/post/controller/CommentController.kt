package com.teamsparta.reviewers.domain.post.controller

import com.teamsparta.reviewers.domain.post.dto.request.CreateCommentRequest
import com.teamsparta.reviewers.domain.post.dto.request.CreateReplyRequest
import com.teamsparta.reviewers.domain.post.dto.request.DeleteReplyRequest
import com.teamsparta.reviewers.domain.post.dto.request.UpdateReplyRequest
import com.teamsparta.reviewers.domain.post.dto.response.CommentReplyResponse
import com.teamsparta.reviewers.domain.post.dto.response.CommentResponse
import com.teamsparta.reviewers.domain.post.service.CommentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/reviewers/{postId}/comment")
class CommentController(
    private val commentService: CommentService
) {

    @PostMapping()
    fun createComment(
        @PathVariable postId: Long, userId: Long,
        @RequestBody createCommentRequest: CreateCommentRequest
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentService.createComment(postId, userId, createCommentRequest))
    }

    @PostMapping("/{commentId}/reply") // 대댓글 작성
    fun createReply(
        @PathVariable postId: Long,
        userId: Long,
        parentCommentId: Long,
        @RequestBody createReplyRequest: CreateReplyRequest
    ): ResponseEntity<CommentReplyResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentService.createReply(postId, userId, parentCommentId, createReplyRequest))
    }

    @PutMapping("/{commentId}/reply") // 대댓글 수정
    fun updateReply(
        @PathVariable postId: Long,
        userId: Long,
        parentCommentId: Long,
        @RequestBody updateReplyRequest: UpdateReplyRequest
    ): ResponseEntity<CommentReplyResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.updateReply(postId, userId, parentCommentId, updateReplyRequest))
    }

    @DeleteMapping("/{commentId}/reply") // 대댓글 삭제
    fun deleteReply(
        @PathVariable postId: Long,
        userId: Long,
        parentCommentId: Long,
        @RequestBody deleteReplyRequest: DeleteReplyRequest
    ): ResponseEntity<CommentReplyResponse> {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
}
