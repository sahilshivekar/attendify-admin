package com.attendify_admin.common.data.dto.response

data class Class(
    val id: Int,
    val activeFrom: String,
    val activeTill: String,
    val classType: String,
    val dayOfWeek: String,
    val endTime: String,
    val startTime: String,
    val batchId: Any,
    val Batch: Batch?,
    val courseId: Int,
    val Course: Course?,
    val instructorId: Int,
    val Staff: Staff?,
    val roomId: Int,
    val Room: Room?,
    val timetableId: Int,
    val Timetable: Timetable?,
    val isExtraClass: Boolean,
    val createdAt: String,
    val updatedAt: String
)