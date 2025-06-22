package com.attendify_admin.home.feature_academics.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.attendify_admin.common.data.remote.dto.response.DivisionDto
import com.attendify_admin.home.feature_academics.data.remote.DivisionApi

class GetDivisionsPagingSource(
    private val divisionApi: DivisionApi,
    private val semesterNumber: Int?,
    private val branchId: Int?,
    private val academicStartYear: Int?,
    private val academicEndYear: Int?,
    private val searchQuery: String?
) : PagingSource<Int, DivisionDto>() {

    private var totalLoadedCount = 0

    override fun getRefreshKey(state: PagingState<Int, DivisionDto>): Int? {
        return state.anchorPosition?.let { anchor ->
            val page = state.closestPageToPosition(anchor)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DivisionDto> {
        val page = params.key ?: 1

        return try {
            val response = divisionApi.getDivisions(
                semesterNumber = semesterNumber,
                branchId = branchId,
                academicStartYear = academicStartYear,
                academicEndYear = academicEndYear,
                searchQuery = searchQuery,
                page = page,
                limit = params.loadSize
            )

            val body = response.body()?.data
            val items = body?.divisions ?: emptyList()
            val total = body?.totalCount ?: Int.MAX_VALUE
            totalLoadedCount += items.size

            LoadResult.Page(
                data = items,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (totalLoadedCount >= total) null else page + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
