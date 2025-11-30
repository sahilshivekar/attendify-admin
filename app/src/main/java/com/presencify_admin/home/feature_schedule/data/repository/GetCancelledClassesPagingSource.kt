package com.presencify_admin.home.feature_schedule.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.presencify_admin.common.data.remote.dto.response.CancelledClassDto
import com.presencify_admin.home.feature_schedule.data.remote.ClassApi

class GetCancelledClassesPagingSource(
    private val classApi: ClassApi,
    private val divisionId: Int,
    private val batchId: Int,
    private val date: String
) : PagingSource<Int, CancelledClassDto>() {

    private var totalLoadedCount = 0

    override fun getRefreshKey(state: PagingState<Int, CancelledClassDto>): Int? {
        return state.anchorPosition?.let { pos ->
            val anchorPage = state.closestPageToPosition(pos)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CancelledClassDto> {
        val page = params.key ?: 1
        return try {
            val response = classApi.getCancelledClasses(
                divisionId = divisionId,
                batchId = batchId,
                date = date,
                page = page,
                limit = params.loadSize,
                false
            )

            val cancelledClasses = response.body()?.data?.cancelledClasses ?: emptyList()
            val total = response.body()?.data?.totalCount ?: Int.MAX_VALUE

            totalLoadedCount += cancelledClasses.size

            LoadResult.Page(
                data = cancelledClasses,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (totalLoadedCount >= total) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
