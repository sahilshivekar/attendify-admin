package com.attendify_admin.home.feature_users.domain.repository

import androidx.paging.PagingData
import com.attendify_admin.common.data.remote.dto.response.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.DropoutDto
import com.attendify_admin.common.data.remote.dto.response.StudentBatchDto
import com.attendify_admin.common.data.remote.dto.response.StudentDivisionDto
import com.attendify_admin.common.data.remote.dto.response.StudentDto
import com.attendify_admin.common.data.remote.dto.response.StudentFCMTokenDto
import com.attendify_admin.common.data.remote.dto.response.StudentSemesterDto
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
        schemeId: Int,
        admissionYear: String,
        admissionType: String,
        branchId: Int,
        studentImageFile: File?,
        parentEmail: String?
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
    suspend fun removeStudentImage(studentId: Int): Response<AttendifyApiResponse<StudentDto>>

    // Remove a student
    suspend fun removeStudent(studentId: Int): Response<AttendifyApiResponse<Unit>>

    // Get student details by ID
    suspend fun getStudentDetailsById(studentId: Int): Response<AttendifyApiResponse<StudentDto>>

    // Add student to semester
    suspend fun addStudentToSemester(requestBody: AddStudentToSemesterRequest): Response<AttendifyApiResponse<StudentSemesterDto>>

    // Remove student from semester
    suspend fun removeStudentFromSemester(studentSemesterId: Int): Response<AttendifyApiResponse<Unit>>

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

    suspend fun removeStudentFromDropout(
        studentId: Int,
        academicStartYear: Int,
        academicEndYear: Int,
    ): Response<AttendifyApiResponse<Unit>>

    suspend fun getDropoutById(dropoutId: Int): Response<AttendifyApiResponse<DropoutDto?>>

    suspend fun getDropoutDetailsOfStudent(studentId: Int): Response<AttendifyApiResponse<List<DropoutDto>?>>

    suspend fun addStudentFcmToken(requestBody: AddStudentFcmTokenRequest): Response<AttendifyApiResponse<StudentFCMTokenDto?>>

    suspend fun updateStudentFcmToken(requestBody: UpdateStudentFcmTokenRequest): Response<AttendifyApiResponse<StudentFCMTokenDto?>>

    suspend fun removeStudentFcmToken(studentId: Int): Response<AttendifyApiResponse<Unit>>
}

