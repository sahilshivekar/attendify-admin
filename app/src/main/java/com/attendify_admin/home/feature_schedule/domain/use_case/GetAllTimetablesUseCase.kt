package com.attendify_admin.home.feature_schedule.domain.use_case


import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.dto.response.toTimetable
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.common.domain.model.Timetable
import com.attendify_admin.home.feature_schedule.domain.repository.TimetableRepository
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetAllTimetablesUseCase @Inject constructor(
    private val timetableRepository: TimetableRepository,
) {
    operator fun invoke(
        semesterNumber: Int?,
        academicStartYearOfSemester: Int?,
        academicEndYearOfSemester: Int?
    ): Flow<Resource<ImmutableList<Timetable>>> = flow {
        emit(Resource.Loading())

        val response = runCatching {
            timetableRepository.getAllTimetables(
                semesterNumber,
                academicStartYearOfSemester,
                academicEndYearOfSemester
            )
        }

        response.onSuccess { res ->
            if (res.isSuccessful) {
                emit(Resource.Success(res.body()?.data?.timetables?.map { it.toTimetable() }?.toImmutableList()))
            } else {
                emit(Resource.Error(message = RemoteUtils.getErrorMessage(res)))
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
