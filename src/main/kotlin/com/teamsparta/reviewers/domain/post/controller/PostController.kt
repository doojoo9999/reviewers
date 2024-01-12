package com.teamsparta.reviewers.domain.post.controller

import com.teamsparta.reviewers.domain.post.dto.request.CreatePostRequest
import com.teamsparta.reviewers.domain.post.dto.request.UpdatePostRequest
import com.teamsparta.reviewers.domain.post.dto.response.PostResponse
import com.teamsparta.reviewers.domain.post.service.PostService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/reviewers")
@RestController
class PostController(
    private val postService: PostService
) {
    @GetMapping()
    fun getPostList() : ResponseEntity<List<PostResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postService.getPostList())
    }

    @PostMapping()
    fun createPost(@RequestBody createPostRequest : CreatePostRequest ) : ResponseEntity<PostResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(postService.createPost(createPostRequest))
    }

    @PutMapping("/{postId}")
    fun updatePost(
        @PathVariable postId : Long,
        @RequestBody updatePostRequest : UpdatePostRequest
    ) : ResponseEntity<PostResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postService.updatePost(postId, updatePostRequest))
    }

    @DeleteMapping("/{postId}")
    fun deletePost(
        @PathVariable postId: Long
    ) : ResponseEntity<PostResponse> {
        postService.deletePost(postId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }

}