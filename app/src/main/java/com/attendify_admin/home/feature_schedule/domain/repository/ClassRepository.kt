package com.attendify_admin.home.feature_schedule.domain.repository

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.CancelledClassDto
import com.attendify_admin.common.data.remote.dto.response.ClassDto
import com.attendify_admin.home.feature_schedule.data.dto.request.AddClassRequest
import com.attendify_admin.home.feature_schedule.data.dto.request.AddExtraClassRequest
import com.attendify_admin.home.feature_schedule.data.dto.request.CancelClassRequest
import com.attendify_admin.home.feature_schedule.data.dto.request.ExtendActiveTillDateRequest
import retrofit2.Response


interface ClassRepository {

    // Add a class
    suspend fun addClass(requestBody: AddClassRequest): Response<AttendifyApiResponse<ClassDto>>

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
    ): Response<AttendifyApiResponse<List<ClassDto>?>>

    // Get class by ID
    suspend fun getClassById(classId: Int): Response<AttendifyApiResponse<ClassDto>>

    // Extend active till date of a class
    suspend fun extendActiveTillDateOfClass(requestBody: ExtendActiveTillDateRequest): Response<AttendifyApiResponse<ClassDto>>

    // Remove a class
    suspend fun removeClass(classId: Int): Response<AttendifyApiResponse<Unit>>

    suspend fun cancelClass(requestBody: CancelClassRequest): Response<AttendifyApiResponse<Unit>>

    suspend fun addExtraClass(requestBody: AddExtraClassRequest): Response<AttendifyApiResponse<ClassDto>>

    suspend fun getCancelledClasses(
        divisionId: Int,
        batchId: Int,
        date: String, // Keeping as Int based on API, but consider if it should be String (e.g., "YYYY-MM-DD")
        page: Int,
        limit: Int
    ): Response<AttendifyApiResponse<CancelledClassDto>>
}