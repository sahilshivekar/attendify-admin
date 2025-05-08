package com.attendify_admin.home.shedule.data

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.home.shedule.data.dto.request.AddClassRequest
import com.attendify_admin.home.shedule.domain.repository.ClassRepository
import retrofit2.Response
import com.attendify_admin.common.data.remote.response_dto.Class
import com.attendify_admin.home.shedule.data.dto.request.ExtendActiveTillDateRequest

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
}