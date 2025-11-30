package com.presencify_admin.home.feature_schedule.data.remote.dto.request

data class ExtendActiveTillDateRequest(
    val classId: Int,
    val newActiveTill: String
)