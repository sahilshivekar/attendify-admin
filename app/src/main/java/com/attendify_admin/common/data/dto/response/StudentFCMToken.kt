package com.attendify_admin.common.data.dto.response

data class StudentFcmToken(
    val id: Int,
    val fcmToken: String,
    val studentId: Int,
    val createdAt: String,
    val updatedAt: String
)