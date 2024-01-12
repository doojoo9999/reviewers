package com.teamsparta.reviewers.domain.post.service

import com.teamsparta.reviewers.domain.exception.IdNotMatchException
import com.teamsparta.reviewers.domain.exception.ModelNotFoundException
import com.teamsparta.reviewers.domain.post.dto.request.CreateCommentRequest
import com.teamsparta.reviewers.domain.post.dto.request.DeleteCommentRequest
import com.teamsparta.reviewers.domain.post.dto.request.UpdateCommentRequest
import com.teamsparta.reviewers.domain.post.dto.request.*
import com.teamsparta.reviewers.domain.post.dto.response.CommentReplyResponse
import com.teamsparta.reviewers.domain.post.dto.response.CommentResponse
import com.teamsparta.reviewers.domain.post.model.CommentEntity
import com.teamsparta.reviewers.domain.post.model.toReplyResponse
import com.teamsparta.reviewers.domain.post.model.toResponse
import com.teamsparta.reviewers.domain.post.repository.CommentRepository
import com.teamsparta.reviewers.domain.post.repository.PostRepository
import com.teamsparta.reviewers.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.ui.Model


@Service
class CommentServiceImpl(
    private val postRepository: PostRepository,
    private val commentRepository: CommentRepository,
    private val userRepository: UserRepository
) : CommentService {

    @Transactional
    override fun createComment(
        postId: Long,
        userId: Long,
        request: CreateCommentRequest
    ): CommentResponse {
        val post = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("Post", postId)
        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("User", userId)
        return commentRepository.save(
            CommentEntity(
                content = request.content,
                userName = request.userName,
                user = user,
                post = post
            )
        ).toResponse()
    }

    @Transactional
    override fun updateComment(
        postId: Long,
        commentId: Long,
        userId: Long,
        request: UpdateCommentRequest
    ): CommentResponse {

        val post = postRepository.findByIdOrNull(postId)
            ?: throw ModelNotFoundException("Post", postId)
        val comment = commentRepository.findByIdOrNull(commentId)
            ?: throw ModelNotFoundException("Comment", commentId)

        post.content = request.content
        comment.content = request.content
        return comment.toResponse()
    }
    /*    //63~74줄은 캡슐화, 외부에서 사용 하는데 필요한 부분만 공개하기 위한 부분
        // 주어진 ID에 해당하는 게시물 찾기, 못 하면 예외처리
        private fun findPostById(postId: Long): PostEntity {
            return postRepository.findByIdOrNull(postId)
                ?: throw ModelNotFoundException("Post", postId)
        }
        // 주어진 ID에 해당하는 댓글 찾기, 못 하면 예외처리
        private fun findCommentById(commentId: Long): CommentEntity {
            return commentRepository.findByIdOrNull(commentId)
                ?: throw ModelNotFoundException("Comment", commentId)
        }
        // 게시물과 댓글의 내용을 주어진 내용으로 업데이트
        private fun updatePostAndCommentContents(post: PostEntity, comment: CommentEntity, newContent: String) {
            post.content = newContent
            comment.content = newContent
        }*/

    @Transactional
    override fun deleteComment(
        postId: Long,
        commentId: Long,
        userId: Long,
        request: DeleteCommentRequest
    ): CommentResponse {
        val post = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("Post", postId)
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("comment", commentId)
        if(comment.user.userid != request.userId) {
            throw IdNotMatchException("id", request.userId)
        }
        commentRepository.delete(comment)
        return commentRepository.save(comment)
            .toResponse()
    }

    @Transactional
    override fun getCommentByPostId(
        postId: Long
    ): List<CommentResponse> {
        val post = postRepository.findByIdOrNull(postId)
            ?: throw ModelNotFoundException("Post", postId)
        return post.comments.map { it.toResponse() }
    }

    @Transactional
    override fun getCommentByCommentId(
        postId: Long, commentId: Long
    ): List<CommentResponse> {
        val post = postRepository.findByIdOrNull(postId)
            ?: throw ModelNotFoundException("Comment", commentId)
        return  post.comments.map { it.toResponse() }
    }

            .toResponse()
    }

    @Transactional
    override fun createReply( // 대댓글 작성
        postId: Long,
        userId: Long,
        parentcommentId: Long,
        request: CreateReplyRequest
    ): CommentReplyResponse {
        val post = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("Post", postId)
        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("User", userId)
        val parentComment = commentRepository.findByIdOrNull(parentcommentId) ?: throw ModelNotFoundException(
            "CommentId", parentcommentId
        )

        return commentRepository.save(
            CommentEntity(
                content = request.content,
                userName = request.userName,
                user = user,
                post = post,
                parentComment = parentComment
            )
        ).toReplyResponse()
    }

    @Transactional
    override fun updateReply(// 대댓글 수정
        postId: Long,
        userId: Long,
        parentcommentId: Long,
        request: UpdateReplyRequest
    ): CommentReplyResponse {
        val post = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("Post", postId)
        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("User", userId)
        val parentComment = commentRepository.findByIdOrNull(parentcommentId) ?: throw ModelNotFoundException(
            "Comment", parentcommentId
        )

        return commentRepository.save(
            CommentEntity(
                content = request.content,
                userName = request.userName,
                user = user,
                post = post,
                parentComment = parentComment
            )
        ).toReplyResponse()
    }

    @Transactional
    override fun deleteReply( // 대댓글 삭제
        postId: Long,
        userId: Long,
        parentcommentId: Long,
        request: DeleteReplyRequest
    ) {
        val post = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("Post", postId)
        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("User", userId)
        val parentComment = commentRepository.findByIdOrNull(parentcommentId) ?: throw ModelNotFoundException(
            "ParentComment", parentcommentId
        )

        commentRepository.delete(parentComment)
    }
}