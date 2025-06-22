package com.attendify_admin.home.feature_users.data.remote.dto.request

data class UpdateStudentFcmTokenRequest(
    val studentId: Int, // Based on Node.js controller
    val fcmToken: String
)