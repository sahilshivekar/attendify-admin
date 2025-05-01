package com.attendify_admin.academics.domain.repository

import com.attendify_admin.common.data.remote.response_dto.University
import com.attendify_admin.common.data.remote.AttendifyApiResponse
import com.attendify_admin.academics.data.dto.request.AddUniversityRequest
import com.attendify_admin.academics.data.dto.request.UpdateUniversityRequest
import retrofit2.Response

interface UniversityRepository {
    suspend fun getUniversities(): Response<AttendifyApiResponse<List<University?>>>

    suspend fun addUniversity(requestBody: AddUniversityRequest): Response<AttendifyApiResponse<University?>>

    suspend fun updateUniversity(requestBody: UpdateUniversityRequest): Response<AttendifyApiResponse<University?>>

    suspend fun removeUniversity(universityId: String): Response<AttendifyApiResponse<String?>>

    suspend fun getUniversityById(universityId: String): Response<AttendifyApiResponse<University?>>
}