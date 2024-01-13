package com.teamsparta.reviewers.domain.post.model

import com.teamsparta.reviewers.domain.user.model.UserEntity
import jakarta.persistence.*

@Entity
@Table(name = "likes")
class LikeEntity(
    @ManyToOne
    @JoinColumn(name = "email")
    val email: UserEntity,

    @ManyToOne
    @JoinColumn(name = "postid")
    val post: PostEntity
)
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long = 0
}