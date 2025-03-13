package com.edu.wiet_admin.users.domain.repository

import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.Staff
import com.edu.wiet_admin.common.data.remote.response_dto.TeacherTeaches
import com.edu.wiet_admin.users.data.dto.request.AddTeachingSubjectRequest
import com.edu.wiet_admin.users.data.dto.request.RemoveImageRequest
import com.edu.wiet_admin.users.data.dto.request.RemoveStaffRequest
import com.edu.wiet_admin.users.data.dto.request.UpdateStaffDetailsRequest
import com.edu.wiet_admin.users.data.dto.request.UpdateStaffPasswordRequest
import retrofit2.Response
import java.io.File

interface StaffRepository {

    // Get all staff
    suspend fun getStaff(
        searchQuery: String?,
        courseId: Int?,
        page: Int = 1,
        limit: Int = 10
    ): Response<WietApiResponse<List<Staff>>>

    // Get staff by ID
    suspend fun getStaffById(staffId: Int): Response<WietApiResponse<Staff>>

    // Add a staff member
    suspend fun addStaff(
        firstName: String,
        middleName: String?,
        lastName: String,
        email: String,
        phoneNumber: String,
        gender: String,
        highestQualification: String?,
        role: String,
        password: String,
        confirmPassword: String,
        isActive: Boolean,
        staffImageFile: File?
    ): Response<WietApiResponse<Staff>>

    // Update staff details
    suspend fun updateStaffDetails(requestBody: UpdateStaffDetailsRequest): Response<WietApiResponse<Staff>>

    // Update staff password
    suspend fun updateStaffPassword(requestBody: UpdateStaffPasswordRequest): Response<WietApiResponse<Staff>>

    // Update staff image
    suspend fun updateStaffImage(
        studentId: String,
        staffImageFile: File
    ): Response<WietApiResponse<Staff>>

    // Remove staff
    suspend fun removeStaff(requestBody: RemoveStaffRequest): Response<WietApiResponse<Unit>>

    // Remove staff image
    suspend fun removeImage(requestBody: RemoveImageRequest): Response<WietApiResponse<Staff>>

    // Get teaching subjects
    suspend fun getTeachingSubjects(staffId: Int): Response<WietApiResponse<List<TeacherTeaches>>>

    // Add teaching subject
    suspend fun addTeachingSubject(requestBody: AddTeachingSubjectRequest): Response<WietApiResponse<TeacherTeaches>>

    // Remove teaching subject
    suspend fun removeTeachingSubject(teacherSubjectId: Int): Response<WietApiResponse<Unit>>
}