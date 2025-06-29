package com.attendify_admin.home.feature_users.presentation.search_student

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.attendify_admin.common.presentation.PreviewWrapper
import com.attendify_admin.common.presentation.UiConstants
import com.attendify_admin.common.presentation.components.AttendifyButton
import com.attendify_admin.common.presentation.components.AttendifyNoResultsIndicator
import com.attendify_admin.common.presentation.components.AttendifySearchBar
import com.attendify_admin.home.feature_users.presentation.search_student.components.ModalBottomSheetForSearchStudentScreen
import com.attendify_admin.home.feature_users.presentation.search_student.components.StudentCard
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchStudentScreen(
    modifier: Modifier = Modifier,
    state: SearchStudentState,
    onEvent: (SearchStudentEvent) -> Unit,
    onStudentCardClick: (Int) -> Unit,
    onDoneClick: () -> Unit = {},
) {

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false  // creating it without false while initial value hidden causes crashes and creating the sheet state separately and modifying it directly causes crash
        )
    )
    LaunchedEffect(Unit) {
        bottomSheetScaffoldState.bottomSheetState.hide()
    }

    val scope = rememberCoroutineScope()

//
//    ObserveAsEvents(
//        SnackbarController.events,
//    ) { event ->
//        scope.launch {
//            bottomSheetScaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
//
//            val result = bottomSheetScaffoldState.snackbarHostState.showSnackbar(
//                message = event.message,
//                actionLabel = event.action?.name,
//                duration = if (event.action == null) SnackbarDuration.Short else SnackbarDuration.Long
//            )
//
//            if (result == SnackbarResult.ActionPerformed) {
//                event.action?.action()
//            }
//        }
//
//    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        BottomSheetScaffold(
            sheetContent = {
                ModalBottomSheetForSearchStudentScreen(
                    sheetState = bottomSheetScaffoldState.bottomSheetState,
                    onEvent = onEvent,
                    state = state
                )
            },
            containerColor = MaterialTheme.colorScheme.background,
            sheetPeekHeight = 0.dp,
            sheetContainerColor = MaterialTheme.colorScheme.surface,
            sheetMaxWidth = UiConstants.MAX_WIDTH,
            scaffoldState = bottomSheetScaffoldState,
        ) {
            Column(
                modifier = Modifier
                    .widthIn(max = UiConstants.MAX_WIDTH)
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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
                        scope.launch {
                            bottomSheetScaffoldState.bottomSheetState.expand()
                        }
                        onEvent(SearchStudentEvent.BottomSheetVisibilityChanged(true))
                    },
                    searchBarPlaceholder = "Search Student",
                )


                val students = state.students.collectAsLazyPagingItems()
                val pullRefreshState = rememberPullToRefreshState()

                if (students.loadState.refresh is LoadState.Loading) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator()
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .widthIn(max = UiConstants.MAX_WIDTH),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    AnimatedVisibility(
                        visible = students.itemCount > 0 || (students.loadState.refresh is LoadState.NotLoading && students.itemCount == 0) || students.loadState.hasError,
                        enter = fadeIn() + slideInVertically {
                            it / 5
                        },
                        exit = fadeOut(),
                        modifier = Modifier.weight(1f)
                    ) {
                        PullToRefreshBox(
                            isRefreshing = false,
                            onRefresh = {
                                onEvent(SearchStudentEvent.FetchStudents)
                            },
                            state = pullRefreshState,
                            indicator = {
                                Indicator(
                                    state = pullRefreshState,
                                    isRefreshing = students.loadState.refresh is LoadState.Loading,
                                    Modifier.align(Alignment.TopCenter),
                                    containerColor = MaterialTheme.colorScheme.surface,
                                    color = MaterialTheme.colorScheme.primary,
                                )
                            }
                        ) {
                            if (students.loadState.refresh is LoadState.NotLoading && students.itemCount == 0) {
                                AttendifyNoResultsIndicator()
                            }

                            if (students.loadState.hasError) {
                                AttendifyNoResultsIndicator(text = "Some error occurred while fetching students")
                            }


                            LazyColumn(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Top,
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
                                            studentImageUrl = student.studentImageUrl,
                                            isSelected = state.selectedStudentIds.contains(student.id),
                                            showCheckmark = state.isSelectable
                                        )
                                    }
                                    if (index < students.itemCount - 1)
                                        HorizontalDivider()
                                }

                                if (students.loadState.append is LoadState.Loading) {
                                    item {
                                        CircularProgressIndicator(
                                            modifier = Modifier.padding(
                                                vertical = 32.dp
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                    if (state.isSelectable) {
                        Log.d("search student", "selection enabled")
                        AttendifyButton(
                            onClick = onDoneClick,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp)
                        ) {
                            Text("Done")
                        }
                    }
                }
            }
        }
    }
}


@Preview
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