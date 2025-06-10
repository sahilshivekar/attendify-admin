package com.attendify_admin.home.users.data.dto.request

data class UpdateStudentFcmTokenRequest(
    val studentId: Int, // Based on Node.js controller
    val fcmToken: String
)