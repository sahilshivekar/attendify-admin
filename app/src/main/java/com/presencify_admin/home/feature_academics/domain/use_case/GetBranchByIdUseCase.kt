package com.presencify_admin.home.feature_academics.domain.use_case

import com.presencify_admin.common.data.remote.Resource
import com.presencify_admin.common.data.remote.dto.response.toBranch
import com.presencify_admin.common.domain.RemoteUtils
import com.presencify_admin.common.domain.model.Branch
import com.presencify_admin.home.feature_academics.domain.repository.BranchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetBranchByIdUseCase @Inject constructor(
    private val branchRepository: BranchRepository
) {
    operator fun invoke(branchId: Int): Flow<Resource<Branch>> = flow {

        emit(Resource.Loading<Branch>())

        val response = runCatching {
            branchRepository.getBranchById(branchId)
        }

        response.onSuccess { response ->
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()?.data?.toBranch()))
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
