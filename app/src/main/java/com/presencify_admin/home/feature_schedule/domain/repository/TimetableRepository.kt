package com.presencify_admin.home.feature_schedule.domain.repository

import androidx.paging.PagingData
import com.presencify_admin.common.data.remote.dto.response.PresencifyApiResponse
import com.presencify_admin.common.data.remote.dto.response.TimetableDto
import com.presencify_admin.common.data.remote.dto.response.TimetableListWithTotalCountDto
import com.presencify_admin.home.feature_schedule.data.remote.dto.request.AddTimetableRequest
import com.presencify_admin.home.feature_schedule.data.remote.dto.request.UpdateTimetableRequest
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
    ): Response<PresencifyApiResponse<TimetableListWithTotalCountDto>>

    // Get timetable by ID
    suspend fun getTimetableById(timetableId: Int): Response<PresencifyApiResponse<TimetableDto>>

    // Add a timetable
    suspend fun addTimetable(requestBody: AddTimetableRequest): Response<PresencifyApiResponse<TimetableDto>>

    // Update a timetable
    suspend fun updateTimetable(requestBody: UpdateTimetableRequest): Response<PresencifyApiResponse<TimetableDto>>

    // Remove a timetable
    suspend fun removeTimetable(timetableId: Int): Response<PresencifyApiResponse<Unit>>
}