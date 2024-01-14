package com.teamsparta.reviewers.domain.exception

data class EmailNotFoundException(
    val modelName: String, val user:Long): RuntimeException(
    "Model $modelName not found with given id: $user"
)
