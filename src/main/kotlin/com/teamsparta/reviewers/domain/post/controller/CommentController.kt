package com.teamsparta.reviewers.domain.post.controller

import com.teamsparta.reviewers.domain.post.dto.request.*
import com.teamsparta.reviewers.domain.post.dto.response.CommentReplyResponse
import com.teamsparta.reviewers.domain.post.dto.response.CommentResponse
import com.teamsparta.reviewers.domain.post.dto.response.DeleteResponse
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


import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/posts/{postId}/comments")
class CommentController(
    private val commentService: CommentService

) {

    // 코멘트 작성
    @PostMapping()
    fun createComment(
        @PathVariable postId: Long,
        @RequestHeader("userId") userId: Long,
        @RequestBody createCommentRequest: CreateCommentRequest
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentService.createComment(postId, userId, createCommentRequest))
    }


 @PutMapping("/{commentId}")
    fun updateComment(
     @PathVariable postId: Long,
     @PathVariable commentId: Long,
     @RequestHeader("userId") userId: Long,
     @RequestBody updateCommentRequest: UpdateCommentRequest
   ): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.updateComment(postId, commentId, userId, updateCommentRequest))
   }

    @DeleteMapping("/{commentId}") // 댓글 삭제
    fun deleteComment(
        @PathVariable postId: Long,
        @PathVariable commentId: Long,
        @RequestHeader("userId") userId: Long,
        @RequestBody deleteCommentRequest: DeleteCommentRequest
    ): ResponseEntity<DeleteResponse> {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(commentService.deleteComment(postId, commentId, userId, deleteCommentRequest))
    }


    @GetMapping() //한 포스트에 달린 전체 댓글 조회
     fun getCommentByPostId(
         @PathVariable postId: Long
     ):ResponseEntity<List<CommentResponse>> {
         return ResponseEntity
             .status(HttpStatus.OK)
             .body(commentService.getCommentByPostId(postId))
     }
    @GetMapping("/{commentId}") // 댓글 따로 조회
    fun getRepliesByCommentId(
        @PathVariable postId: Long,
        @PathVariable commentId: Long
    ): ResponseEntity<List<CommentReplyResponse>>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.getRepliesByCommentId(postId,commentId))
    }

    @PostMapping("/{commentId}/replies") // 대댓글 작성
    fun createReply(
        @PathVariable postId: Long,
        @PathVariable commentId: Long,
        @RequestHeader("userId") userId: Long,
        @RequestBody createReplyRequest: CreateReplyRequest
    ): ResponseEntity<CommentReplyResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentService.createReply(postId, userId, commentId, createReplyRequest))
    }

//    @PutMapping("/{commentId}/replies/{replyId}") // 대댓글 수정
//    fun updateReply(
//        @PathVariable postId: Long,
//        @PathVariable commentId: Long,
//        @PathVariable replyId: Long,
//        @RequestBody updateReplyRequest: UpdateReplyRequest
//    ): ResponseEntity<CommentReplyResponse> {
//        return ResponseEntity
//            .status(HttpStatus.OK)
//            .body(commentService.updateReply(postId, commentId, replyId, updateReplyRequest))
//    }

//    @DeleteMapping("/{commentId}/replies/{replyId}") // 대댓글 삭제
//    fun deleteReply(
//        @PathVariable postId: Long,
//        @PathVariable commentId: Long,
//        @PathVariable replyId: Long,
//        @RequestHeader("userId") userId: Long,
//        @RequestBody deleteReplyRequest: DeleteReplyRequest
//    ): ResponseEntity<DeleteResponse> {
//        return ResponseEntity
//            .status(HttpStatus.NO_CONTENT)
//            .body(commentService.deleteReply(postId, commentId, replyId, deleteReplyRequest))
//    }

}
