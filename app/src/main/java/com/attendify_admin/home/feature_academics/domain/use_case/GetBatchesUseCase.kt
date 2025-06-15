package com.attendify_admin.home.feature_academics.domain.use_case

import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.dto.response.toBatch
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.common.domain.model.Batch
import com.attendify_admin.home.feature_academics.domain.repository.BatchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetBatchesUseCase @Inject constructor(
    private val batchRepository: BatchRepository,
) {
    operator fun invoke(
        semesterNumber: Int? = null,
        branchId: Int? = null,
        academicStartYear: Int? = null,
        academicEndYear: Int? = null,
        searchQuery: String? = null,
        page: Int,
        limit: Int,
    ): Flow<Resource<List<Batch>?>> = flow {

        emit(Resource.Loading())

        val response = runCatching {
            batchRepository.getBatches(
                semesterNumber,
                branchId,
                academicStartYear,
                academicEndYear,
                searchQuery,
                page,
                limit
            )
        }

        response.onSuccess { response ->
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()?.data?.map { it.toBatch() }))
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
