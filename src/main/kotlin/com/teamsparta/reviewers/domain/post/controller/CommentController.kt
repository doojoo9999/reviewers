package com.teamsparta.reviewers.domain.post.controller

import com.teamsparta.reviewers.domain.post.dto.request.CreateCommentRequest
import com.teamsparta.reviewers.domain.post.dto.response.CommentResponse
import com.teamsparta.reviewers.domain.post.service.CommentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.teamsparta.reviewers.domain.post.dto.request.CreatePostRequest
import com.teamsparta.reviewers.domain.post.service.PostService


import org.springframework.web.bind.annotation.*

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
class CommentController<UpdateCommentRequest>(
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