package com.attendify_admin.home.feature_users.domain.repository

import androidx.paging.PagingData
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.DropoutDto
import com.attendify_admin.common.data.remote.dto.response.StudentBatchDto
import com.attendify_admin.common.data.remote.dto.response.StudentDivisionDto
import com.attendify_admin.common.data.remote.dto.response.StudentDto
import com.attendify_admin.common.data.remote.dto.response.StudentFCMTokenDto
import com.attendify_admin.common.data.remote.dto.response.StudentSemesterDto
import com.attendify_admin.home.feature_users.data.dto.request.AddDropoutRequest
import com.attendify_admin.home.feature_users.data.dto.request.AddStudentFcmTokenRequest
import com.attendify_admin.home.feature_users.data.dto.request.AddStudentToBatchRequest
import com.attendify_admin.home.feature_users.data.dto.request.AddStudentToDivisionRequest
import com.attendify_admin.home.feature_users.data.dto.request.AddStudentToSemesterRequest
import com.attendify_admin.home.feature_users.data.dto.request.ChangeStudentBatchRequest
import com.attendify_admin.home.feature_users.data.dto.request.ChangeStudentDivisionRequest
import com.attendify_admin.home.feature_users.data.dto.request.RemoveDropoutRequest
import com.attendify_admin.home.feature_users.data.dto.request.RemoveStudentFcmTokenRequest
import com.attendify_admin.home.feature_users.data.dto.request.RemoveStudentFromSemesterRequest
import com.attendify_admin.home.feature_users.data.dto.request.RemoveStudentImageRequest
import com.attendify_admin.home.feature_users.data.dto.request.RemoveStudentRequest
import com.attendify_admin.home.feature_users.data.dto.request.UpdateStudentDetailsRequest
import com.attendify_admin.home.feature_users.data.dto.request.UpdateStudentFcmTokenRequest
import com.attendify_admin.home.feature_users.data.dto.request.UpdateStudentPasswordRequest
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import java.io.File

interface StudentRepository {

    // Get all students
    fun getStudents(
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
    ): Flow<PagingData<StudentDto>>

    // Add a student
    suspend fun addStudent(
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
        studentImageFile: File?
    ): Response<AttendifyApiResponse<StudentDto>>

    // Update student details
    suspend fun updateStudentDetails(requestBody: UpdateStudentDetailsRequest): Response<AttendifyApiResponse<StudentDto>>

    // Update student password
    suspend fun updateStudentPassword(requestBody: UpdateStudentPasswordRequest): Response<AttendifyApiResponse<StudentDto>>

    // Update student image
    suspend fun updateStudentImage(
        studentId: Int,
        studentImageFile: File
    ): Response<AttendifyApiResponse<StudentDto>>

    // Remove student image
    suspend fun removeStudentImage(requestBody: RemoveStudentImageRequest): Response<AttendifyApiResponse<StudentDto>>

    // Remove a student
    suspend fun removeStudent(requestBody: RemoveStudentRequest): Response<AttendifyApiResponse<Unit>>

    // Get student details by ID
    suspend fun getStudentDetailsById(studentId: Int): Response<AttendifyApiResponse<StudentDto>>

    // Add student to semester
    suspend fun addStudentToSemester(requestBody: AddStudentToSemesterRequest): Response<AttendifyApiResponse<StudentSemesterDto>>

    // Remove student from semester
    suspend fun removeStudentFromSemester(requestBody: RemoveStudentFromSemesterRequest): Response<AttendifyApiResponse<Unit>>

    // Add student to division
    suspend fun addStudentToDivision(requestBody: AddStudentToDivisionRequest): Response<AttendifyApiResponse<StudentDivisionDto>>

    // Change student division
    suspend fun changeStudentDivision(requestBody: ChangeStudentDivisionRequest): Response<AttendifyApiResponse<StudentDivisionDto>>

    // Add student to batch
    suspend fun addStudentToBatch(requestBody: AddStudentToBatchRequest): Response<AttendifyApiResponse<StudentBatchDto>>

    // Change student batch
    suspend fun changeStudentBatch(requestBody: ChangeStudentBatchRequest): Response<AttendifyApiResponse<StudentBatchDto>>

    // Get student semesters by ID
    suspend fun getStudentSemestersById(studentId: Int): Response<AttendifyApiResponse<List<StudentSemesterDto>?>>

    // Get student divisions by ID
    suspend fun getStudentDivisionsById(studentId: Int, semesterNumber: Int?): Response<AttendifyApiResponse<List<StudentDivisionDto>?>>

    // Get student batches by ID
    suspend fun getStudentBatchesById(studentId: Int, semesterNumber: Int?): Response<AttendifyApiResponse<List<StudentBatchDto>?>>

    suspend fun addStudentToDropout(requestBody: AddDropoutRequest): Response<AttendifyApiResponse<DropoutDto?>>

    suspend fun removeStudentFromDropout(requestBody: RemoveDropoutRequest): Response<AttendifyApiResponse<Unit>>

    suspend fun getDropoutById(dropoutId: Int): Response<AttendifyApiResponse<DropoutDto?>>

    suspend fun getDropoutDetailsOfStudent(studentId: Int): Response<AttendifyApiResponse<List<DropoutDto>?>>

    suspend fun addStudentFcmToken(requestBody: AddStudentFcmTokenRequest): Response<AttendifyApiResponse<StudentFCMTokenDto?>>

    suspend fun updateStudentFcmToken(requestBody: UpdateStudentFcmTokenRequest): Response<AttendifyApiResponse<StudentFCMTokenDto?>>

    suspend fun removeStudentFcmToken(requestBody: RemoveStudentFcmTokenRequest): Response<AttendifyApiResponse<Unit>>
}

