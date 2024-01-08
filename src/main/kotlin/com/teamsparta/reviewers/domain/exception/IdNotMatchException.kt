package com.teamsparta.reviewers.domain.exception

data class IdNotMatchException(
    val modelName : String, val id : Long) : RuntimeException(
" wrong Model : $modelName, id : $id")
