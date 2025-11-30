package com.presencify_admin.feature_admin_auth.data.remote.dto.responses

import com.presencify_admin.feature_admin_auth.domain.model.VerificationCode

data class VerificationCodeDto(
    val expiresAt: String
)

fun VerificationCodeDto.toVerificationCode(): VerificationCode {
    return VerificationCode(
        expiresAt = expiresAt
    )
}