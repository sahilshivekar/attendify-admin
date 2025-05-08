package com.attendify_admin.home.shedule.data.dto.request

data class AddClassRequest(
    val instructorId: Int,
    val startTime: String,
    val endTime: String,
    val dayOfWeek: String,
    val roomId: Int,
    val batchId: Int?,
    val activeFrom: String,
    val activeTill: String,
    val classType: String,
    val courseId: Int,
    val timetableId: Int
)

