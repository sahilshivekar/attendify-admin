package com.attendify_admin.home.feature_users.presentation.search_staff

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import com.attendify_admin.home.feature_users.presentation.search_staff.components.StaffCard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchStaffScreen(
    modifier: Modifier = Modifier,
    state: SearchStaffState,
    onEvent: (SearchStaffEvent) -> Unit,
    onStaffCardClick: (Int) -> Unit = {},
) {


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

            val staff = state.staff.collectAsLazyPagingItems()

            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.fillMaxSize()
            ) {
                items(
                    count = staff.itemCount,
                    key = { index -> index }

                ) { index ->
                    staff[index]?.let { staffMember ->
                        StaffCard(
                            name = "${staffMember.firstName} ${if (staffMember.middleName != null) staffMember.middleName + " " else ""}${staffMember.lastName}",
                            role = staffMember.role,
                            imageUrl = staffMember.staffImageUrl,
                            highestQualification = staffMember.highestQualification,
                            onClick = {
                                onStaffCardClick(staffMember.id)
                            }
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }

                item {
                    if (staff.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator(
                            modifier = Modifier.padding(vertical = 32.dp)
                        )
                    }
                }
                item {
                    if (staff.loadState.refresh is LoadState.NotLoading && staff.itemCount == 0) {
                        AttendifyNoResultsIndicator()
                    }
                }
                item {
                    if (staff.loadState.hasError) {
                        AttendifyNoResultsIndicator(text = "Some error occurred while fetching staff")
                    }
                }
                item {
                    if (staff.loadState.refresh is LoadState.Loading) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}


@PreviewScreenSizes
@Composable
fun SearchStaffScreenPreview() {
    PreviewWrapper {
        SearchStaffScreen(
            state = SearchStaffState(),
            onEvent = {}
        )
    }
}