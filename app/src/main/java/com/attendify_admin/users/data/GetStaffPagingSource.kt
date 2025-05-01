package com.attendify_admin.users.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.attendify_admin.common.data.remote.response_dto.Staff

class GetStaffPagingSource(
    private val staffApi: StaffApi,
    private val searchQuery: String?,
    private val courseId: Int?
) : PagingSource<Int, Staff>() {

    // used to find refresh key that is relevant to user's current scrolling position
    override fun getRefreshKey(state: PagingState<Int, Staff>): Int? {
        // anchorPosition is index of a item in the paging data
        return state.anchorPosition?.let { anchorPosition ->

            // anchor page is a page that was holding the item with the index (stored in anchorPosition)
            val anchorPage = state.closestPageToPosition(anchorPosition)

            // as the pages needs to be order each page stores key of its next and prev page so by using them we
            // access current page
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private var staffTotalCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Staff> {
        val page = params.key ?: 1
        return try {
            val newResponse = staffApi.getStaff(
                searchQuery,
                courseId,
                page
            )
            staffTotalCount += newResponse.body()?.data?.staff?.size ?: 0


            LoadResult.Page(
                data = newResponse.body()?.data?.staff ?: emptyList(),
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (staffTotalCount == (newResponse.body()?.data?.totalStaff
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