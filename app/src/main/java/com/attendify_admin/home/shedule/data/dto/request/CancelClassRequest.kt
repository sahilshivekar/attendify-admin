package com.attendify_admin.home.shedule.data.dto.request

data class CancelClassRequest(
    val classId: Int,
    val reason: String?,
    val date: String,
)