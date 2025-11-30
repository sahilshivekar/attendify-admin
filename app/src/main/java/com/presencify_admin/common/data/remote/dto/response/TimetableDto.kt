package com.presencify_admin.common.data.remote.dto.response

import com.presencify_admin.common.domain.model.Timetable
import com.google.gson.annotations.SerializedName

data class TimetableDto(
    val id: Int,
    val timetableVersion: Int,
    val divisionId: Int,
    @SerializedName("Division")
    val division: DivisionDto?,
    val createdAt: String,
    val updatedAt: String
)

fun TimetableDto.toTimetable(): Timetable {
    return Timetable(
        id = id,
        timetableVersion = timetableVersion,
        divisionId = divisionId,
        division = division?.toDivision()
    )
}
