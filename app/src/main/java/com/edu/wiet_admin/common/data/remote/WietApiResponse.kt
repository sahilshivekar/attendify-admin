package com.edu.wiet_admin.common.data.remote

data class WietApiResponse<T>(
    val data: T?,
    val message: String,
    val statusCode: Int,
    val success: Boolean
)


