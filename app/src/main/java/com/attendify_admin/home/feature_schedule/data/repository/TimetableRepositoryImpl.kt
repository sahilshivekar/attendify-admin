package com.attendify_admin.home.feature_schedule.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.attendify_admin.common.data.remote.dto.response.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.TimetableDto
import com.attendify_admin.common.data.remote.dto.response.TimetableListWithTotalCountDto
import com.attendify_admin.home.feature_schedule.data.remote.TimetableApi
import com.attendify_admin.home.feature_schedule.data.remote.dto.request.AddTimetableRequest
import com.attendify_admin.home.feature_schedule.data.remote.dto.request.UpdateTimetableRequest
import com.attendify_admin.home.feature_schedule.domain.repository.TimetableRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


class TimetableRepositoryImpl(
    private val timetableApi: TimetableApi
) : TimetableRepository {

    override fun getTimetables(
        semesterNumber: Int?,
        academicStartYearOfSemester: Int?,
        academicEndYearOfSemester: Int?
    ): Flow<PagingData<TimetableDto>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                GetTimetablesPagingSource(
                    timetableApi = timetableApi,
                    semesterNumber = semesterNumber,
                    academicStartYearOfSemester = academicStartYearOfSemester,
                    academicEndYearOfSemester = academicEndYearOfSemester
                )
            }
        ).flow
    }
    override suspend fun getAllTimetables(
        semesterNumber: Int?,
        academicStartYearOfSemester: Int?,
        academicEndYearOfSemester: Int?
    ): Response<AttendifyApiResponse<TimetableListWithTotalCountDto>> {
        return timetableApi.getTimetables(
            semesterNumber = semesterNumber,
            academicStartYearOfSemester = academicStartYearOfSemester,
            academicEndYearOfSemester = academicEndYearOfSemester,
            page = 1,
            limit = 10,
            getAll = true
        )
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