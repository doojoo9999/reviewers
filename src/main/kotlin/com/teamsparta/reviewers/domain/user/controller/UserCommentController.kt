package com.teamsparta.reviewers.domain.user.controller

import com.teamsparta.reviewers.domain.post.dto.response.CommentResponse
import com.teamsparta.reviewers.domain.post.service.CommentServiceImpl
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
@RestController
class UserCommentController(
    private val commentService: CommentServiceImpl) {
    @GetMapping("/reviewers/{postId}/comment/{commentId}/user/{userId}") // 유저별 댓글 조회
    fun getCommentByUserId(
        @PathVariable
        postId: Long,
        commentId: Long,
        userId: Long,
    ): ResponseEntity<List<CommentResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.getCommentByUserId(userId))
    }
}