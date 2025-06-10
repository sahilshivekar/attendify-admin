package com.attendify_admin.home.users.data.dto.request

data class RemoveStudentFcmTokenRequest(
    val studentId: Int // Based on Node.js controller's `req.body`
)