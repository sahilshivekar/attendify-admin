package com.presencify_admin.home.feature_academics.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.presencify_admin.common.data.remote.dto.response.CourseDto
import com.presencify_admin.home.feature_academics.data.remote.CourseApi

class GetCoursesPagingSource(
    private val courseApi: CourseApi,
    private val searchQuery: String?,
    private val branchId: Int?,
    private val semesterNumber: Int?,
    private val schemeId: Int?
) : PagingSource<Int, CourseDto>() {

    private var totalLoadedCount = 0

    override fun getRefreshKey(state: PagingState<Int, CourseDto>): Int? {
        return state.anchorPosition?.let { anchor ->
            val page = state.closestPageToPosition(anchor)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CourseDto> {
        val page = params.key ?: 1

        return try {
            val response = courseApi.getCourses(
                searchQuery = searchQuery,
                branchId = branchId,
                semesterNumber = semesterNumber,
                schemeId = schemeId,
                page = page,
                limit = params.loadSize,
                false
            )

            val responseBody = response.body()?.data
            val items = responseBody?.courses ?: emptyList()
            val total = responseBody?.totalCount ?: Int.MAX_VALUE
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
