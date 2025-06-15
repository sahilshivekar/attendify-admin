package com.attendify_admin.feature_admin_mgt.domain.use_case

import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.feature_admin_auth.data.remote.dto.responses.toAdmin
import com.attendify_admin.feature_admin_auth.domain.model.Admin
import com.attendify_admin.feature_admin_mgt.domain.repository.AdminMgtRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetAdminsUseCase @Inject constructor(
    private val adminMgtRepository: AdminMgtRepository
) {
    operator fun invoke(
        searchQuery: String?,
        sortBy: String?,
        sortOrder: String?,
        page: Int?,
        limit: Int?
    ): Flow<Resource<List<Admin>?>> = flow {

        emit(Resource.Loading<List<Admin>?>())

        val response = runCatching {
            adminMgtRepository.getAdmins(searchQuery, sortBy, sortOrder, page, limit)
        }

        response.onSuccess { response ->
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()?.data?.map { it.toAdmin() }))
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

