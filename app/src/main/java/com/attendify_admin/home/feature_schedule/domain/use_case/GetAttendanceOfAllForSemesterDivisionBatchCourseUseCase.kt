package com.attendify_admin.home.feature_schedule.domain.use_case

import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.dto.response.toAttendanceAllStudents
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.common.domain.model.AttendanceAllStudents
import com.attendify_admin.home.feature_schedule.domain.repository.AttendanceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetAttendanceOfAllForSemesterDivisionBatchCourseUseCase @Inject constructor(
    private val attendanceRepository: AttendanceRepository
) {
    operator fun invoke(
        courseId: Int,
        semesterId: Int,
        divisionId: Int,
        batchId: Int,
        startDate: String,
        endDate: String,
    ): Flow<Resource<AttendanceAllStudents?>> = flow {

        emit(Resource.Loading<AttendanceAllStudents?>())

        val response = runCatching {
            attendanceRepository.getAttendanceOfAllForSemesterDivisionBatchCourse(
                courseId,
                semesterId,
                divisionId,
                batchId,
                startDate,
                endDate
            )
        }

        response.onSuccess { response ->
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()?.data?.toAttendanceAllStudents()))
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
