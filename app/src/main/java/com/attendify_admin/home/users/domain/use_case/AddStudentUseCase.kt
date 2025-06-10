package com.attendify_admin.home.users.domain.use_case

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.data.dto.response.Student
import com.attendify_admin.common.domain.RemoteUtils
import com.attendify_admin.home.users.domain.repository.StudentRepository
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
        schemeId: Int,
        admissionYear: String,
        admissionType: String,
        branchId: Int,
        studentImageFile: File?
    ): Flow<Resource<AttendifyApiResponse<Student>>> {
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
                admissionYear,
                admissionType,
                branchId,
                studentImageFile
            )
        }
    }
}