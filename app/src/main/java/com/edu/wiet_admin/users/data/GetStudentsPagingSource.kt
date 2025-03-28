package com.edu.wiet_admin.users.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.edu.wiet_admin.common.data.remote.response_dto.Student

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
    private val batchCode: String?
) : PagingSource<Int, Student>() {

    // used to find refresh key that is relevant to user's current scrolling position
    override fun getRefreshKey(state: PagingState<Int, Student>): Int? {
        // anchorPosition is index of a item in the paging data
        return state.anchorPosition?.let { anchorPosition ->

            // anchor page is a page that was holding the item with the index (stored in anchorPosition)
            val anchorPage = state.closestPageToPosition(anchorPosition)

            // as the pages needs to be order each page stores key of its next and prev page so by using them we
            // access current page
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private var totalStudentsCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Student> {
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
                page
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