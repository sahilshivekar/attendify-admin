package com.attendify_admin.home.shedule.data.dto.request

data class CancelClassRequest(
    val classId: String,
    val reason: String?,
    val date: String,
)