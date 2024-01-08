package com.teamsparta.reviewers.domain.post.model

import com.teamsparta.reviewers.domain.post.dto.response.PostResponse
import jakarta.persistence.*

@Entity
@Table(name = "posts")
class PostEntity (
    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "content", nullable = false)
    var content: String,

    @Column(name = "thumbnailUrl", nullable = false)
    var thumbnailUrl: String,

    @Column(name = "likes", nullable = false)
    var likes : Int? = 0,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0


    fun addLikes() {
        likes = likes!!.plus(1)
    }
}

fun PostEntity.toResponse(): PostResponse {
    return PostResponse(
        thumbnailUrl = thumbnailUrl,
        title = title,
        content = content,
        likes = 0
    )
}


