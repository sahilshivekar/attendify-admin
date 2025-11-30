package com.presencify_admin.common.domain.model

data class Class(
    val id: Int,
    val activeFrom: String,
    val activeTill: String,
    val classType: String,
    val dayOfWeek: String,
    val endTime: String,
    val startTime: String,
    val batchId: Any,
    val batch: Batch?,
    val courseId: Int,
    val course: Course?,
    val instructorId: Int,
    val staff: Staff?,
    val roomId: Int,
    val room: Room?,
    val timetableId: Int,
    val timetable: Timetable?,
    val isExtraClass: Boolean
)