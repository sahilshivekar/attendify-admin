package com.presencify_admin.home.feature_schedule.domain.use_case

import com.presencify_admin.common.data.remote.Resource
import com.presencify_admin.common.domain.RemoteUtils
import com.presencify_admin.home.feature_schedule.domain.repository.AttendanceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class MarkStudentAttendanceByBLEsessionUUIDUseCase @Inject constructor(
    private val attendanceRepository: AttendanceRepository
) {
    operator fun invoke(
        bleSessionUUID: String,
        studentId: Int,
    ): Flow<Resource<String?>> = flow {

        emit(Resource.Loading<String?>())

        val response = runCatching {
            attendanceRepository.markStudentAttendanceByBLEsessionUUID(
                bleSessionUUID,
                studentId
            )
        }

        response.onSuccess { response ->
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()?.data))
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
