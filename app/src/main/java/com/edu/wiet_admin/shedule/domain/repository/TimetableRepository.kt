package com.edu.wiet_admin.shedule.domain.repository

import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Timetable
import com.edu.wiet_admin.shedule.data.dto.request.AddTimetableRequest
import com.edu.wiet_admin.shedule.data.dto.request.UpdateTimetableRequest
import retrofit2.Response

interface TimetableRepository {

    // Get all timetables
    suspend fun getTimetables(
        semesterNumber: Int?,
        academicStartYearOfSemester: Int?,
        academicEndYearOfSemester: Int?,
        page: Int = 1,
        limit: Int = 10
    ): Response<WietApiResponse<List<Timetable>>>

    // Get timetable by ID
    suspend fun getTimetableById(timetableId: Int): Response<WietApiResponse<Timetable>>

    // Add a timetable
    suspend fun addTimetable(requestBody: AddTimetableRequest): Response<WietApiResponse<Timetable>>

    // Update a timetable
    suspend fun updateTimetable(requestBody: UpdateTimetableRequest): Response<WietApiResponse<Timetable>>

    // Remove a timetable
    suspend fun removeTimetable(timetableId: Int): Response<WietApiResponse<Unit>>
}