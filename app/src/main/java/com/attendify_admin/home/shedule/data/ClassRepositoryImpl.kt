package com.attendify_admin.home.shedule.data

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.dto.response.CancelledClass
import com.attendify_admin.common.data.dto.response.Class
import com.attendify_admin.home.shedule.data.dto.request.AddClassRequest
import com.attendify_admin.home.shedule.data.dto.request.AddExtraClassRequest
import com.attendify_admin.home.shedule.data.dto.request.CancelClassRequest
import com.attendify_admin.home.shedule.data.dto.request.ExtendActiveTillDateRequest
import com.attendify_admin.home.shedule.domain.repository.ClassRepository
import retrofit2.Response

class ClassRepositoryImpl(
    private val classApi: ClassApi
) : ClassRepository {

    override suspend fun addClass(requestBody: AddClassRequest): Response<AttendifyApiResponse<Class>> {
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
    ): Response<AttendifyApiResponse<List<Class>>> {
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

    override suspend fun getClassById(classId: Int): Response<AttendifyApiResponse<Class>> {
        return classApi.getClassById(classId)
    }

    override suspend fun extendActiveTillDateOfClass(requestBody: ExtendActiveTillDateRequest): Response<AttendifyApiResponse<Class>> {
        return classApi.extendActiveTillDateOfClass(requestBody)
    }

    override suspend fun removeClass(classId: Int): Response<AttendifyApiResponse<Unit>> {
        return classApi.removeClass(classId)
    }

    override suspend fun cancelClass(requestBody: CancelClassRequest): Response<AttendifyApiResponse<Unit>> {
        return classApi.cancelClass(requestBody)
    }

    // New: Implement addExtraClass
    override suspend fun addExtraClass(requestBody: AddExtraClassRequest): Response<AttendifyApiResponse<Class>> {
        return classApi.addExtraClass(requestBody)
    }

    // New: Implement getCancelledClasses
    override suspend fun getCancelledClasses(
        divisionId: Int,
        batchId: Int,
        date: String,
        page: Int,
        limit: Int
    ): Response<AttendifyApiResponse<CancelledClass>> {
        return classApi.getCancelledClasses(
            divisionId,
            batchId,
            date,
            page,
            limit
        )
    }
}