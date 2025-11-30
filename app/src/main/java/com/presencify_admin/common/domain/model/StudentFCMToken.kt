package com.presencify_admin.common.domain.model

data class StudentFCMToken(
    val id: Int,
    val fcmToken: String,
    val studentId: Int
)