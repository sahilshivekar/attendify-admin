package com.edu.wiet_admin.users.domain.use_case

import com.edu.wiet_admin.common.data.remote.Resource
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Student
import com.edu.wiet_admin.common.domain.RemoteUtils
import com.edu.wiet_admin.users.domain.repository.StudentRepository
import kotlinx.coroutines.flow.Flow
import java.io.File
import javax.inject.Inject

class AddStudentUseCase @Inject constructor(private val studentRepository: StudentRepository) {
    operator fun invoke(
        prn: String,
        firstName: String,
        middleName: String?,
        lastName: String,
        email: String,
        phoneNumber: String,
        gender: String,
        dob: String?,
        schemeId: String,
        academicStatus: String,
        admissionYear: String,
        admissionType: String,
        branchId: String,
        studentImageFile: File?
    ): Flow<Resource<WietApiResponse<Student>>> {
        return RemoteUtils.responseFlow {
            studentRepository.addStudent(
                prn,
                firstName,
                middleName,
                lastName,
                email,
                phoneNumber,
                gender,
                dob,
                schemeId,
                academicStatus,
                admissionYear,
                admissionType,
                branchId,
                studentImageFile
            )
        }
    }
}