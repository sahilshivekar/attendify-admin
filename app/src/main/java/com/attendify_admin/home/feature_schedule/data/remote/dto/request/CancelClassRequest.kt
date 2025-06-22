package com.attendify_admin.home.feature_schedule.data.remote.dto.request

data class CancelClassRequest(
    val classId: Int,
    val reason: String?,
    val date: String,
)