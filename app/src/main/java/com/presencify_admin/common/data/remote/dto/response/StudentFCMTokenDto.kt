package com.presencify_admin.common.data.remote.dto.response

import com.presencify_admin.common.domain.model.StudentFCMToken

data class StudentFCMTokenDto(
    val id: Int,
    val fcmToken: String,
    val studentId: Int,
    val createdAt: String,
    val updatedAt: String
)

fun StudentFCMTokenDto.toStudentFCMToken(): StudentFCMToken {
    return StudentFCMToken(
        id = id,
        fcmToken = fcmToken,
        studentId = studentId
    )
}