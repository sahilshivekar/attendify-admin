package com.presencify_admin.home.feature_users.domain.repository

import androidx.paging.PagingData
import com.presencify_admin.common.data.remote.dto.response.PresencifyApiResponse
import com.presencify_admin.common.data.remote.dto.response.StaffDto
import com.presencify_admin.common.data.remote.dto.response.StaffListWithTotalDto
import com.presencify_admin.common.data.remote.dto.response.TeacherTeachesDto
import com.presencify_admin.home.feature_users.data.remote.dto.request.AddTeachingSubjectRequest
import com.presencify_admin.home.feature_users.data.remote.dto.request.UpdateStaffDetailsRequest
import com.presencify_admin.home.feature_users.data.remote.dto.request.UpdateStaffPasswordRequest
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import java.io.File

interface StaffRepository {

    // Get all staff
    fun getStaff(
        searchQuery: String?,
        courseId: Int?
    ): Flow<PagingData<StaffDto>>

    suspend fun getAllStaff(
        searchQuery: String?,
        courseId: Int?
    ): Response<PresencifyApiResponse<StaffListWithTotalDto>>


    // Get staff by ID
    suspend fun getStaffById(staffId: Int): Response<PresencifyApiResponse<StaffDto>>

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
    ): Response<PresencifyApiResponse<StaffDto>>

    // Update staff details
    suspend fun updateStaffDetails(requestBody: UpdateStaffDetailsRequest): Response<PresencifyApiResponse<StaffDto>>

    // Update staff password
    suspend fun updateStaffPassword(requestBody: UpdateStaffPasswordRequest): Response<PresencifyApiResponse<StaffDto>>

    // Update staff image
    suspend fun updateStaffImage(
        studentId: Int,
        staffImageFile: File
    ): Response<PresencifyApiResponse<StaffDto>>

    // Remove staff
    suspend fun removeStaff(staffId: Int): Response<PresencifyApiResponse<Unit>>

    // Remove staff image
    suspend fun removeImage(staffId: Int): Response<PresencifyApiResponse<StaffDto>>

    // Get teaching subjects
    suspend fun getTeachingSubjects(staffId: Int): Response<PresencifyApiResponse<List<TeacherTeachesDto>?>>

    // Add teaching subject
    suspend fun addTeachingSubject(requestBody: AddTeachingSubjectRequest): Response<PresencifyApiResponse<TeacherTeachesDto>>

    // Remove teaching subject
    suspend fun removeTeachingSubject(teacherSubjectId: Int): Response<PresencifyApiResponse<Unit>>
}