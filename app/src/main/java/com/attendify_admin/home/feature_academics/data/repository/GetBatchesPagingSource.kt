package com.attendify_admin.home.feature_academics.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.attendify_admin.common.data.remote.dto.response.BatchDto
import com.attendify_admin.home.feature_academics.data.remote.BatchApi

class GetBatchesPagingSource(
    private val batchApi: BatchApi,
    private val semesterNumber: Int?,
    private val branchId: Int?,
    private val academicStartYear: Int?,
    private val academicEndYear: Int?,
    private val searchQuery: String?
) : PagingSource<Int, BatchDto>() {

    private var totalLoadedCount = 0

    override fun getRefreshKey(state: PagingState<Int, BatchDto>): Int? {
        return state.anchorPosition?.let { pos ->
            val page = state.closestPageToPosition(pos)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BatchDto> {
        val page = params.key ?: 1

        return try {
            val response = batchApi.getBatches(
                semesterNumber = semesterNumber,
                branchId = branchId,
                academicStartYear = academicStartYear,
                academicEndYear = academicEndYear,
                searchQuery = searchQuery,
                page = page,
                limit = params.loadSize,
                false
            )

            val batches = response.body()?.data?.batches ?: emptyList()
            val total = response.body()?.data?.totalCount ?: Int.MAX_VALUE
            totalLoadedCount += batches.size

            LoadResult.Page(
                data = batches,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (totalLoadedCount >= total) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
