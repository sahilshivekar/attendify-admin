package com.attendify_admin.home.shedule.domain.repository

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Timetable
import com.attendify_admin.home.shedule.data.dto.request.AddTimetableRequest
import com.attendify_admin.home.shedule.data.dto.request.UpdateTimetableRequest
import retrofit2.Response

interface TimetableRepository {

    // Get all timetables
    suspend fun getTimetables(
        semesterNumber: Int?,
        academicStartYearOfSemester: Int?,
        academicEndYearOfSemester: Int?,
        page: Int = 1,
        limit: Int = 10
    ): Response<AttendifyApiResponse<List<Timetable>>>

    // Get timetable by ID
    suspend fun getTimetableById(timetableId: Int): Response<AttendifyApiResponse<Timetable>>

    // Add a timetable
    suspend fun addTimetable(requestBody: AddTimetableRequest): Response<AttendifyApiResponse<Timetable>>

    // Update a timetable
    suspend fun updateTimetable(requestBody: UpdateTimetableRequest): Response<AttendifyApiResponse<Timetable>>

    // Remove a timetable
    suspend fun removeTimetable(timetableId: Int): Response<AttendifyApiResponse<Unit>>
}