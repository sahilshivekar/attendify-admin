package com.presencify_admin.home.feature_schedule.domain.repository

import androidx.paging.PagingData
import com.presencify_admin.common.data.remote.dto.response.PresencifyApiResponse
import com.presencify_admin.common.data.remote.dto.response.CancelledClassDto
import com.presencify_admin.common.data.remote.dto.response.CancelledClassListWithTotalCountDto
import com.presencify_admin.common.data.remote.dto.response.ClassDto
import com.presencify_admin.common.data.remote.dto.response.ClassListWithTotalCountDto
import com.presencify_admin.home.feature_schedule.data.remote.dto.request.AddClassRequest
import com.presencify_admin.home.feature_schedule.data.remote.dto.request.AddExtraClassRequest
import com.presencify_admin.home.feature_schedule.data.remote.dto.request.CancelClassRequest
import com.presencify_admin.home.feature_schedule.data.remote.dto.request.ExtendActiveTillDateRequest
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


interface ClassRepository {

    // Add a class
    suspend fun addClass(requestBody: AddClassRequest): Response<PresencifyApiResponse<ClassDto>>

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

    suspend fun getAllClasses(
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
        semesterId: Int?
    ): Response<PresencifyApiResponse<ClassListWithTotalCountDto>>

    // Get class by ID
    suspend fun getClassById(classId: Int): Response<PresencifyApiResponse<ClassDto>>

    // Extend active till date of a class
    suspend fun extendActiveTillDateOfClass(requestBody: ExtendActiveTillDateRequest): Response<PresencifyApiResponse<ClassDto>>

    // Remove a class
    suspend fun removeClass(classId: Int): Response<PresencifyApiResponse<Unit>>

    suspend fun cancelClass(requestBody: CancelClassRequest): Response<PresencifyApiResponse<Unit>>

    suspend fun addExtraClass(requestBody: AddExtraClassRequest): Response<PresencifyApiResponse<ClassDto>>

    fun getCancelledClasses(
        divisionId: Int,
        batchId: Int,
        date: String,
    ): Flow<PagingData<CancelledClassDto>>

    suspend fun getAllCancelledClasses(
        divisionId: Int,
        batchId: Int,
        date: String
    ): Response<PresencifyApiResponse<CancelledClassListWithTotalCountDto>>

}