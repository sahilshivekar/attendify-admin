package com.presencify_admin.common.data.remote.dto.response

import com.presencify_admin.common.domain.model.Class
import com.google.gson.annotations.SerializedName

data class ClassDto(
    val id: Int,
    val activeFrom: String,
    val activeTill: String,
    val classType: String,
    val dayOfWeek: String,
    val endTime: String,
    val startTime: String,
    val batchId: Any,
    @SerializedName("Batch")
    val batch: BatchDto?,
    val courseId: Int,
    @SerializedName("Course")
    val course: CourseDto?,
    val instructorId: Int,
    @SerializedName("Staff")
    val staff: StaffDto?,
    val roomId: Int,
    @SerializedName("Room")
    val room: RoomDto?,
    val timetableId: Int,
    @SerializedName("Timetable")
    val timetable: TimetableDto?,
    val isExtraClass: Boolean,
    val createdAt: String,
    val updatedAt: String
)

fun ClassDto.toClass(): Class {
    return Class(
        id = id,
        activeFrom = activeFrom,
        activeTill = activeTill,
        classType = classType,
        dayOfWeek = dayOfWeek,
        endTime = endTime,
        startTime = startTime,
        batchId = batchId,
        batch = batch?.toBatch(),
        courseId = courseId,
        course = course?.toCourse(),
        instructorId = instructorId,
        staff = staff?.toStaff(),
        roomId = roomId,
        room = room?.toRoom(),
        timetableId = timetableId,
        timetable = timetable?.toTimetable(),
        isExtraClass = isExtraClass
    )
}
