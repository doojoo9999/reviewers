//package com.teamsparta.reviewers.domain.post.model
//
//import com.teamsparta.reviewers.domain.post.dto.response.CommentReplyResponse
//import com.teamsparta.reviewers.domain.user.model.UserEntity
//import jakarta.persistence.*
//import org.springframework.data.annotation.Id
//
//@Entity
//@Table(name = "comments_reply")
//class CommentReplyEntity (
//    @Column(name = "content", nullable = false)
//    var content: String,
//
//    @Column(name = "username", nullable = false)
//    var username: String,
//
//    @ManyToOne
//    @JoinColumn(name = "userid", nullable = false)
//    var user : UserEntity,
//
//    @ManyToOne
//    @JoinColumn(name = "postid", nullable = false)
//    var post : PostEntity,
//
//    @ManyToOne
//    @JoinColumn(name = "parent_comment_id")
//    var parentComment: CommentReplyEntity?)
//) {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//            @Column(name = "reply_comment_id")
//            var replycommentid:Long? = null
//    // 여기 var 명을 replycomment로 하는게 맞는지?
//}
//
//fun CommentEntity.toReplyResponse(): CommentReplyResponse {
//    return CommentReplyResponse(
//        postid = post.postid,
//        userid = user.userid,
//        parentComment = parentcomment
//    )
//}