package com.attendify_admin.home.shedule.data.dto.request

import retrofit2.http.Query

data class CancelClassRequest(
    val classId: String,
    val reason: String?,
    val date: String,
)