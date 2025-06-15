package com.attendify_admin.common.data.remote.dto.response

import com.attendify_admin.common.domain.model.Attendance
import com.google.gson.annotations.SerializedName

data class AttendanceDto(
    val id: Int,
    val date: String,
    val classId: Int,
    @SerializedName("Class")
    val classObj: ClassDto?,
    @SerializedName("BLEsessionUUID")
    val bleSessionUUID: String?,
    val createdAt: String,
    val updatedAt: String,
)


fun AttendanceDto.toAttendance(): Attendance {
    return Attendance(
        id = id,
        date = date,
        classId = classId,
        classObj = classObj?.toClass(),
        bleSessionUUID = bleSessionUUID
    )
}
