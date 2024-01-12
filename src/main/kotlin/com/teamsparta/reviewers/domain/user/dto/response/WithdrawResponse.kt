package com.teamsparta.reviewers.domain.user.dto.response

import com.teamsparta.reviewers.domain.user.common.UserRole
import io.swagger.v3.oas.annotations.media.Schema

data class WithdrawResponse(

    val message: String, val success: Boolean

)