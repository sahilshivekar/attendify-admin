package com.attendify_admin.users.domain.repository

import androidx.paging.PagingData
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.response_dto.Student
import com.attendify_admin.common.data.remote.response_dto.StudentBatch
import com.attendify_admin.common.data.remote.response_dto.StudentDivision
import com.attendify_admin.common.data.remote.response_dto.StudentSemester
import com.attendify_admin.users.data.dto.request.AddStudentToBatchRequest
import com.attendify_admin.users.data.dto.request.AddStudentToDivisionRequest
import com.attendify_admin.users.data.dto.request.AddStudentToSemesterRequest
import com.attendify_admin.users.data.dto.request.ChangeStudentBatchRequest
import com.attendify_admin.users.data.dto.request.ChangeStudentDivisionRequest
import com.attendify_admin.users.data.dto.request.RemoveStudentFromSemesterRequest
import com.attendify_admin.users.data.dto.request.RemoveStudentImageRequest
import com.attendify_admin.users.data.dto.request.RemoveStudentRequest
import com.attendify_admin.users.data.dto.request.UpdateStudentDetailsRequest
import com.attendify_admin.users.data.dto.request.UpdateStudentPasswordRequest
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
    ): Flow<PagingData<Student>>

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
        schemeId: String,
        academicStatus: String,
        admissionYear: String,
        admissionType: String,
        branchId: String,
        studentImageFile: File?
    ): Response<AttendifyApiResponse<Student>>

    // Update student details
    suspend fun updateStudentDetails(requestBody: UpdateStudentDetailsRequest): Response<AttendifyApiResponse<Student>>

    // Update student password
    suspend fun updateStudentPassword(requestBody: UpdateStudentPasswordRequest): Response<AttendifyApiResponse<Student>>

    // Update student image
    suspend fun updateStudentImage(
        studentId: String,
        studentImageFile: File
    ): Response<AttendifyApiResponse<Student>>

    // Remove student image
    suspend fun removeStudentImage(requestBody: RemoveStudentImageRequest): Response<AttendifyApiResponse<Student>>

    // Remove a student
    suspend fun removeStudent(requestBody: RemoveStudentRequest): Response<AttendifyApiResponse<Unit>>

    // Get student details by ID
    suspend fun getStudentDetailsById(studentId: Int): Response<AttendifyApiResponse<Student>>

    // Add student to semester
    suspend fun addStudentToSemester(requestBody: AddStudentToSemesterRequest): Response<AttendifyApiResponse<StudentSemester>>

    // Remove student from semester
    suspend fun removeStudentFromSemester(requestBody: RemoveStudentFromSemesterRequest): Response<AttendifyApiResponse<Unit>>

    // Add student to division
    suspend fun addStudentToDivision(requestBody: AddStudentToDivisionRequest): Response<AttendifyApiResponse<StudentDivision>>

    // Change student division
    suspend fun changeStudentDivision(requestBody: ChangeStudentDivisionRequest): Response<AttendifyApiResponse<StudentDivision>>

    // Add student to batch
    suspend fun addStudentToBatch(requestBody: AddStudentToBatchRequest): Response<AttendifyApiResponse<StudentBatch>>

    // Change student batch
    suspend fun changeStudentBatch(requestBody: ChangeStudentBatchRequest): Response<AttendifyApiResponse<StudentBatch>>

    // Get student semesters by ID
    suspend fun getStudentSemestersById(studentId: Int): Response<AttendifyApiResponse<List<StudentSemester>>>

    // Get student divisions by ID
    suspend fun getStudentDivisionsById(studentId: Int, semesterNumber: Int?): Response<AttendifyApiResponse<List<StudentDivision>>>

    // Get student batches by ID
    suspend fun getStudentBatchesById(studentId: Int, semesterNumber: Int?): Response<AttendifyApiResponse<List<StudentBatch>>>
}

