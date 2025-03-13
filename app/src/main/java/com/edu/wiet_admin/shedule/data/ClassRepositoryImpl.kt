package com.edu.wiet_admin.shedule.data

import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.shedule.data.dto.request.AddClassRequest
import com.edu.wiet_admin.shedule.domain.repository.ClassRepository
import retrofit2.Response
import com.edu.wiet_admin.common.data.remote.response_dto.Class
import com.edu.wiet_admin.shedule.data.dto.request.ExtendActiveTillDateRequest

class ClassRepositoryImpl(
    private val classApi: ClassApi
) : ClassRepository {

    override suspend fun addClass(requestBody: AddClassRequest): Response<WietApiResponse<Class>> {
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
    ): Response<WietApiResponse<List<Class>>> {
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

    override suspend fun getClassById(classId: Int): Response<WietApiResponse<Class>> {
        return classApi.getClassById(classId)
    }

    override suspend fun extendActiveTillDateOfClass(requestBody: ExtendActiveTillDateRequest): Response<WietApiResponse<Class>> {
        return classApi.extendActiveTillDateOfClass(requestBody)
    }

    override suspend fun removeClass(classId: Int): Response<WietApiResponse<Unit>> {
        return classApi.removeClass(classId)
    }
}