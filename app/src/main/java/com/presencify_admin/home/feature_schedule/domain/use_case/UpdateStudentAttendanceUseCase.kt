package com.presencify_admin.home.feature_schedule.domain.use_case

import com.presencify_admin.common.data.remote.Resource
import com.presencify_admin.common.data.remote.dto.response.toAttendanceStudent
import com.presencify_admin.common.domain.RemoteUtils
import com.presencify_admin.common.domain.model.AttendanceStudent
import com.presencify_admin.home.feature_schedule.data.remote.dto.request.UpdateStudentAttendanceRequest
import com.presencify_admin.home.feature_schedule.domain.repository.AttendanceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class UpdateStudentAttendanceUseCase @Inject constructor(
    private val attendanceRepository: AttendanceRepository
) {
    operator fun invoke(
        requestBody: UpdateStudentAttendanceRequest
    ): Flow<Resource<AttendanceStudent?>> = flow {

        emit(Resource.Loading<AttendanceStudent?>())

        val response = runCatching {
            attendanceRepository.updateStudentAttendance(requestBody)
        }

        response.onSuccess { response ->
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()?.data?.toAttendanceStudent()))
            } else {
                val errorMessage = RemoteUtils.getErrorMessage(response)
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
