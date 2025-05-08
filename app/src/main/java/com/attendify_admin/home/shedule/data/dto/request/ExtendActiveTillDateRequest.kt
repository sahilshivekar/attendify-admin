package com.attendify_admin.home.shedule.data.dto.request

data class ExtendActiveTillDateRequest(
    val classId: Int,
    val newActiveTill: String
)