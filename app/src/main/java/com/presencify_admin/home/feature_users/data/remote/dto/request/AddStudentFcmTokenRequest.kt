package com.presencify_admin.home.feature_users.data.remote.dto.request

data class AddStudentFcmTokenRequest(
    val studentId: Int, // Based on Node.js controller's `findByPk` and `create`
    val fcmToken: String
)