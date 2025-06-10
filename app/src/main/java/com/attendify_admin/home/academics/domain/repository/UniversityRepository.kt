package com.attendify_admin.home.academics.domain.repository

import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.common.data.dto.response.University
import com.attendify_admin.home.academics.data.dto.request.AddUniversityRequest
import com.attendify_admin.home.academics.data.dto.request.UpdateUniversityRequest
import retrofit2.Response

interface UniversityRepository {
    suspend fun getUniversities(): Response<AttendifyApiResponse<List<University?>>>

    suspend fun addUniversity(requestBody: AddUniversityRequest): Response<AttendifyApiResponse<University?>>

    suspend fun updateUniversity(requestBody: UpdateUniversityRequest): Response<AttendifyApiResponse<University?>>

    suspend fun removeUniversity(universityId: Int): Response<AttendifyApiResponse<String?>>

    suspend fun getUniversityById(universityId: Int): Response<AttendifyApiResponse<University?>>
}