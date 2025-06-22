package com.attendify_admin.home.feature_users.presentation.search_student

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.attendify_admin.common.presentation.PreviewWrapper
import com.attendify_admin.common.presentation.UiConstants
import com.attendify_admin.common.presentation.components.AttendifyNoResultsIndicator
import com.attendify_admin.common.presentation.components.AttendifySearchBar
import com.attendify_admin.home.feature_users.presentation.search_student.components.ModalBottomSheetForSearchStudentScreen
import com.attendify_admin.home.feature_users.presentation.search_student.components.StudentCard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchStudentScreen(
    modifier: Modifier = Modifier,
    state: SearchStudentState,
    onEvent: (SearchStudentEvent) -> Unit,
    onStudentCardClick: (Int) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.widthIn(max = UiConstants.MAX_WIDTH),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            if (state.isBottomSheetVisible) {
                ModalBottomSheetForSearchStudentScreen(
                    sheetState = sheetState,
                    onEvent = onEvent,
                    state = state
                )
            }

            AttendifySearchBar(
                modifier = Modifier
                    .padding(vertical = 16.dp),
                searchQuery = state.searchQuery,
                onSearchQueryValueChange = {
                    onEvent(SearchStudentEvent.SearchQueryChanged(it))
                    onEvent(SearchStudentEvent.FetchStudents)
                },
                onSearchIconClick = { onEvent(SearchStudentEvent.FetchStudents) },
                onFilterIconClick = {
                    onEvent(SearchStudentEvent.BottomSheetVisibilityChanged(true))
                },
                searchBarPlaceholder = "Search Student",
            )

            val students = state.students.collectAsLazyPagingItems()

            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.fillMaxSize()
            ) {

                items(
                    count = students.itemCount,
                    key = { it }
                ) { index ->
                    students[index]?.let { student ->
                        StudentCard(
                            modifier = Modifier,
                            onClick = { onStudentCardClick(student.id) },
                            studentName = student.studentName,
                            studentBranch = student.studentBranch,
                            studentYear = student.studentYear,
                            studentImageUrl = student.studentImageUrl
                        )
                    }
                    if (index < students.itemCount - 1)
                        HorizontalDivider()
                }

                item {
                    if (students.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator(
                            modifier = Modifier.padding(vertical = 32.dp)
                        )
                    }
                }

                item {
                    if (students.loadState.refresh is LoadState.Loading) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }

                item {
                    if (students.loadState.refresh is LoadState.NotLoading && students.itemCount == 0) {
                        AttendifyNoResultsIndicator()
                    }
                }

                item {
                    if (students.loadState.hasError) {
                        AttendifyNoResultsIndicator(text = "Some error occurred while fetching students")
                    }
                }
            }
        }
    }
}


@PreviewScreenSizes
@Composable
fun SearchStudentScreenPreview() {
    PreviewWrapper {
        SearchStudentScreen(
            state = SearchStudentState(),
            onEvent = {},
            modifier = Modifier,
            onStudentCardClick = {}
        )
    }
}