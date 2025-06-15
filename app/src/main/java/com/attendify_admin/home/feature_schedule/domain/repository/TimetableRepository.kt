package com.attendify_admin.home.feature_schedule.domain.repository

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.TimetableDto
import com.attendify_admin.home.feature_schedule.data.dto.request.AddTimetableRequest
import com.attendify_admin.home.feature_schedule.data.dto.request.UpdateTimetableRequest
import retrofit2.Response

interface TimetableRepository {

    // Get all timetables
    suspend fun getTimetables(
        semesterNumber: Int?,
        academicStartYearOfSemester: Int?,
        academicEndYearOfSemester: Int?,
        page: Int = 1,
        limit: Int = 10
    ): Response<AttendifyApiResponse<List<TimetableDto>?>>

    // Get timetable by ID
    suspend fun getTimetableById(timetableId: Int): Response<AttendifyApiResponse<TimetableDto>>

    // Add a timetable
    suspend fun addTimetable(requestBody: AddTimetableRequest): Response<AttendifyApiResponse<TimetableDto>>

    // Update a timetable
    suspend fun updateTimetable(requestBody: UpdateTimetableRequest): Response<AttendifyApiResponse<TimetableDto>>

    // Remove a timetable
    suspend fun removeTimetable(timetableId: Int): Response<AttendifyApiResponse<Unit>>
}