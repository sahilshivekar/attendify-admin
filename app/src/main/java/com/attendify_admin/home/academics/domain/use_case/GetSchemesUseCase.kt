package com.attendify_admin.home.academics.domain.use_case

import android.util.Log
import com.attendify_admin.home.academics.domain.repository.SchemeRepository
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Scheme
import com.attendify_admin.common.domain.RemoteUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// SchemeRepository Use Cases
class GetSchemesUseCase @Inject constructor(private val schemeRepository: SchemeRepository) {
    operator fun invoke(searchQuery: String?): Flow<Resource<AttendifyApiResponse<List<Scheme?>>>> {
        Log.d("use_case", "here in the schemes use case")
        return RemoteUtils.responseFlow { schemeRepository.getSchemes(searchQuery) }
    }
}