package com.attendify_admin.home.users.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Dropout
import com.attendify_admin.common.data.remote.response_dto.Student
import com.attendify_admin.common.data.remote.response_dto.StudentBatch
import com.attendify_admin.common.data.remote.response_dto.StudentDivision
import com.attendify_admin.common.data.remote.response_dto.StudentFcmToken
import com.attendify_admin.common.data.remote.response_dto.StudentSemester
import com.attendify_admin.home.users.data.dto.request.AddDropoutRequest
import com.attendify_admin.home.users.data.dto.request.AddStudentFcmTokenRequest
import com.attendify_admin.home.users.data.dto.request.AddStudentToBatchRequest
import com.attendify_admin.home.users.data.dto.request.AddStudentToDivisionRequest
import com.attendify_admin.home.users.data.dto.request.AddStudentToSemesterRequest
import com.attendify_admin.home.users.data.dto.request.ChangeStudentBatchRequest
import com.attendify_admin.home.users.data.dto.request.ChangeStudentDivisionRequest
import com.attendify_admin.home.users.data.dto.request.RemoveDropoutRequest
import com.attendify_admin.home.users.data.dto.request.RemoveStudentFcmTokenRequest
import com.attendify_admin.home.users.data.dto.request.RemoveStudentFromSemesterRequest
import com.attendify_admin.home.users.data.dto.request.RemoveStudentImageRequest
import com.attendify_admin.home.users.data.dto.request.RemoveStudentRequest
import com.attendify_admin.home.users.data.dto.request.UpdateStudentDetailsRequest
import com.attendify_admin.home.users.data.dto.request.UpdateStudentFcmTokenRequest
import com.attendify_admin.home.users.data.dto.request.UpdateStudentPasswordRequest
import com.attendify_admin.home.users.domain.repository.StudentRepository
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
    ): Flow<PagingData<Student>> {

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
    ): Response<AttendifyApiResponse<Student>> {
        val prnBody = prn.toRequestBody("text/plain".toMediaTypeOrNull())
        val firstNameBody = firstName.toRequestBody("text/plain".toMediaTypeOrNull())
        val middleNameBody = middleName?.toRequestBody("text/plain".toMediaTypeOrNull())
        val lastNameBody = lastName.toRequestBody("text/plain".toMediaTypeOrNull())
        val emailBody = email.toRequestBody("text/plain".toMediaTypeOrNull())
        val phoneNumberBody = phoneNumber.toRequestBody("text/plain".toMediaTypeOrNull())
        val genderBody = gender.toRequestBody("text/plain".toMediaTypeOrNull())
        val dobBody = dob?.toRequestBody("text/plain".toMediaTypeOrNull())
        val schemeIdBody = schemeId.toRequestBody("text/plain".toMediaTypeOrNull())
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
//            passwordBody,
//            confirmPasswordBody,
            schemeIdBody,
            admissionYearBody,
            admissionTypeBody,
            branchIdBody,
            studentImagePart
        )
    }

    override suspend fun updateStudentDetails(requestBody: UpdateStudentDetailsRequest): Response<AttendifyApiResponse<Student>> {
        return studentApi.updateStudentDetails(requestBody)
    }

    override suspend fun updateStudentPassword(requestBody: UpdateStudentPasswordRequest): Response<AttendifyApiResponse<Student>> {
        return studentApi.updateStudentPassword(requestBody)
    }

    override suspend fun updateStudentImage(
        studentId: Int,
        studentImageFile: File,
    ): Response<AttendifyApiResponse<Student>> {
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

    override suspend fun removeStudentImage(requestBody: RemoveStudentImageRequest): Response<AttendifyApiResponse<Student>> {
        return studentApi.removeStudentImage(requestBody)
    }

    override suspend fun removeStudent(requestBody: RemoveStudentRequest): Response<AttendifyApiResponse<Unit>> {
        return studentApi.removeStudent(requestBody)
    }

    override suspend fun getStudentDetailsById(studentId: Int): Response<AttendifyApiResponse<Student>> {
        return studentApi.getStudentDetailsById(studentId)
    }

    override suspend fun addStudentToSemester(requestBody: AddStudentToSemesterRequest): Response<AttendifyApiResponse<StudentSemester>> {
        return studentApi.addStudentToSemester(requestBody)
    }

    override suspend fun removeStudentFromSemester(requestBody: RemoveStudentFromSemesterRequest): Response<AttendifyApiResponse<Unit>> {
        return studentApi.removeStudentFromSemester(requestBody)
    }

    override suspend fun addStudentToDivision(requestBody: AddStudentToDivisionRequest): Response<AttendifyApiResponse<StudentDivision>> {
        return studentApi.addStudentToDivision(requestBody)
    }

    override suspend fun changeStudentDivision(requestBody: ChangeStudentDivisionRequest): Response<AttendifyApiResponse<StudentDivision>> {
        return studentApi.changeStudentDivision(requestBody)
    }

    override suspend fun addStudentToBatch(requestBody: AddStudentToBatchRequest): Response<AttendifyApiResponse<StudentBatch>> {
        return studentApi.addStudentToBatch(requestBody)
    }

    override suspend fun changeStudentBatch(requestBody: ChangeStudentBatchRequest): Response<AttendifyApiResponse<StudentBatch>> {
        return studentApi.changeStudentBatch(requestBody)
    }

    override suspend fun getStudentSemestersById(studentId: Int): Response<AttendifyApiResponse<List<StudentSemester>>> {
        return studentApi.getStudentSemestersById(studentId)
    }

    override suspend fun getStudentDivisionsById(
        studentId: Int,
        semesterNumber: Int?,
    ): Response<AttendifyApiResponse<List<StudentDivision>>> {
        return studentApi.getStudentDivisionsById(studentId, semesterNumber)
    }

    override suspend fun getStudentBatchesById(
        studentId: Int,
        semesterNumber: Int?,
    ): Response<AttendifyApiResponse<List<StudentBatch>>> {
        return studentApi.getStudentBatchesById(studentId, semesterNumber)
    }

    override suspend fun addStudentToDropout(requestBody: AddDropoutRequest): Response<AttendifyApiResponse<Dropout?>> {
        return studentApi.addStudentToDropout(requestBody)
    }

    override suspend fun removeStudentFromDropout(requestBody: RemoveDropoutRequest): Response<AttendifyApiResponse<Unit>> {
        return studentApi.removeStudentFromDropout(requestBody)
    }

    override suspend fun getDropoutById(dropoutId: Int): Response<AttendifyApiResponse<Dropout?>> {
        return studentApi.getDropoutById(dropoutId)
    }

    override suspend fun getDropoutDetailsOfStudent(studentId: Int): Response<AttendifyApiResponse<List<Dropout>?>> {
        return studentApi.getDropoutDetailsOfStudent(studentId)
    }

    override suspend fun addStudentFcmToken(requestBody: AddStudentFcmTokenRequest): Response<AttendifyApiResponse<StudentFcmToken?>> {
        return studentApi.addStudentFcmToken(requestBody)
    }

    override suspend fun updateStudentFcmToken(requestBody: UpdateStudentFcmTokenRequest): Response<AttendifyApiResponse<StudentFcmToken?>> {
        return studentApi.updateStudentFcmToken(requestBody)
    }

    override suspend fun removeStudentFcmToken(requestBody: RemoveStudentFcmTokenRequest): Response<AttendifyApiResponse<Unit>> {
        return studentApi.removeStudentFcmToken(requestBody)
    }

}