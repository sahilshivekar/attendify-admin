package com.attendify_admin.home.users.presentation.search_staff

import androidx.paging.PagingData
import com.attendify_admin.common.data.remote.response_dto.Staff
import kotlinx.coroutines.flow.Flow

data class SearchStaffState (
    val isFetchingStaff: Boolean = true,
    val dialogText: String? = null,
    val staff: Flow<PagingData<Staff>>? = null,
    val searchQuery: String = ""
)


