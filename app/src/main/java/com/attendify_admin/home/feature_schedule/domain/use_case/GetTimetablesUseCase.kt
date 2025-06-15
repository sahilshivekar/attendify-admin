package com.attendify_admin.home.feature_schedule.domain.use_case

// TimetableRepository Use Cases
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.dto.response.toTimetable
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.common.domain.model.Timetable
import com.attendify_admin.home.feature_schedule.domain.repository.TimetableRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetTimetablesUseCase @Inject constructor(
    private val timetableRepository: TimetableRepository,
) {
    operator fun invoke(
        semesterNumber: Int?,
        academicStartYearOfSemester: Int?,
        academicEndYearOfSemester: Int?,
        page: Int,
        limit: Int,
    ): Flow<Resource<List<Timetable>?>> = flow {

        emit(Resource.Loading<List<Timetable>?>())

        val response = runCatching {
            timetableRepository.getTimetables(
                semesterNumber,
                academicStartYearOfSemester,
                academicEndYearOfSemester,
                page,
                limit
            )
        }

        response.onSuccess { response ->
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()?.data?.map { it.toTimetable() }))
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
