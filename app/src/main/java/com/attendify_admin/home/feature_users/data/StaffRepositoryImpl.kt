package com.attendify_admin.home.feature_users.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.remote.dto.response.StaffDto
import com.attendify_admin.common.data.remote.dto.response.TeacherTeachesDto
import com.attendify_admin.home.feature_users.data.dto.request.AddTeachingSubjectRequest
import com.attendify_admin.home.feature_users.data.dto.request.UpdateStaffDetailsRequest
import com.attendify_admin.home.feature_users.data.dto.request.UpdateStaffPasswordRequest
import com.attendify_admin.home.feature_users.domain.repository.StaffRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import java.io.File

class StaffRepositoryImpl(
    private val staffApi: StaffApi
) : StaffRepository {

    override fun getStaff(
        searchQuery: String?,
        courseId: Int?
    ): Flow<PagingData<StaffDto>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { GetStaffPagingSource(staffApi, searchQuery, courseId) }
        ).flow
    }

    override suspend fun getStaffById(staffId: Int): Response<AttendifyApiResponse<StaffDto>> {
        return staffApi.getStaffById(staffId)
    }

    override suspend fun addStaff(
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
    ): Response<AttendifyApiResponse<StaffDto>> {
        val firstNameBody = firstName.toRequestBody("text/plain".toMediaTypeOrNull())
        val middleNameBody = middleName?.toRequestBody("text/plain".toMediaTypeOrNull())
        val lastNameBody = lastName.toRequestBody("text/plain".toMediaTypeOrNull())
        val emailBody = email.toRequestBody("text/plain".toMediaTypeOrNull())
        val phoneNumberBody = phoneNumber.toRequestBody("text/plain".toMediaTypeOrNull())
        val genderBody = gender.toRequestBody("text/plain".toMediaTypeOrNull())
        val highestQualificationBody =
            highestQualification?.toRequestBody("text/plain".toMediaTypeOrNull())
        val roleBody = role.toRequestBody("text/plain".toMediaTypeOrNull())
//        val passwordBody = password.toRequestBody("text/plain".toMediaTypeOrNull())
//        val confirmPasswordBody = confirmPassword.toRequestBody("text/plain".toMediaTypeOrNull())
        val isActiveBody = isActive.toString().toRequestBody("text/plain".toMediaTypeOrNull())

        val staffImagePart = if (staffImageFile != null) {
            val requestFile = staffImageFile.asRequestBody("image/*".toMediaTypeOrNull())
            MultipartBody.Part.createFormData("staffImageFile", staffImageFile.name, requestFile)
        } else {
            null
        }

        return staffApi.addStaff(
            firstNameBody,
            middleNameBody,
            lastNameBody,
            emailBody,
            phoneNumberBody,
            genderBody,
            highestQualificationBody,
            roleBody,
//            passwordBody,
//            confirmPasswordBody,
            isActiveBody,
            staffImagePart
        )
    }

    override suspend fun updateStaffDetails(requestBody: UpdateStaffDetailsRequest): Response<AttendifyApiResponse<StaffDto>> {
        return staffApi.updateStaffDetails(requestBody)
    }

    override suspend fun updateStaffPassword(requestBody: UpdateStaffPasswordRequest): Response<AttendifyApiResponse<StaffDto>> {
        return staffApi.updateStaffPassword(requestBody)
    }

    override suspend fun updateStaffImage(
        studentId: Int,
        staffImageFile: File
    ): Response<AttendifyApiResponse<StaffDto>> {
        val studentIdBody = studentId.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        val requestFile = staffImageFile.asRequestBody("image/*".toMediaTypeOrNull())
        val staffImagePart = MultipartBody.Part.createFormData("staffImageFile", staffImageFile.name, requestFile)
        return staffApi.updateStaffImage(
            studentIdBody,
            staffImagePart
        )
    }

    override suspend fun removeStaff(staffId: Int): Response<AttendifyApiResponse<Unit>> {
        return staffApi.removeStaff(staffId)
    }

    override suspend fun removeImage(staffId: Int): Response<AttendifyApiResponse<StaffDto>> {
        return staffApi.removeImage(staffId)
    }

    override suspend fun getTeachingSubjects(staffId: Int): Response<AttendifyApiResponse<List<TeacherTeachesDto>?>> {
        return staffApi.getTeachingSubjects(staffId)
    }

    override suspend fun addTeachingSubject(requestBody: AddTeachingSubjectRequest): Response<AttendifyApiResponse<TeacherTeachesDto>> {
        return staffApi.addTeachingSubject(requestBody)
    }

    override suspend fun removeTeachingSubject(teacherSubjectId: Int): Response<AttendifyApiResponse<Unit>> {
        return staffApi.removeTeachingSubject(teacherSubjectId)
    }
}