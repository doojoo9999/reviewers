package com.teamsparta.reviewers.domain.post.model

import com.teamsparta.reviewers.domain.post.dto.response.PostResponse
import com.teamsparta.reviewers.domain.post.dto.response.AddLikeResponse
import com.teamsparta.reviewers.domain.user.model.UserEntity
import jakarta.persistence.*

@Entity
@Table(name = "posts")
class PostEntity (
    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "content", nullable = false)
    var content: String,

    @Column(name = "thumbnailurl", nullable = false)
    var thumbnailUrl: String,

    @Column(name = "likes", nullable = false)
    var likes : Int,

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    val comments: MutableList<CommentEntity> = mutableListOf(),

    @ManyToOne
    @JoinColumn(name = "email")
    val email : UserEntity,



) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var postid: Long = 0
}

fun PostEntity.toResponse(): PostResponse {
    return PostResponse(
        thumbnailUrl = thumbnailUrl,
        title = title,
        content = content,
        likes = likes
    )
}

fun PostEntity.toAddLikeResponse() : AddLikeResponse {
    return AddLikeResponse(
        email = email.email,
        postid = postid,
        likes = likes,
    )
}


