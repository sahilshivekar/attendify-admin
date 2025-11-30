package com.presencify_admin.home.feature_users.domain.repository

import androidx.paging.PagingData
import com.presencify_admin.common.data.remote.dto.response.PresencifyApiResponse
import com.presencify_admin.common.data.remote.dto.response.DropoutDto
import com.presencify_admin.common.data.remote.dto.response.StudentBatchDto
import com.presencify_admin.common.data.remote.dto.response.StudentDivisionDto
import com.presencify_admin.common.data.remote.dto.response.StudentDto
import com.presencify_admin.common.data.remote.dto.response.StudentFCMTokenDto
import com.presencify_admin.common.data.remote.dto.response.StudentListWithTotalDto
import com.presencify_admin.common.data.remote.dto.response.StudentSemesterDto
import com.presencify_admin.home.feature_users.data.remote.dto.request.AddDropoutRequest
import com.presencify_admin.home.feature_users.data.remote.dto.request.AddStudentFcmTokenRequest
import com.presencify_admin.home.feature_users.data.remote.dto.request.AddStudentToBatchRequest
import com.presencify_admin.home.feature_users.data.remote.dto.request.AddStudentToDivisionRequest
import com.presencify_admin.home.feature_users.data.remote.dto.request.AddStudentToSemesterRequest
import com.presencify_admin.home.feature_users.data.remote.dto.request.ChangeStudentBatchRequest
import com.presencify_admin.home.feature_users.data.remote.dto.request.ChangeStudentDivisionRequest
import com.presencify_admin.home.feature_users.data.remote.dto.request.UpdateStudentDetailsRequest
import com.presencify_admin.home.feature_users.data.remote.dto.request.UpdateStudentFcmTokenRequest
import com.presencify_admin.home.feature_users.data.remote.dto.request.UpdateStudentPasswordRequest
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
        dropoutAcademicStartYear: String? = null,
        dropoutAcademicEndYear: String? = null,
        semesterId: Int?,
        ): Flow<PagingData<StudentDto>>

    suspend fun getAllStudents(
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
        dropoutAcademicStartYear: String?,
        dropoutAcademicEndYear: String?,
        semesterId: Int?
    ): Response<PresencifyApiResponse<StudentListWithTotalDto>>


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
        parentEmail: String?,
    ): Response<PresencifyApiResponse<StudentDto>>

    // Update student details
    suspend fun updateStudentDetails(requestBody: UpdateStudentDetailsRequest): Response<PresencifyApiResponse<StudentDto>>

    // Update student password
    suspend fun updateStudentPassword(requestBody: UpdateStudentPasswordRequest): Response<PresencifyApiResponse<StudentDto>>

    // Update student image
    suspend fun updateStudentImage(
        studentId: Int,
        studentImageFile: File,
    ): Response<PresencifyApiResponse<StudentDto>>

    // Remove student image
    suspend fun removeStudentImage(studentId: Int): Response<PresencifyApiResponse<StudentDto>>

    // Remove a student
    suspend fun removeStudent(studentId: Int): Response<PresencifyApiResponse<Unit>>

    // Get student details by ID
    suspend fun getStudentDetailsById(studentId: Int): Response<PresencifyApiResponse<StudentDto>>

    // Add student to semester
    suspend fun addStudentToSemester(requestBody: AddStudentToSemesterRequest): Response<PresencifyApiResponse<StudentSemesterDto>>

    // Remove student from semester
    suspend fun removeStudentFromSemester(studentSemesterId: Int): Response<PresencifyApiResponse<Unit>>

    // Add student to division
    suspend fun addStudentToDivision(requestBody: AddStudentToDivisionRequest): Response<PresencifyApiResponse<StudentDivisionDto>>

    // Change student division
    suspend fun changeStudentDivision(requestBody: ChangeStudentDivisionRequest): Response<PresencifyApiResponse<StudentDivisionDto>>

    // Add student to batch
    suspend fun addStudentToBatch(requestBody: AddStudentToBatchRequest): Response<PresencifyApiResponse<StudentBatchDto>>

    // Change student batch
    suspend fun changeStudentBatch(requestBody: ChangeStudentBatchRequest): Response<PresencifyApiResponse<StudentBatchDto>>

    // Get student semesters by ID
    suspend fun getStudentSemestersById(studentId: Int): Response<PresencifyApiResponse<List<StudentSemesterDto>?>>

    // Get student divisions by ID
    suspend fun getStudentDivisionsById(
        studentId: Int,
        semesterNumber: Int?,
    ): Response<PresencifyApiResponse<List<StudentDivisionDto>?>>

    // Get student batches by ID
    suspend fun getStudentBatchesById(
        studentId: Int,
        semesterNumber: Int?,
    ): Response<PresencifyApiResponse<List<StudentBatchDto>?>>

    suspend fun addStudentToDropout(requestBody: AddDropoutRequest): Response<PresencifyApiResponse<DropoutDto?>>

    suspend fun removeStudentFromDropout(
        studentId: Int,
        academicStartYear: Int,
        academicEndYear: Int,
    ): Response<PresencifyApiResponse<Unit>>

    suspend fun getDropoutById(dropoutId: Int): Response<PresencifyApiResponse<DropoutDto?>>

    suspend fun getDropoutDetailsOfStudent(studentId: Int): Response<PresencifyApiResponse<List<DropoutDto>?>>

    suspend fun addStudentFcmToken(requestBody: AddStudentFcmTokenRequest): Response<PresencifyApiResponse<StudentFCMTokenDto?>>

    suspend fun updateStudentFcmToken(requestBody: UpdateStudentFcmTokenRequest): Response<PresencifyApiResponse<StudentFCMTokenDto?>>

    suspend fun removeStudentFcmToken(studentId: Int): Response<PresencifyApiResponse<Unit>>
}

