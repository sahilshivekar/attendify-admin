package com.edu.wiet_admin.shedule.domain.repository

import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Class
import com.edu.wiet_admin.shedule.data.dto.request.AddClassRequest
import com.edu.wiet_admin.shedule.data.dto.request.ExtendActiveTillDateRequest
import retrofit2.Response


interface ClassRepository {

    // Add a class
    suspend fun addClass(requestBody: AddClassRequest): Response<WietApiResponse<Class>>

    // Get all classes
    suspend fun getClasses(
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
        page: Int = 1,
        limit: Int = 10
    ): Response<WietApiResponse<List<Class>>>

    // Get class by ID
    suspend fun getClassById(classId: Int): Response<WietApiResponse<Class>>

    // Extend active till date of a class
    suspend fun extendActiveTillDateOfClass(requestBody: ExtendActiveTillDateRequest): Response<WietApiResponse<Class>>

    // Remove a class
    suspend fun removeClass(classId: Int): Response<WietApiResponse<Unit>>
}