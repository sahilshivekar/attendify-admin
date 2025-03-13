package com.edu.wiet_admin.academics.domain.repository

import com.edu.wiet_admin.common.data.remote.response_dto.University
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.academics.data.dto.request.AddUniversityRequest
import com.edu.wiet_admin.academics.data.dto.request.UpdateUniversityRequest
import retrofit2.Response

interface UniversityRepository {
    suspend fun getUniversities(): Response<WietApiResponse<List<University?>>>

    suspend fun addUniversity(requestBody: AddUniversityRequest): Response<WietApiResponse<University?>>

    suspend fun updateUniversity(requestBody: UpdateUniversityRequest): Response<WietApiResponse<University?>>

    suspend fun removeUniversity(universityId: String): Response<WietApiResponse<String?>>

    suspend fun getUniversityById(universityId: String): Response<WietApiResponse<University?>>
}