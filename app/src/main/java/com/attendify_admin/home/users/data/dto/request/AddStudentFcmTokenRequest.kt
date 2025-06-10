package com.attendify_admin.home.users.data.dto.request

data class AddStudentFcmTokenRequest(
    val studentId: Int, // Based on Node.js controller's `findByPk` and `create`
    val fcmToken: String
)