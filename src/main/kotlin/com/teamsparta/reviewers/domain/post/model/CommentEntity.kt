package com.teamsparta.reviewers.domain.post.model

import com.teamsparta.reviewers.domain.post.dto.response.CommentResponse
import com.teamsparta.reviewers.domain.user.model.UserEntity
import jakarta.persistence.*

@Entity
@Table(name = "comments")
class CommentEntity (
    @Column(name = "content", nullable = false)
    var content: String,

    @Column(name = "username", nullable = false)
    var userName: String,

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    var user: UserEntity,

    @ManyToOne
    @JoinColumn(name = "postid", nullable = false)
    var post: PostEntity,
// JPA 어노테이션을 사용하여 CommentEntity의 부모-자식 관계를 표현

// @ManyToOne: 다대일(N:1) 관계
// - CommentEntity가 여러 개의 자식 댓글을 가질 수 있음
// - parentComment 속성을 통해 부모 댓글에 대한 참조
    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    var parentComment: CommentEntity? = null,
// @OneToMany: 일대다(1:N) 관계
// - CommentEntity가 여러 개의 자식 댓글을 가질 수 있음
// - mappedBy 부모 엔티티의 필드와 매핑된 자식 엔티티의 속성을 지정
// - fetch 지연 로딩
// - cascade 연관된 엔티티에 대한 변경을 전파
// - orphanRemoval은 부모 엔티티에서 떨어진 자식 엔티티를 삭제할지 여부 = 데이터베이스에서도 댓글 정보를 삭제할 지 여부
    @OneToMany(mappedBy = "parentComment", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    val commentList: MutableList<CommentEntity> = mutableListOf()
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentid")
    var commentid: Long? = null
}

fun CommentEntity.toResponse(): CommentResponse {
    return CommentResponse(
        content = content,
        userName = userName,
    )
}