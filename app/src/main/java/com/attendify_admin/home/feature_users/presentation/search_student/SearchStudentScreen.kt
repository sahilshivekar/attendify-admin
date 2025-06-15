package com.attendify_admin.home.feature_users.presentation.search_student

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.attendify_admin.common.presentation.PreviewWrapper
import com.attendify_admin.common.presentation.ScreenPreview
import com.attendify_admin.common.presentation.components.AttendifyAlertDialog
import com.attendify_admin.common.presentation.components.AttendifyNoResultsIndicator
import com.attendify_admin.common.presentation.components.AttendifySearchBar
import com.attendify_admin.home.feature_users.presentation.search_student.components.ModalBottomSheetForSearchStudentScreen
import com.attendify_admin.home.feature_users.presentation.search_student.components.StudentCard
import com.attendify_admin.home.feature_users.utils.StudentUtils


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchStudentScreen(
    modifier: Modifier = Modifier,
    state: SearchStudentState,
    onEvent: (SearchStudentEvent) -> Unit,
    onStudentCardClick: (Int) -> Unit
) {


    if (state.dialogText != null) {
        AttendifyAlertDialog(
            dialogText = state.dialogText,
            onDismiss = { onEvent(SearchStudentEvent.DismissAlertDialog) }
        )
    }

    val sheetState = rememberModalBottomSheetState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp),
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

        val students = state.students?.collectAsLazyPagingItems()

        students?.let {
            when {
                students.loadState.refresh is LoadState.NotLoading && students.itemCount == 0 -> {
                    AttendifyNoResultsIndicator()
                }

                students.loadState.hasError -> {
                    AttendifyNoResultsIndicator(text = "Some error occurred while fetching students")
                }

                students.loadState.refresh is LoadState.Loading -> {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator()
                    }
                }

                students.itemCount > 0 -> {
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(count = students.itemCount) { index ->
                            students[index]?.let { student ->
                                StudentCard(
                                    modifier = Modifier,
                                    onClick = { onStudentCardClick(student.id) },
                                    studentName = "${student.firstName} ${if (student.middleName != null) student.middleName + " " else ""}${student.lastName}",
                                    studentBranch = student.branch?.abbreviation ?: "",
                                    studentYear = if (
                                        (student.studentSemesters?.size ?: 0) > 0 &&
                                        student.studentSemesters?.first()?.semester?.semesterNumber != null
                                    )
                                        StudentUtils.getCurrentYearFromSem(
                                            student.studentSemesters.first().semester.semesterNumber
                                        ) else null,
                                    studentImageUrl = student.studentImgUrl
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                        }

                        if (students.loadState.append is LoadState.Loading) {
                            item {
                                CircularProgressIndicator(
                                    modifier = Modifier.padding(vertical = 32.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

    }
}


@ScreenPreview
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