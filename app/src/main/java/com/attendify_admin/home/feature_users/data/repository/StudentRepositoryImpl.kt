package com.attendify_admin.home.feature_users.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.attendify_admin.common.data.remote.dto.response.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.DropoutDto
import com.attendify_admin.common.data.remote.dto.response.StudentBatchDto
import com.attendify_admin.common.data.remote.dto.response.StudentDivisionDto
import com.attendify_admin.common.data.remote.dto.response.StudentDto
import com.attendify_admin.common.data.remote.dto.response.StudentFCMTokenDto
import com.attendify_admin.common.data.remote.dto.response.StudentSemesterDto
import com.attendify_admin.home.feature_users.data.remote.StudentApi
import com.attendify_admin.home.feature_users.data.remote.dto.request.AddDropoutRequest
import com.attendify_admin.home.feature_users.data.remote.dto.request.AddStudentFcmTokenRequest
import com.attendify_admin.home.feature_users.data.remote.dto.request.AddStudentToBatchRequest
import com.attendify_admin.home.feature_users.data.remote.dto.request.AddStudentToDivisionRequest
import com.attendify_admin.home.feature_users.data.remote.dto.request.AddStudentToSemesterRequest
import com.attendify_admin.home.feature_users.data.remote.dto.request.ChangeStudentBatchRequest
import com.attendify_admin.home.feature_users.data.remote.dto.request.ChangeStudentDivisionRequest
import com.attendify_admin.home.feature_users.data.remote.dto.request.UpdateStudentDetailsRequest
import com.attendify_admin.home.feature_users.data.remote.dto.request.UpdateStudentFcmTokenRequest
import com.attendify_admin.home.feature_users.data.remote.dto.request.UpdateStudentPasswordRequest
import com.attendify_admin.home.feature_users.domain.repository.StudentRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import java.io.File

class StudentRepositoryImpl(
    private val studentApi: StudentApi,
) : StudentRepository {

    override fun getStudents(
        searchQuery: String?,
        branchIds: List<Int>?,
        semesterNumbers: List<Int>?,
        academicStartYearOfSemester: Int?,
        academicEndYearOfSemester: Int?,
        batchId: Int?,
        schemeId: Int?,
        divisionId: Int?,
        academicStatuses: List<String>?,
        admissionTypes: List<String>?,
        admissionYear: Int?,
        currentBatch: Boolean?,
        currentDivision: Boolean?,
        currentSemester: Boolean?,
        divisionCode: String?,
        batchCode: String?,
    ): Flow<PagingData<StudentDto>> {

        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                GetStudentsPagingSource(
                    studentApi = studentApi,
                    searchQuery,
                    branchIds,
                    semesterNumbers,
                    academicStartYearOfSemester,
                    academicEndYearOfSemester,
                    batchId,
                    schemeId,
                    divisionId,
                    academicStatuses,
                    admissionTypes,
                    admissionYear,
                    currentBatch,
                    currentDivision,
                    currentSemester,
                    divisionCode,
                    batchCode
                )
            }
        ).flow

    }

    override suspend fun addStudent(
        prn: String,
        firstName: String,
        middleName: String?,
        lastName: String,
        email: String,
        phoneNumber: String,
        gender: String,
        dob: String?,
//        password: String,
//        confirmPassword: String,
        schemeId: Int,
        admissionYear: String,
        admissionType: String,
        branchId: Int,
        studentImageFile: File?,
        parentEmail: String?,
    ): Response<AttendifyApiResponse<StudentDto>> {
        val prnBody = prn.toRequestBody("text/plain".toMediaTypeOrNull())
        val firstNameBody = firstName.toRequestBody("text/plain".toMediaTypeOrNull())
        val middleNameBody = middleName?.toRequestBody("text/plain".toMediaTypeOrNull())
        val lastNameBody = lastName.toRequestBody("text/plain".toMediaTypeOrNull())
        val emailBody = email.toRequestBody("text/plain".toMediaTypeOrNull())
        val phoneNumberBody = phoneNumber.toRequestBody("text/plain".toMediaTypeOrNull())
        val genderBody = gender.toRequestBody("text/plain".toMediaTypeOrNull())
        val dobBody = dob?.toRequestBody("text/plain".toMediaTypeOrNull())
        val schemeIdBody = schemeId.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        val admissionYearBody = admissionYear.toRequestBody("text/plain".toMediaTypeOrNull())
        val admissionTypeBody = admissionType.toRequestBody("text/plain".toMediaTypeOrNull())
        val branchIdBody = branchId.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        val parentEmailBody = parentEmail.toString().toRequestBody("text/plain".toMediaTypeOrNull())
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
            schemeIdBody,
            admissionYearBody,
            admissionTypeBody,
            branchIdBody,
            studentImagePart,
            parentEmailBody
        )
    }

    override suspend fun updateStudentDetails(requestBody: UpdateStudentDetailsRequest): Response<AttendifyApiResponse<StudentDto>> {
        return studentApi.updateStudentDetails(requestBody)
    }

    override suspend fun updateStudentPassword(requestBody: UpdateStudentPasswordRequest): Response<AttendifyApiResponse<StudentDto>> {
        return studentApi.updateStudentPassword(requestBody)
    }

    override suspend fun updateStudentImage(
        studentId: Int,
        studentImageFile: File,
    ): Response<AttendifyApiResponse<StudentDto>> {
        val studentIdBody = studentId.toString().toRequestBody("text/plain".toMediaTypeOrNull())
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

    override suspend fun removeStudentImage(studentId: Int): Response<AttendifyApiResponse<StudentDto>> {
        return studentApi.removeStudentImage(studentId)
    }

    override suspend fun removeStudent(studentId: Int): Response<AttendifyApiResponse<Unit>> {
        return studentApi.removeStudent(studentId)
    }

    override suspend fun getStudentDetailsById(studentId: Int): Response<AttendifyApiResponse<StudentDto>> {
        return studentApi.getStudentDetailsById(studentId)
    }

    override suspend fun addStudentToSemester(requestBody: AddStudentToSemesterRequest): Response<AttendifyApiResponse<StudentSemesterDto>> {
        return studentApi.addStudentToSemester(requestBody)
    }

    override suspend fun removeStudentFromSemester(studentSemesterId: Int): Response<AttendifyApiResponse<Unit>> {
        return studentApi.removeStudentFromSemester(studentSemesterId)
    }

    override suspend fun addStudentToDivision(requestBody: AddStudentToDivisionRequest): Response<AttendifyApiResponse<StudentDivisionDto>> {
        return studentApi.addStudentToDivision(requestBody)
    }

    override suspend fun changeStudentDivision(requestBody: ChangeStudentDivisionRequest): Response<AttendifyApiResponse<StudentDivisionDto>> {
        return studentApi.changeStudentDivision(requestBody)
    }

    override suspend fun addStudentToBatch(requestBody: AddStudentToBatchRequest): Response<AttendifyApiResponse<StudentBatchDto>> {
        return studentApi.addStudentToBatch(requestBody)
    }

    override suspend fun changeStudentBatch(requestBody: ChangeStudentBatchRequest): Response<AttendifyApiResponse<StudentBatchDto>> {
        return studentApi.changeStudentBatch(requestBody)
    }

    override suspend fun getStudentSemestersById(studentId: Int): Response<AttendifyApiResponse<List<StudentSemesterDto>?>> {
        return studentApi.getStudentSemestersById(studentId)
    }

    override suspend fun getStudentDivisionsById(
        studentId: Int,
        semesterNumber: Int?,
    ): Response<AttendifyApiResponse<List<StudentDivisionDto>?>> {
        return studentApi.getStudentDivisionsById(studentId, semesterNumber)
    }

    override suspend fun getStudentBatchesById(
        studentId: Int,
        semesterNumber: Int?,
    ): Response<AttendifyApiResponse<List<StudentBatchDto>?>> {
        return studentApi.getStudentBatchesById(studentId, semesterNumber)
    }

    override suspend fun addStudentToDropout(requestBody: AddDropoutRequest): Response<AttendifyApiResponse<DropoutDto?>> {
        return studentApi.addStudentToDropout(requestBody)
    }

    override suspend fun removeStudentFromDropout(
        studentId: Int,
        academicStartYear: Int,
        academicEndYear: Int,
    ): Response<AttendifyApiResponse<Unit>> {
        return studentApi.removeStudentFromDropout(
            studentId,
            academicStartYear,
            academicEndYear
        )
    }

    override suspend fun getDropoutById(dropoutId: Int): Response<AttendifyApiResponse<DropoutDto?>> {
        return studentApi.getDropoutById(dropoutId)
    }

    override suspend fun getDropoutDetailsOfStudent(studentId: Int): Response<AttendifyApiResponse<List<DropoutDto>?>> {
        return studentApi.getDropoutDetailsOfStudent(studentId)
    }

    override suspend fun addStudentFcmToken(requestBody: AddStudentFcmTokenRequest): Response<AttendifyApiResponse<StudentFCMTokenDto?>> {
        return studentApi.addStudentFcmToken(requestBody)
    }

    override suspend fun updateStudentFcmToken(requestBody: UpdateStudentFcmTokenRequest): Response<AttendifyApiResponse<StudentFCMTokenDto?>> {
        return studentApi.updateStudentFcmToken(requestBody)
    }

    override suspend fun removeStudentFcmToken(studentId: Int): Response<AttendifyApiResponse<Unit>> {
        return studentApi.removeStudentFcmToken(studentId)
    }

}