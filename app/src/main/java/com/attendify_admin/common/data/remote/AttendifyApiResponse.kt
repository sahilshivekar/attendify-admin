package com.attendify_admin.common.data.remote

data class AttendifyApiResponse<T>(
    val data: T?,
    val message: String,
    val statusCode: Int,
    val success: Boolean
)


