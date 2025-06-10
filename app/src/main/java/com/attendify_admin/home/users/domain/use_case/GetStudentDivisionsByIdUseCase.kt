package com.attendify_admin.home.users.domain.use_case

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.dto.response.StudentDivision
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.users.domain.repository.StudentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStudentDivisionsByIdUseCase @Inject constructor(private val studentRepository: StudentRepository) {
    operator fun invoke(studentId: Int, semesterNumber: Int?): Flow<Resource<AttendifyApiResponse<List<StudentDivision>>>> {
        return RemoteUtils.responseFlow {
            studentRepository.getStudentDivisionsById(
                studentId,
                semesterNumber
            )
        }
    }
}