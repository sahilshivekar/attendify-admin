package com.presencify_admin.home.feature_users.data.remote.dto.request

data class RemoveStudentFcmTokenRequest(
    val studentId: Int // Based on Node.js controller's `req.body`
)