package com.teamsparta.reviewers.domain.post.service

import com.teamsparta.reviewers.domain.exception.IdNotMatchException
import com.teamsparta.reviewers.domain.exception.ModelNotFoundException
import com.teamsparta.reviewers.domain.post.dto.request.CreateCommentRequest
import com.teamsparta.reviewers.domain.post.dto.request.DeleteCommentRequest
import com.teamsparta.reviewers.domain.post.dto.request.UpdateCommentRequest
import com.teamsparta.reviewers.domain.post.dto.request.*
import com.teamsparta.reviewers.domain.post.dto.response.CommentReplyResponse
import com.teamsparta.reviewers.domain.post.dto.response.CommentResponse
import com.teamsparta.reviewers.domain.post.dto.response.DeleteResponse
import com.teamsparta.reviewers.domain.post.model.CommentEntity
import com.teamsparta.reviewers.domain.post.model.toReplyResponse
import com.teamsparta.reviewers.domain.post.model.toResponse
import com.teamsparta.reviewers.domain.post.repository.CommentRepository
import com.teamsparta.reviewers.domain.post.repository.PostRepository
import com.teamsparta.reviewers.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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

        comment.content = request.content
        return comment.toResponse()
    }


    @Transactional
    override fun deleteComment(
        postId: Long,
        commentId: Long,
        userId: Long,
        request: DeleteCommentRequest
    ): DeleteResponse {
        val post = postRepository.findByIdOrNull(postId)
            ?: throw ModelNotFoundException("Post", postId)
        val comment = commentRepository.findByIdOrNull(commentId)
            ?: throw ModelNotFoundException("comment", commentId)
        if(comment.user.userid != request.userid) {
            throw IdNotMatchException("id", request.userid)
        }
        commentRepository.delete(comment)
        return DeleteResponse(message = "삭제 완료")
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
    override fun getRepliesByCommentId(
        postId: Long,
        commentId: Long
    ): List<CommentReplyResponse> {
        val comment = commentRepository.findByIdOrNull(commentId)
            ?: throw ModelNotFoundException("Comment", commentId)

        val replies = commentRepository.findByParentCommentId(commentId)

        return replies.map { it.toReplyResponse() }
    }
    @Transactional
    override fun createReply(
        postId: Long,
        userId: Long,
        parentCommentId: Long,
        request: CreateReplyRequest
    ): CommentReplyResponse {
        val post = postRepository.findByIdOrNull(postId)
            ?: throw ModelNotFoundException("Post", postId)
        val user = userRepository.findByIdOrNull(userId)
            ?: throw ModelNotFoundException("User", userId)

        // 일반 댓글 case
        if (parentCommentId == null) {
            return commentRepository.save(
                CommentEntity(
                    content = request.content,
                    user = user,
                    post = post,
                    parentCommentId = null
                )
            ).toReplyResponse()
        }

        // 대댓글 case
        else {
            val parentComment = commentRepository.findByIdOrNull(parentCommentId)
                ?: throw ModelNotFoundException("ParentComment", parentCommentId)

            return commentRepository.save(
                CommentEntity(
                    content = request.content,
                    user = user,
                    post = post,
                    parentCommentId = parentCommentId
                )
            ).toReplyResponse()
        }


    }
//    @Transactional
//    override fun updateReply(// 대댓글 수정
//        postId: Long,
//        userId: Long,
//        commentId: Long,
//        request: UpdateReplyRequest
//    ): CommentReplyResponse {
//        val post = postRepository.findByIdOrNull(postId)
//            ?: throw ModelNotFoundException("Post", postId)
//        val user = userRepository.findByIdOrNull(userId)
//            ?: throw ModelNotFoundException("User", userId)
//        val comment = commentRepository.findByIdOrNull(commentId)
//            ?: throw ModelNotFoundException("Commnet", commentId)
//
//        comment.content = request.content
//
//        return commentRepository.save(comment).toReplyResponse()
//
//    }
//    @Transactional
//    override fun deleteReply(
//        postId: Long,
//        commentId: Long,
//        parentcommentId: Long,
//        request: DeleteReplyRequest
//    ) : DeleteResponse {
//        val post = postRepository.findByIdOrNull(postId)
//            ?: throw ModelNotFoundException("Post", postId)
//        val user = userRepository.findByIdOrNull(commentId)
//            ?: throw ModelNotFoundException("User", commentId)
//        val parentComment = commentRepository.findByIdOrNull(parentcommentId)
//            ?: throw ModelNotFoundException(
//                "ParentComment", parentcommentId
//            )
//        commentRepository.delete(parentComment)
//
//        return DeleteResponse(message = "삭제 완료")
}
