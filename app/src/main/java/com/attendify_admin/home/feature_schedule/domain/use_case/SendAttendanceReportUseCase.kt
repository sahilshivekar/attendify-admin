package com.attendify_admin.home.feature_schedule.domain.use_case

import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.dto.response.toNoParentEmailStudents
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.common.domain.model.NoParentEmailStudents
import com.attendify_admin.home.feature_schedule.domain.repository.AttendanceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class SendAttendanceReportUseCase @Inject constructor(
    private val attendanceRepository: AttendanceRepository,
) {
    operator fun invoke(
        startDate: String,
        endDate: String,
        studentIds: List<String>,
        courseIds: List<String>,
        semesterId: Int,
    ): Flow<Resource<List<NoParentEmailStudents>?>> = flow {

        emit(Resource.Loading<List<NoParentEmailStudents>?>())

        val response = runCatching {
            attendanceRepository.sendAttendanceReport(
                startDate,
                endDate,
                studentIds,
                courseIds,
                semesterId
            )
        }

        response.onSuccess { response ->
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()?.data?.map { it.toNoParentEmailStudents() }))
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
