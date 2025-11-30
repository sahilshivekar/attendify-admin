package com.presencify_admin.home.feature_schedule.domain.use_case

import com.presencify_admin.common.data.remote.Resource
import com.presencify_admin.common.data.remote.dto.response.toClass
import com.presencify_admin.common.domain.RemoteUtils
import com.presencify_admin.common.domain.model.Class
import com.presencify_admin.home.feature_schedule.domain.repository.ClassRepository
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetAllClassesUseCase @Inject constructor(
    private val classRepository: ClassRepository,
) {
    operator fun invoke(
        searchQuery: String? = null,
        timetableId: Int? = null,
        divisionId: Int? = null,
        startTime: String? = null,
        endTime: String? = null,
        activeFrom: String? = null,
        activeTill: String? = null,
        instructorId: Int? = null,
        dayOfWeek: String? = null,
        roomId: Int? = null,
        batchId: Int? = null,
        classType: String? = null,
        courseId: Int? = null,
        semesterId: Int? = null,
    ): Flow<Resource<ImmutableList<Class>>> = flow {

        emit(Resource.Loading())

        val response = runCatching {
            classRepository.getAllClasses(
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
                semesterId
            )
        }

        response.onSuccess { res ->
            if (res.isSuccessful) {
                emit(Resource.Success(res.body()?.data?.classes?.map { it.toClass() }?.toImmutableList()))
            } else {
                val errorMessage = RemoteUtils.getErrorMessage(res)
                emit(Resource.Error(message = errorMessage))
            }
        }

        response.onFailure { exception ->
            when (exception) {
                is IOException -> emit(Resource.Error(message = RemoteUtils.NETWORK_IO_ERROR_MESSAGE))
                else -> emit(Resource.Error(message = RemoteUtils.UNKNOWN_NETWORK_ERROR_MESSAGE))
            }
        }
    }
}
