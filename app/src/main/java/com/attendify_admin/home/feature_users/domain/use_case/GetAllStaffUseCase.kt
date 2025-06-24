package com.attendify_admin.home.feature_users.domain.use_case

import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.dto.response.toStaff
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.common.domain.model.Staff
import com.attendify_admin.home.feature_users.domain.repository.StaffRepository
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetAllStaffUseCase @Inject constructor(
    private val staffRepository: StaffRepository
) {
    operator fun invoke(
        searchQuery: String?,
        courseId: Int?
    ): Flow<Resource<ImmutableList<Staff>>> = flow {
        emit(Resource.Loading())

        val result = runCatching {
            staffRepository.getAllStaff(
                searchQuery = searchQuery,
                courseId = courseId
            )
        }

        result.onSuccess { res ->
            if (res.isSuccessful) {
                emit(Resource.Success(res.body()?.data?.staff?.map { it.toStaff() }?.toImmutableList()))
            } else {
                emit(Resource.Error(message = RemoteUtils.getErrorMessage(res)))
            }
        }

        result.onFailure { e ->
            emit(
                Resource.Error(
                    message = when (e) {
                        is IOException -> RemoteUtils.NETWORK_IO_ERROR_MESSAGE
                        else -> RemoteUtils.UNKNOWN_NETWORK_ERROR_MESSAGE
                    }
                )
            )
        }
    }
}
