package com.attendify_admin.home.feature_schedule.data.dto.request

data class ExtendActiveTillDateRequest(
    val classId: Int,
    val newActiveTill: String
)