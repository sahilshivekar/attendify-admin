package com.attendify_admin.home.feature_schedule.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.attendify_admin.common.data.remote.dto.response.ClassDto
import com.attendify_admin.home.feature_schedule.data.remote.ClassApi

class GetClassesPagingSource(
    private val classApi: ClassApi,
    private val searchQuery: String?,
    private val timetableId: Int?,
    private val divisionId: Int?,
    private val startTime: String?,
    private val endTime: String?,
    private val activeFrom: String?,
    private val activeTill: String?,
    private val instructorId: Int?,
    private val dayOfWeek: String?,
    private val roomId: Int?,
    private val batchId: Int?,
    private val classType: String?,
    private val courseId: Int?,
    private val semesterId: Int?
) : PagingSource<Int, ClassDto>() {

    private var totalLoadedCount = 0

    override fun getRefreshKey(state: PagingState<Int, ClassDto>): Int? {
        return state.anchorPosition?.let { pos ->
            val anchorPage = state.closestPageToPosition(pos)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ClassDto> {
        val page = params.key ?: 1
        return try {
            val response = classApi.getClasses(
                searchQuery = searchQuery,
                timetableId = timetableId,
                divisionId = divisionId,
                startTime = startTime,
                endTime = endTime,
                activeFrom = activeFrom,
                activeTill = activeTill,
                instructorId = instructorId,
                dayOfWeek = dayOfWeek,
                roomId = roomId,
                batchId = batchId,
                classType = classType,
                courseId = courseId,
                semesterId = semesterId,
                page = page,
                limit = params.loadSize,
                false
            )

            val classes = response.body()?.data?.classes ?: emptyList()
            val total = response.body()?.data?.totalCount ?: Int.MAX_VALUE

            totalLoadedCount += classes.size

            LoadResult.Page(
                data = classes,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (totalLoadedCount >= total) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
