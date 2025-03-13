package com.edu.wiet_admin.users.domain.use_case

import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Student
import com.edu.wiet_admin.common.domain.RemoteUtils
import com.edu.wiet_admin.users.domain.repository.StudentRepository
import kotlinx.coroutines.flow.Flow
import java.io.File
import javax.inject.Inject

class UpdateStudentImageUseCase @Inject constructor(private val studentRepository: StudentRepository) {
    operator fun invoke(
        studentId: String,
        studentImageFile: File
    ): Flow<Resource<WietApiResponse<Student>>> {
        return RemoteUtils.responseFlow {
            studentRepository.updateStudentImage(
                studentId,
                studentImageFile
            )
        }
    }
}