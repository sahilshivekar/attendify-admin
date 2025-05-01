package com.attendify_admin.users.domain.use_case

import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Student
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.users.domain.repository.StudentRepository
import kotlinx.coroutines.flow.Flow
import java.io.File
import javax.inject.Inject

class UpdateStudentImageUseCase @Inject constructor(private val studentRepository: StudentRepository) {
    operator fun invoke(
        studentId: String,
        studentImageFile: File
    ): Flow<Resource<AttendifyApiResponse<Student>>> {
        return RemoteUtils.responseFlow {
            studentRepository.updateStudentImage(
                studentId,
                studentImageFile
            )
        }
    }
}