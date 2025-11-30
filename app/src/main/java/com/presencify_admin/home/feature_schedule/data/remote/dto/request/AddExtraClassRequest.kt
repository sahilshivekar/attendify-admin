package com.presencify_admin.home.feature_schedule.data.remote.dto.request

data class AddExtraClassRequest(
    val instructorId: Int,
    val startTime: String,
    val endTime: String,
    val dayOfWeek: String,
    val roomId: Int,
    val batchId: Int,
    val activeFrom: String,
    val activeTill: String,
    val classType: String,
    val courseId: Int,
    val timetableId: Int
)