package com.attendify_admin.shedule.data.dto.request

data class ExtendActiveTillDateRequest(
    val classId: Int,
    val newActiveTill: String
)