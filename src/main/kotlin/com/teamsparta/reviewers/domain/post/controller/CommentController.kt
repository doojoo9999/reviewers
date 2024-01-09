package com.teamsparta.reviewers.domain.post.controller

import com.teamsparta.reviewers.domain.post.dto.request.CreatePostRequest
import com.teamsparta.reviewers.domain.post.dto.request.UpdateCommentRequest
import com.teamsparta.reviewers.domain.post.dto.response.CommentResponse
import com.teamsparta.reviewers.domain.post.service.PostService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/reviewers/{postId}/comment")
class CommentController(
    private val cardService: PostService
) {

    @PostMapping
    fun createComment(@PathVariable postId: Long,
                   @RequestBody createCommentRequest: CreatePostRequest
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(cardService.createComment(postId, createCommentRequest))
    }

    @PutMapping("/{commentId}")
    fun updateComment(@PathVariable postId: Long,
                      @PathVariable commentId: Long,
                      @RequestBody updateCommentRequest: UpdateCommentRequest
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(cardService.updateComment(postId, commentId, updateCommentRequest))
    }


}