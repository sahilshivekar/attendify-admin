package com.attendify_admin.home.feature_schedule.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.attendify_admin.common.data.remote.dto.response.TimetableDto
import com.attendify_admin.home.feature_schedule.data.remote.TimetableApi

class GetTimetablesPagingSource(
    private val timetableApi: TimetableApi,
    private val semesterNumber: Int?,
    private val academicStartYearOfSemester: Int?,
    private val academicEndYearOfSemester: Int?
) : PagingSource<Int, TimetableDto>() {

    private var totalLoadedCount = 0

    override fun getRefreshKey(state: PagingState<Int, TimetableDto>): Int? {
        return state.anchorPosition?.let { pos ->
            val anchorPage = state.closestPageToPosition(pos)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TimetableDto> {
        val page = params.key ?: 1

        return try {
            val response = timetableApi.getTimetables(
                semesterNumber = semesterNumber,
                academicStartYearOfSemester = academicStartYearOfSemester,
                academicEndYearOfSemester = academicEndYearOfSemester,
                page = page,
                limit = params.loadSize
            )

            val timetables = response.body()?.data?.timetables ?: emptyList()
            val total = response.body()?.data?.totalCount ?: Int.MAX_VALUE

            totalLoadedCount += timetables.size

            LoadResult.Page(
                data = timetables,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (totalLoadedCount >= total) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
