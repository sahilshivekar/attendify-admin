package com.attendify_admin.home.feature_schedule.domain.repository

import androidx.paging.PagingData
import com.attendify_admin.common.data.remote.dto.response.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.CancelledClassDto
import com.attendify_admin.common.data.remote.dto.response.ClassDto
import com.attendify_admin.home.feature_schedule.data.remote.dto.request.AddClassRequest
import com.attendify_admin.home.feature_schedule.data.remote.dto.request.AddExtraClassRequest
import com.attendify_admin.home.feature_schedule.data.remote.dto.request.CancelClassRequest
import com.attendify_admin.home.feature_schedule.data.remote.dto.request.ExtendActiveTillDateRequest
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


interface ClassRepository {

    // Add a class
    suspend fun addClass(requestBody: AddClassRequest): Response<AttendifyApiResponse<ClassDto>>

    // Get all classes
    fun getClasses(
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
    ): Flow<PagingData<ClassDto>>

    // Get class by ID
    suspend fun getClassById(classId: Int): Response<AttendifyApiResponse<ClassDto>>

    // Extend active till date of a class
    suspend fun extendActiveTillDateOfClass(requestBody: ExtendActiveTillDateRequest): Response<AttendifyApiResponse<ClassDto>>

    // Remove a class
    suspend fun removeClass(classId: Int): Response<AttendifyApiResponse<Unit>>

    suspend fun cancelClass(requestBody: CancelClassRequest): Response<AttendifyApiResponse<Unit>>

    suspend fun addExtraClass(requestBody: AddExtraClassRequest): Response<AttendifyApiResponse<ClassDto>>

    fun getCancelledClasses(
        divisionId: Int,
        batchId: Int,
        date: String,
    ): Flow<PagingData<CancelledClassDto>>
}