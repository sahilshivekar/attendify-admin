package com.attendify_admin.home.feature_users.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.attendify_admin.common.data.remote.dto.response.StudentDto
import com.attendify_admin.home.feature_users.data.remote.StudentApi

class GetStudentsPagingSource(
    private val studentApi: StudentApi,
    private val searchQuery: String?,
    private val branchIds: List<Int>?,
    private val semesterNumbers: List<Int>?,
    private val academicStartYearOfSemester: Int?,
    private val academicEndYearOfSemester: Int?,
    private val batchId: Int?,
    private val schemeId: Int?,
    private val divisionId: Int?,
    private val academicStatuses: List<String>?,
    private val admissionTypes: List<String>?,
    private val admissionYear: Int?,
    private val currentBatch: Boolean?,
    private val currentDivision: Boolean?,
    private val currentSemester: Boolean?,
    private val divisionCode: String?,
    private val batchCode: String?,
    private val dropoutAcademicStartYear: String?,
    private val dropoutAcademicEndYear: String?,
    private val semesterId: Int?
) : PagingSource<Int, StudentDto>() {

    override fun getRefreshKey(state: PagingState<Int, StudentDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private var totalStudentsCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, StudentDto> {
        val page = params.key ?: 1
        return try {
            val newResponse = studentApi.getStudents(
                searchQuery,
                branchIds,
                semesterNumbers,
                academicStartYearOfSemester,
                academicEndYearOfSemester,
                batchId,
                schemeId,
                divisionId,
                academicStatuses,
                admissionTypes,
                admissionYear,
                currentBatch,
                currentDivision,
                currentSemester,
                divisionCode,
                batchCode,
                page,
                params.loadSize,
                dropoutAcademicStartYear,
                dropoutAcademicEndYear,
                false,
                semesterId = semesterId
            )
            totalStudentsCount += newResponse.body()?.data?.students?.size ?: 0

            LoadResult.Page(
                data = newResponse.body()?.data?.students ?: emptyList(),
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (totalStudentsCount == (newResponse.body()?.data?.totalStudents
                        ?: Int.MAX_VALUE)
                ) null else page + 1
            )

        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }

}