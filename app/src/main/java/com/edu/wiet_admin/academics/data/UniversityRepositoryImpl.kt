package com.edu.wiet_admin.academics.data

import com.edu.wiet_admin.academics.data.dto.request.AddUniversityRequest
import com.edu.wiet_admin.academics.data.dto.request.UpdateUniversityRequest
import com.edu.wiet_admin.academics.domain.repository.UniversityRepository
import com.edu.wiet_admin.common.data.remote.WietApiResponse
import com.edu.wiet_admin.common.data.remote.response_dto.University
import retrofit2.Response
import javax.inject.Inject

class UniversityRepositoryImpl @Inject constructor(
    private val universityApi: UniversityApi
) : UniversityRepository {

    override suspend fun getUniversities(): Response<WietApiResponse<List<University?>>> =
        universityApi.getUniversities()

    override suspend fun addUniversity(requestBody: AddUniversityRequest): Response<WietApiResponse<University?>> =
        universityApi.addUniversity(requestBody)

    override suspend fun updateUniversity(requestBody: UpdateUniversityRequest): Response<WietApiResponse<University?>> =
        universityApi.updateUniversity(requestBody)

    override suspend fun removeUniversity(universityId: String): Response<WietApiResponse<String?>> =
        universityApi.removeUniversity(universityId)

    override suspend fun getUniversityById(universityId: String): Response<WietApiResponse<University?>> =
        universityApi.getUniversityById(universityId)
}