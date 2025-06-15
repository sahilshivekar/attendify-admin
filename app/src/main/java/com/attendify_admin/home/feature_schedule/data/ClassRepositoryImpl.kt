package com.attendify_admin.home.feature_schedule.data

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.CancelledClassDto
import com.attendify_admin.common.data.remote.dto.response.ClassDto
import com.attendify_admin.home.feature_schedule.data.dto.request.AddClassRequest
import com.attendify_admin.home.feature_schedule.data.dto.request.AddExtraClassRequest
import com.attendify_admin.home.feature_schedule.data.dto.request.CancelClassRequest
import com.attendify_admin.home.feature_schedule.data.dto.request.ExtendActiveTillDateRequest
import com.attendify_admin.home.feature_schedule.domain.repository.ClassRepository
import retrofit2.Response

class ClassRepositoryImpl(
    private val classApi: ClassApi
) : ClassRepository {

    override suspend fun addClass(requestBody: AddClassRequest): Response<AttendifyApiResponse<ClassDto>> {
        return classApi.addClass(requestBody)
    }

    override suspend fun getClasses(
        searchQuery: String?,
        timetableId: Int?,
        divisionId: Int?,
        startTime: String?,
        endTime: String?,
        activeFrom: String?,
        activeTill: String?,
        instructorId: Int?,
        dayOfWeek: String?,
        roomId: Int?,
        batchId: Int?,
        classType: String?,
        courseId: Int?,
        semesterId: Int?,
        page: Int,
        limit: Int
    ): Response<AttendifyApiResponse<List<ClassDto>?>> {
        return classApi.getClasses(
            searchQuery,
            timetableId,
            divisionId,
            startTime,
            endTime,
            activeFrom,
            activeTill,
            instructorId,
            dayOfWeek,
            roomId,
            batchId,
            classType,
            courseId,
            semesterId,
            page,
            limit
        )
    }

    override suspend fun getClassById(classId: Int): Response<AttendifyApiResponse<ClassDto>> {
        return classApi.getClassById(classId)
    }

    override suspend fun extendActiveTillDateOfClass(requestBody: ExtendActiveTillDateRequest): Response<AttendifyApiResponse<ClassDto>> {
        return classApi.extendActiveTillDateOfClass(requestBody)
    }

    override suspend fun removeClass(classId: Int): Response<AttendifyApiResponse<Unit>> {
        return classApi.removeClass(classId)
    }

    override suspend fun cancelClass(requestBody: CancelClassRequest): Response<AttendifyApiResponse<Unit>> {
        return classApi.cancelClass(requestBody)
    }

    // New: Implement addExtraClass
    override suspend fun addExtraClass(requestBody: AddExtraClassRequest): Response<AttendifyApiResponse<ClassDto>> {
        return classApi.addExtraClass(requestBody)
    }

    // New: Implement getCancelledClasses
    override suspend fun getCancelledClasses(
        divisionId: Int,
        batchId: Int,
        date: String,
        page: Int,
        limit: Int
    ): Response<AttendifyApiResponse<CancelledClassDto>> {
        return classApi.getCancelledClasses(
            divisionId,
            batchId,
            date,
            page,
            limit
        )
    }
}