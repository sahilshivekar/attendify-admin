package com.attendify_admin.home.feature_schedule.domain.repository

import androidx.paging.PagingData
import com.attendify_admin.common.data.remote.dto.response.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.TimetableDto
import com.attendify_admin.common.data.remote.dto.response.TimetableListWithTotalCountDto
import com.attendify_admin.home.feature_schedule.data.remote.dto.request.AddTimetableRequest
import com.attendify_admin.home.feature_schedule.data.remote.dto.request.UpdateTimetableRequest
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface TimetableRepository {

    // Get all timetables
    fun getTimetables(
        semesterNumber: Int?,
        academicStartYearOfSemester: Int?,
        academicEndYearOfSemester: Int?,
    ): Flow<PagingData<TimetableDto>>

    suspend fun getAllTimetables(
        semesterNumber: Int?,
        academicStartYearOfSemester: Int?,
        academicEndYearOfSemester: Int?,
    ): Response<AttendifyApiResponse<TimetableListWithTotalCountDto>>

    // Get timetable by ID
    suspend fun getTimetableById(timetableId: Int): Response<AttendifyApiResponse<TimetableDto>>

    // Add a timetable
    suspend fun addTimetable(requestBody: AddTimetableRequest): Response<AttendifyApiResponse<TimetableDto>>

    // Update a timetable
    suspend fun updateTimetable(requestBody: UpdateTimetableRequest): Response<AttendifyApiResponse<TimetableDto>>

    // Remove a timetable
    suspend fun removeTimetable(timetableId: Int): Response<AttendifyApiResponse<Unit>>
}