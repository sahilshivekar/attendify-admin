package com.presencify_admin.home.feature_schedule.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.presencify_admin.common.data.remote.dto.response.PresencifyApiResponse
import com.presencify_admin.common.data.remote.dto.response.CancelledClassDto
import com.presencify_admin.common.data.remote.dto.response.CancelledClassListWithTotalCountDto
import com.presencify_admin.common.data.remote.dto.response.ClassDto
import com.presencify_admin.common.data.remote.dto.response.ClassListWithTotalCountDto
import com.presencify_admin.home.feature_schedule.data.remote.ClassApi
import com.presencify_admin.home.feature_schedule.data.remote.dto.request.AddClassRequest
import com.presencify_admin.home.feature_schedule.data.remote.dto.request.AddExtraClassRequest
import com.presencify_admin.home.feature_schedule.data.remote.dto.request.CancelClassRequest
import com.presencify_admin.home.feature_schedule.data.remote.dto.request.ExtendActiveTillDateRequest
import com.presencify_admin.home.feature_schedule.domain.repository.ClassRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class ClassRepositoryImpl(
    private val classApi: ClassApi,
) : ClassRepository {

    override suspend fun addClass(requestBody: AddClassRequest): Response<PresencifyApiResponse<ClassDto>> {
        return classApi.addClass(requestBody)
    }


    override fun getClasses(
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
    ): Flow<PagingData<ClassDto>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                GetClassesPagingSource(
                    classApi = classApi,
                    searchQuery = searchQuery,
                    timetableId = timetableId,
                    divisionId = divisionId,
                    startTime = startTime,
                    endTime = endTime,
                    activeFrom = activeFrom,
                    activeTill = activeTill,
                    instructorId = instructorId,
                    dayOfWeek = dayOfWeek,
                    roomId = roomId,
                    batchId = batchId,
                    classType = classType,
                    courseId = courseId,
                    semesterId = semesterId
                )
            }
        ).flow
    }

    override suspend fun getAllClasses(
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
    ): Response<PresencifyApiResponse<ClassListWithTotalCountDto>> {
        return classApi.getClasses(
            searchQuery = searchQuery,
            timetableId = timetableId,
            divisionId = divisionId,
            startTime = startTime,
            endTime = endTime,
            activeFrom = activeFrom,
            activeTill = activeTill,
            instructorId = instructorId,
            dayOfWeek = dayOfWeek,
            roomId = roomId,
            batchId = batchId,
            classType = classType,
            courseId = courseId,
            semesterId = semesterId,
            page = 1,
            limit = 10,
            getAll = true
        )
    }



    override suspend fun getClassById(classId: Int): Response<PresencifyApiResponse<ClassDto>> {
        return classApi.getClassById(classId)
    }

    override suspend fun extendActiveTillDateOfClass(requestBody: ExtendActiveTillDateRequest): Response<PresencifyApiResponse<ClassDto>> {
        return classApi.extendActiveTillDateOfClass(requestBody)
    }

    override suspend fun removeClass(classId: Int): Response<PresencifyApiResponse<Unit>> {
        return classApi.removeClass(classId)
    }

    override suspend fun cancelClass(requestBody: CancelClassRequest): Response<PresencifyApiResponse<Unit>> {
        return classApi.cancelClass(requestBody)
    }

    // New: Implement addExtraClass
    override suspend fun addExtraClass(requestBody: AddExtraClassRequest): Response<PresencifyApiResponse<ClassDto>> {
        return classApi.addExtraClass(requestBody)
    }

    override fun getCancelledClasses(
        divisionId: Int,
        batchId: Int,
        date: String
    ): Flow<PagingData<CancelledClassDto>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                GetCancelledClassesPagingSource(
                    classApi = classApi,
                    divisionId = divisionId,
                    batchId = batchId,
                    date = date
                )
            }
        ).flow
    }

    override suspend fun getAllCancelledClasses(
        divisionId: Int,
        batchId: Int,
        date: String
    ): Response<PresencifyApiResponse<CancelledClassListWithTotalCountDto>> {
        return classApi.getCancelledClasses(
            divisionId = divisionId,
            batchId = batchId,
            date = date,
            page = 1,
            limit = 10,
            getAll = true
        )
    }


}