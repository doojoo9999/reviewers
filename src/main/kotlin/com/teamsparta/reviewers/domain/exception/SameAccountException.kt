package com.teamsparta.reviewers.domain.exception

data class SameAccountException(
    val email: String) : RuntimeException(
        "You have account already email: $email"
)