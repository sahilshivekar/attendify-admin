package com.attendify_admin.home.feature_schedule.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.attendify_admin.common.data.remote.dto.response.RoomDto
import com.attendify_admin.home.feature_schedule.data.remote.RoomApi

class GetRoomsPagingSource(
    private val roomApi: RoomApi,
    private val searchQuery: String?,
    private val sortBy: String,
    private val sortOrder: String
) : PagingSource<Int, RoomDto>() {

    private var totalLoadedCount = 0

    override fun getRefreshKey(state: PagingState<Int, RoomDto>): Int? {
        return state.anchorPosition?.let { pos ->
            val anchorPage = state.closestPageToPosition(pos)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RoomDto> {
        val page = params.key ?: 1
        return try {
            val response = roomApi.getRooms(
                searchQuery = searchQuery,
                sortBy = sortBy,
                sortOrder = sortOrder,
                page = page,
                limit = params.loadSize,
                false
            )

            val rooms = response.body()?.data?.rooms ?: emptyList()
            val total = response.body()?.data?.totalCount ?: Int.MAX_VALUE

            totalLoadedCount += rooms.size

            LoadResult.Page(
                data = rooms,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (totalLoadedCount >= total) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
