package com.edu.wiet_admin.users.presentation.search_staff

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.edu.wiet_admin.users.domain.use_case.GetStaffUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SearchStaffViewModel @Inject constructor(
    private val getStaffUseCase: GetStaffUseCase
) : ViewModel() {

    var state = MutableStateFlow(SearchStaffState())

    init {
        getStaff()
    }
    private fun getStaff() {
        val staff = getStaffUseCase(
            searchQuery = state.value.searchQuery
        ).cachedIn(viewModelScope)
        state.value = state.value.copy(staff = staff)
    }

    fun onEvent(event: SearchStaffEvent) {
        when (event) {
            SearchStaffEvent.DismissAlertDialog -> {
                state.value = state.value.copy(dialogText = null)
            }
            SearchStaffEvent.FetchStaff -> {
                state.value = state.value.copy(isFetchingStaff = true)
                getStaff()
            }
            is SearchStaffEvent.SearchQueryChanged -> {
                state.value = state.value.copy(searchQuery = event.searchQuery)
            }
            is SearchStaffEvent.ShowAlertDialog -> {
                state.value = state.value.copy(dialogText = event.message)
            }
        }
    }
}