package com.attendify_admin.home.feature_users.presentation.search_staff

import androidx.paging.PagingData
import com.attendify_admin.common.domain.model.Staff
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class SearchStaffState (
    val isFetchingStaff: Boolean = true,
    val dialogText: String? = null,
    val staff: Flow<PagingData<Staff>> = emptyFlow(),
    val searchQuery: String = ""
)


