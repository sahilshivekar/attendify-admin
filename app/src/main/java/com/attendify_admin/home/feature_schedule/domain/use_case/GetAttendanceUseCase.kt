package com.attendify_admin.home.feature_schedule.domain.use_case

import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.dto.response.toAttendanceStudent
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.common.domain.model.AttendanceStudent
import com.attendify_admin.home.feature_schedule.domain.repository.AttendanceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetAttendanceUseCase @Inject constructor(
    private val attendanceRepository: AttendanceRepository,
) {
    operator fun invoke(
        date: String?,
        attendanceId: Int?,
        classId: Int?,
        studentId: Int?,
        courseId: Int?,
        semesterId: Int?,
        divisionId: Int?,
    ): Flow<Resource<List<AttendanceStudent>?>> = flow {

        emit(Resource.Loading<List<AttendanceStudent>?>())

        val response = runCatching {
            attendanceRepository.getAttendance(
                date,
                attendanceId,
                classId,
                studentId,
                courseId,
                semesterId,
                divisionId
            )
        }

        response.onSuccess { response ->
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()?.data?.map { it.toAttendanceStudent() }))
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
