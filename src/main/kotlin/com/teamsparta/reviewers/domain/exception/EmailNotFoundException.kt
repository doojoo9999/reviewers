package com.teamsparta.reviewers.domain.exception

data class EmailNotFoundException(
    val modelName: String, val email:String?): RuntimeException(
    "Model $modelName not found with given id: $email"
)
