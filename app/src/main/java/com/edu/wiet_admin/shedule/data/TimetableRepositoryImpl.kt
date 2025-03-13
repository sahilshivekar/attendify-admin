package com.edu.wiet_admin.shedule.data

import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Timetable
import com.edu.wiet_admin.shedule.data.dto.request.AddTimetableRequest
import com.edu.wiet_admin.shedule.data.dto.request.UpdateTimetableRequest
import com.edu.wiet_admin.shedule.domain.repository.TimetableRepository
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
    ): Response<WietApiResponse<List<Timetable>>> {
        return timetableApi.getTimetables(semesterNumber, academicStartYearOfSemester, academicEndYearOfSemester, page, limit)
    }

    override suspend fun getTimetableById(timetableId: Int): Response<WietApiResponse<Timetable>> {
        return timetableApi.getTimetableById(timetableId)
    }

    override suspend fun addTimetable(requestBody: AddTimetableRequest): Response<WietApiResponse<Timetable>> {
        return timetableApi.addTimetable(requestBody)
    }

    override suspend fun updateTimetable(requestBody: UpdateTimetableRequest): Response<WietApiResponse<Timetable>> {
        return timetableApi.updateTimetable(requestBody)
    }

    override suspend fun removeTimetable(timetableId: Int): Response<WietApiResponse<Unit>> {
        return timetableApi.removeTimetable(timetableId)
    }
}