package com.presencify_admin.home.feature_academics.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.presencify_admin.common.data.remote.dto.response.SemesterDto
import com.presencify_admin.home.feature_academics.data.remote.SemesterApi

class GetSemestersPagingSource(
    private val semesterApi: SemesterApi,
    private val semesterNumber: Int?,
    private val academicStartYear: Int?,
    private val academicEndYear: Int?,
    private val branchId: Int?,
    private val schemeId: Int?
) : PagingSource<Int, SemesterDto>() {

    private var totalLoaded = 0

    override fun getRefreshKey(state: PagingState<Int, SemesterDto>): Int? {
        return state.anchorPosition?.let { anchor ->
            val page = state.closestPageToPosition(anchor)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SemesterDto> {
        val page = params.key ?: 1

        return try {
            val response = semesterApi.getSemesters(
                semesterNumber = semesterNumber,
                academicStartYear = academicStartYear,
                academicEndYear = academicEndYear,
                branchId = branchId,
                schemeId = schemeId,
                page = page,
                limit = params.loadSize,
                false
            )

            val data = response.body()?.data
            val semesters = data?.semesters ?: emptyList()
            val total = data?.totalCount ?: Int.MAX_VALUE
            totalLoaded += semesters.size

            LoadResult.Page(
                data = semesters,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (totalLoaded >= total) null else page + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
