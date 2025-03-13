package com.edu.wiet_admin.users.data

import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Student
import com.edu.wiet_admin.common.data.remote.response_dto.StudentBatch
import com.edu.wiet_admin.common.data.remote.response_dto.StudentDivision
import com.edu.wiet_admin.common.data.remote.response_dto.StudentSemester
import com.edu.wiet_admin.users.data.dto.request.AddStudentToBatchRequest
import com.edu.wiet_admin.users.data.dto.request.AddStudentToDivisionRequest
import com.edu.wiet_admin.users.data.dto.request.AddStudentToSemesterRequest
import com.edu.wiet_admin.users.data.dto.request.ChangeStudentBatchRequest
import com.edu.wiet_admin.users.data.dto.request.ChangeStudentDivisionRequest
import com.edu.wiet_admin.users.data.dto.request.RemoveStudentFromSemesterRequest
import com.edu.wiet_admin.users.data.dto.request.RemoveStudentImageRequest
import com.edu.wiet_admin.users.data.dto.request.RemoveStudentRequest
import com.edu.wiet_admin.users.data.dto.request.UpdateStudentDetailsRequest
import com.edu.wiet_admin.users.data.dto.request.UpdateStudentPasswordRequest
import com.edu.wiet_admin.users.domain.repository.StudentRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import java.io.File
import java.util.Date

class StudentRepositoryImpl(
    private val studentApi: StudentApi
) : StudentRepository {

    override suspend fun getStudents(
        searchQuery: String?,
        branchId: Int?,
        semesterNumber: Int?,
        academicStartYearOfSemester: Int?,
        academicEndYearOfSemester: Int?,
        batchId: Int?,
        schemeId: Int?,
        divisionId: Int?,
        academicStatus: String?,
        admissionType: String?,
        admissionYear: Int?,
        currentBatch: Boolean?,
        currentDivision: Boolean?,
        studentStatus: String?,
        divisionCode: String?,
        batchCode: String?,
        page: Int,
        limit: Int
    ): Response<WietApiResponse<List<Student>>> {
        return studentApi.getStudents(
            searchQuery,
            branchId,
            semesterNumber,
            academicStartYearOfSemester,
            academicEndYearOfSemester,
            batchId,
            schemeId,
            divisionId,
            academicStatus,
            admissionType,
            admissionYear,
            currentBatch,
            currentDivision,
            studentStatus,
            divisionCode,
            batchCode,
            page,
            limit
        )
    }

    override suspend fun addStudent(
        prn: String,
        firstName: String,
        middleName: String?,
        lastName: String,
        email: String,
        phoneNumber: String,
        gender: String,
        dob: Date?,
        password: String,
        confirmPassword: String,
        schemeId: String,
        academicStatus: String,
        admissionYear: String,
        admissionType: String,
        branchId: String,
        studentImageFile: File?
    ): Response<WietApiResponse<Student>> {
        val prnBody = prn.toRequestBody("text/plain".toMediaTypeOrNull())
        val firstNameBody = firstName.toRequestBody("text/plain".toMediaTypeOrNull())
        val middleNameBody = middleName?.toRequestBody("text/plain".toMediaTypeOrNull())
        val lastNameBody = lastName.toRequestBody("text/plain".toMediaTypeOrNull())
        val emailBody = email.toRequestBody("text/plain".toMediaTypeOrNull())
        val phoneNumberBody = phoneNumber.toRequestBody("text/plain".toMediaTypeOrNull())
        val genderBody = gender.toRequestBody("text/plain".toMediaTypeOrNull())
        val dobBody = dob?.toString()?.toRequestBody("text/plain".toMediaTypeOrNull())
        val passwordBody = password.toRequestBody("text/plain".toMediaTypeOrNull())
        val confirmPasswordBody = confirmPassword.toRequestBody("text/plain".toMediaTypeOrNull())
        val schemeIdBody = schemeId.toRequestBody("text/plain".toMediaTypeOrNull())
        val academicStatusBody = academicStatus.toRequestBody("text/plain".toMediaTypeOrNull())
        val admissionYearBody = admissionYear.toRequestBody("text/plain".toMediaTypeOrNull())
        val admissionTypeBody = admissionType.toRequestBody("text/plain".toMediaTypeOrNull())
        val branchIdBody = branchId.toRequestBody("text/plain".toMediaTypeOrNull())
        val studentImagePart = if (studentImageFile != null) {
            val requestFile = studentImageFile.asRequestBody("image/*".toMediaTypeOrNull())
            MultipartBody.Part.createFormData(
                name = "studentImageFile",
                filename = studentImageFile.name,
                body = requestFile
            )
        } else {
            null
        }

        return studentApi.addStudent(
            prnBody,
            firstNameBody,
            middleNameBody,
            lastNameBody,
            emailBody,
            phoneNumberBody,
            genderBody,
            dobBody,
            passwordBody,
            confirmPasswordBody,
            schemeIdBody,
            academicStatusBody,
            admissionYearBody,
            admissionTypeBody,
            branchIdBody,
            studentImagePart
        )
    }

    override suspend fun updateStudentDetails(requestBody: UpdateStudentDetailsRequest): Response<WietApiResponse<Student>> {
        return studentApi.updateStudentDetails(requestBody)
    }

    override suspend fun updateStudentPassword(requestBody: UpdateStudentPasswordRequest): Response<WietApiResponse<Student>> {
        return studentApi.updateStudentPassword(requestBody)
    }

    override suspend fun updateStudentImage(
        studentId: String,
        studentImageFile: File
    ): Response<WietApiResponse<Student>> {
        val studentIdBody = studentId.toRequestBody("text/plain".toMediaTypeOrNull())
        val requestFile = studentImageFile.asRequestBody("image/*".toMediaTypeOrNull())
        val studentImagePart = MultipartBody.Part.createFormData(
            name = "studentImageFile",
            filename = studentImageFile.name,
            body = requestFile
        )
        return studentApi.updateStudentImage(
            studentIdBody,
            studentImagePart
        )
    }

    override suspend fun removeStudentImage(requestBody: RemoveStudentImageRequest): Response<WietApiResponse<Student>> {
        return studentApi.removeStudentImage(requestBody)
    }

    override suspend fun removeStudent(requestBody: RemoveStudentRequest): Response<WietApiResponse<Unit>> {
        return studentApi.removeStudent(requestBody)
    }

    override suspend fun getStudentDetailsById(studentId: Int): Response<WietApiResponse<Student>> {
        return studentApi.getStudentDetailsById(studentId)
    }

    override suspend fun addStudentToSemester(requestBody: AddStudentToSemesterRequest): Response<WietApiResponse<StudentSemester>> {
        return studentApi.addStudentToSemester(requestBody)
    }

    override suspend fun removeStudentFromSemester(requestBody: RemoveStudentFromSemesterRequest): Response<WietApiResponse<Unit>> {
        return studentApi.removeStudentFromSemester(requestBody)
    }

    override suspend fun addStudentToDivision(requestBody: AddStudentToDivisionRequest): Response<WietApiResponse<StudentDivision>> {
        return studentApi.addStudentToDivision(requestBody)
    }

    override suspend fun changeStudentDivision(requestBody: ChangeStudentDivisionRequest): Response<WietApiResponse<StudentDivision>> {
        return studentApi.changeStudentDivision(requestBody)
    }

    override suspend fun addStudentToBatch(requestBody: AddStudentToBatchRequest): Response<WietApiResponse<StudentBatch>> {
        return studentApi.addStudentToBatch(requestBody)
    }

    override suspend fun changeStudentBatch(requestBody: ChangeStudentBatchRequest): Response<WietApiResponse<StudentBatch>> {
        return studentApi.changeStudentBatch(requestBody)
    }

    override suspend fun getStudentSemestersById(studentId: Int): Response<WietApiResponse<List<StudentSemester>>> {
        return studentApi.getStudentSemestersById(studentId)
    }

    override suspend fun getStudentDivisionsById(
        studentId: Int,
        semesterNumber: Int?
    ): Response<WietApiResponse<List<StudentDivision>>> {
        return studentApi.getStudentDivisionsById(studentId, semesterNumber)
    }

    override suspend fun getStudentBatchesById(
        studentId: Int,
        semesterNumber: Int?
    ): Response<WietApiResponse<List<StudentBatch>>> {
        return studentApi.getStudentBatchesById(studentId, semesterNumber)
    }
}