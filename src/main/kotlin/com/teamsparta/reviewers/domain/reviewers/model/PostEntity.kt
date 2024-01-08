package com.teamsparta.reviewers.domain.reviewers.model

import com.teamsparta.reviewers.domain.reviewers.dto.PostResponse
import jakarta.persistence.*
import sun.jvm.hotspot.gc.shared.Generation

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
    var likes : Int = 0,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    fun addLikes() {
        likes += 1
    }
}

fun PostEntity.toResponse() : PostResponse {
    return PostResponse (
        thumbnailUrl = thumbnailUrl,
        title = title,
        content = content,
        likes = likes,
    )
}


