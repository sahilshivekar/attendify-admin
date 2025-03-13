package com.edu.wiet_admin.users.domain.repository

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
import retrofit2.Response
import java.io.File
import java.util.Date

interface StudentRepository {

    // Get all students
    suspend fun getStudents(
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
        page: Int = 1,
        limit: Int = 10
    ): Response<WietApiResponse<List<Student>>>

    // Add a student
    suspend fun addStudent(
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
    ): Response<WietApiResponse<Student>>

    // Update student details
    suspend fun updateStudentDetails(requestBody: UpdateStudentDetailsRequest): Response<WietApiResponse<Student>>

    // Update student password
    suspend fun updateStudentPassword(requestBody: UpdateStudentPasswordRequest): Response<WietApiResponse<Student>>

    // Update student image
    suspend fun updateStudentImage(
        studentId: String,
        studentImageFile: File
    ): Response<WietApiResponse<Student>>

    // Remove student image
    suspend fun removeStudentImage(requestBody: RemoveStudentImageRequest): Response<WietApiResponse<Student>>

    // Remove a student
    suspend fun removeStudent(requestBody: RemoveStudentRequest): Response<WietApiResponse<Unit>>

    // Get student details by ID
    suspend fun getStudentDetailsById(studentId: Int): Response<WietApiResponse<Student>>

    // Add student to semester
    suspend fun addStudentToSemester(requestBody: AddStudentToSemesterRequest): Response<WietApiResponse<StudentSemester>>

    // Remove student from semester
    suspend fun removeStudentFromSemester(requestBody: RemoveStudentFromSemesterRequest): Response<WietApiResponse<Unit>>

    // Add student to division
    suspend fun addStudentToDivision(requestBody: AddStudentToDivisionRequest): Response<WietApiResponse<StudentDivision>>

    // Change student division
    suspend fun changeStudentDivision(requestBody: ChangeStudentDivisionRequest): Response<WietApiResponse<StudentDivision>>

    // Add student to batch
    suspend fun addStudentToBatch(requestBody: AddStudentToBatchRequest): Response<WietApiResponse<StudentBatch>>

    // Change student batch
    suspend fun changeStudentBatch(requestBody: ChangeStudentBatchRequest): Response<WietApiResponse<StudentBatch>>

    // Get student semesters by ID
    suspend fun getStudentSemestersById(studentId: Int): Response<WietApiResponse<List<StudentSemester>>>

    // Get student divisions by ID
    suspend fun getStudentDivisionsById(studentId: Int, semesterNumber: Int?): Response<WietApiResponse<List<StudentDivision>>>

    // Get student batches by ID
    suspend fun getStudentBatchesById(studentId: Int, semesterNumber: Int?): Response<WietApiResponse<List<StudentBatch>>>
}

