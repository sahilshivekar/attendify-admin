package com.attendify_admin.home.users.domain.use_case

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.response_dto.Dropout
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.users.domain.repository.StudentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDropoutByIdUseCase @Inject constructor(private val studentRepository: StudentRepository) {
    operator fun invoke(dropoutId: Int): Flow<Resource<AttendifyApiResponse<Dropout?>>> {
        return RemoteUtils.responseFlow { studentRepository.getDropoutById(dropoutId) }
    }
}

