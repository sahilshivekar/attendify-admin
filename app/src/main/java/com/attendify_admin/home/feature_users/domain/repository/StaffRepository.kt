package com.attendify_admin.home.feature_users.domain.repository

import androidx.paging.PagingData
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.StaffDto
import com.attendify_admin.common.data.remote.dto.response.TeacherTeachesDto
import com.attendify_admin.home.feature_users.data.dto.request.AddTeachingSubjectRequest
import com.attendify_admin.home.feature_users.data.dto.request.RemoveImageRequest
import com.attendify_admin.home.feature_users.data.dto.request.RemoveStaffRequest
import com.attendify_admin.home.feature_users.data.dto.request.UpdateStaffDetailsRequest
import com.attendify_admin.home.feature_users.data.dto.request.UpdateStaffPasswordRequest
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import java.io.File

interface StaffRepository {

    // Get all staff
    fun getStaff(
        searchQuery: String?,
        courseId: Int?
    ): Flow<PagingData<StaffDto>>

    // Get staff by ID
    suspend fun getStaffById(staffId: Int): Response<AttendifyApiResponse<StaffDto>>

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
//        password: String,
//        confirmPassword: String,
        isActive: Boolean,
        staffImageFile: File?
    ): Response<AttendifyApiResponse<StaffDto>>

    // Update staff details
    suspend fun updateStaffDetails(requestBody: UpdateStaffDetailsRequest): Response<AttendifyApiResponse<StaffDto>>

    // Update staff password
    suspend fun updateStaffPassword(requestBody: UpdateStaffPasswordRequest): Response<AttendifyApiResponse<StaffDto>>

    // Update staff image
    suspend fun updateStaffImage(
        studentId: Int,
        staffImageFile: File
    ): Response<AttendifyApiResponse<StaffDto>>

    // Remove staff
    suspend fun removeStaff(requestBody: RemoveStaffRequest): Response<AttendifyApiResponse<Unit>>

    // Remove staff image
    suspend fun removeImage(requestBody: RemoveImageRequest): Response<AttendifyApiResponse<StaffDto>>

    // Get teaching subjects
    suspend fun getTeachingSubjects(staffId: Int): Response<AttendifyApiResponse<List<TeacherTeachesDto>?>>

    // Add teaching subject
    suspend fun addTeachingSubject(requestBody: AddTeachingSubjectRequest): Response<AttendifyApiResponse<TeacherTeachesDto>>

    // Remove teaching subject
    suspend fun removeTeachingSubject(teacherSubjectId: Int): Response<AttendifyApiResponse<Unit>>
}