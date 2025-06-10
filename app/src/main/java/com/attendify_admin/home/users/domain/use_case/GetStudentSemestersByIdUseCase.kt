package com.attendify_admin.home.users.domain.use_case

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.dto.response.StudentSemester
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.users.domain.repository.StudentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStudentSemestersByIdUseCase @Inject constructor(private val studentRepository: StudentRepository) {
    operator fun invoke(studentId: Int): Flow<Resource<AttendifyApiResponse<List<StudentSemester>>>> {
        return RemoteUtils.responseFlow { studentRepository.getStudentSemestersById(studentId) }
    }
}