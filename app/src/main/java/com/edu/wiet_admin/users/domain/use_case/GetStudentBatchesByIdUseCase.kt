package com.edu.wiet_admin.users.domain.use_case

import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.StudentBatch
import com.edu.wiet_admin.common.domain.RemoteUtils
import com.edu.wiet_admin.users.domain.repository.StudentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStudentBatchesByIdUseCase @Inject constructor(private val studentRepository: StudentRepository) {
    operator fun invoke(studentId: Int, semesterNumber: Int?): Flow<Resource<WietApiResponse<List<StudentBatch>>>> {
        return RemoteUtils.responseFlow {
            studentRepository.getStudentBatchesById(
                studentId,
                semesterNumber
            )
        }
    }
}