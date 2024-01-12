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
import com.teamsparta.reviewers.domain.post.dto.request.UpdateCommentRequest


import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/reviewers/{postId}/comment")
class CommentController(
    private val commentService: CommentService

) {

    // 코멘트 작성
    @PostMapping()
    fun createComment(
        @PathVariable postId: Long, userId: Long,
        @RequestBody createCommentRequest: CreateCommentRequest
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentService.createComment(postId, userId, createCommentRequest))
    }



 @PutMapping("/{commentId}")
    fun updateComment(
     @PathVariable
     postId: Long,
     commentId: Long,
     userId: Long,
     @RequestBody updateCommentRequest: UpdateCommentRequest
   ): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.updateComment(postId, commentId, userId, updateCommentRequest))
   }


    @GetMapping() //한 포스트에 달린 전체 댓글 조회
     fun getCommentByPostId(
         @PathVariable
         postId: Long,
         commentId: Long,
         userId: Long
     ):ResponseEntity<List<CommentResponse>> {
         return ResponseEntity
             .status(HttpStatus.OK)
             .body(commentService.getCommentByPostId(postId))
     }
    @GetMapping("/{commentId}/all") // 댓글 따로 조회
    fun getCommentByCommentId(
        @PathVariable
        postId: Long,
        commentId: Long
    ): ResponseEntity<List<CommentResponse>>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.getCommentByCommentId(postId,commentId))
    }
}