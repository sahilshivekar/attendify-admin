package com.attendify_admin.home.users.presentation.search_staff

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
import com.attendify_admin.home.users.presentation.search_staff.components.StaffCard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchStaffScreen(
    modifier: Modifier = Modifier,
    state: SearchStaffState,
    onEvent: (SearchStaffEvent) -> Unit
) {


    if (state.dialogText != null) {
        AttendifyAlertDialog(
            dialogText = state.dialogText,
            onDismiss = { onEvent(SearchStaffEvent.DismissAlertDialog) }
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AttendifySearchBar(
            modifier = Modifier
                .padding(vertical = 16.dp),
            searchQuery = state.searchQuery,
            onSearchQueryValueChange = {
                onEvent(SearchStaffEvent.SearchQueryChanged(it))
                onEvent(SearchStaffEvent.FetchStaff)
            },
            onSearchIconClick = { onEvent(SearchStaffEvent.FetchStaff) },
            showFilterIcon = false,
            searchBarPlaceholder = "Search Staff",
        )

        val staff = state.staff?.collectAsLazyPagingItems()

        staff?.let {
            when {
                staff.loadState.refresh is LoadState.NotLoading && staff.itemCount == 0 -> {
                    AttendifyNoResultsIndicator()
                }

                staff.loadState.hasError -> {
                    AttendifyNoResultsIndicator(text = "Some error occurred while fetching staff")
                }

                staff.loadState.refresh is LoadState.Loading -> {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator()
                    }
                }

                staff.itemCount > 0 -> {
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(count = staff.itemCount) { index ->
                            staff[index]?.let { staffMember ->
                                StaffCard(
                                    name = "${staffMember.firstName} ${if (staffMember.middleName != null) staffMember.middleName + " " else ""}${staffMember.lastName}",
                                    role = staffMember.role,
                                    imageUrl = staffMember.staffImageUrl,
                                    highestQualification = staffMember.highestQualification,
                                    onClick = {

                                    }
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                        }

                        if (staff.loadState.append is LoadState.Loading) {
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
fun SearchStaffScreenPreview() {
    PreviewWrapper {
        SearchStaffScreen(
            state = SearchStaffState(),
            onEvent = {}
        )
    }
}