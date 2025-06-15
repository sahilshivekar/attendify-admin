package com.attendify_admin.home.feature_schedule.data

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.TimetableDto
import com.attendify_admin.home.feature_schedule.data.dto.request.AddTimetableRequest
import com.attendify_admin.home.feature_schedule.data.dto.request.UpdateTimetableRequest
import com.attendify_admin.home.feature_schedule.domain.repository.TimetableRepository
import retrofit2.Response


class TimetableRepositoryImpl(
    private val timetableApi: TimetableApi
) : TimetableRepository {

    override suspend fun getTimetables(
        semesterNumber: Int?,
        academicStartYearOfSemester: Int?,
        academicEndYearOfSemester: Int?,
        page: Int,
        limit: Int
    ): Response<AttendifyApiResponse<List<TimetableDto>?>> {
        return timetableApi.getTimetables(semesterNumber, academicStartYearOfSemester, academicEndYearOfSemester, page, limit)
    }

    override suspend fun getTimetableById(timetableId: Int): Response<AttendifyApiResponse<TimetableDto>> {
        return timetableApi.getTimetableById(timetableId)
    }

    override suspend fun addTimetable(requestBody: AddTimetableRequest): Response<AttendifyApiResponse<TimetableDto>> {
        return timetableApi.addTimetable(requestBody)
    }

    override suspend fun updateTimetable(requestBody: UpdateTimetableRequest): Response<AttendifyApiResponse<TimetableDto>> {
        return timetableApi.updateTimetable(requestBody)
    }

    override suspend fun removeTimetable(timetableId: Int): Response<AttendifyApiResponse<Unit>> {
        return timetableApi.removeTimetable(timetableId)
    }
}