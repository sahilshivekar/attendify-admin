package com.attendify_admin.common.data.remote.response_dto

data class StudentFcmToken(
    val id: Int,
    val fcmToken: String,
    val studentId: Int,
    val createdAt: String,
    val updatedAt: String
)