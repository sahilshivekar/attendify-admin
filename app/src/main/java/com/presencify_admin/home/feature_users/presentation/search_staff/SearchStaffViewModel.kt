package com.presencify_admin.home.feature_users.presentation.search_staff

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.presencify_admin.home.feature_users.domain.use_case.GetStaffUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SearchStaffViewModel @Inject constructor(
    private val getStaffUseCase: GetStaffUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = MutableStateFlow(SearchStaffState()) // Made private
    val state: StateFlow<SearchStaffState> = _state.asStateFlow() // Exposed as StateFlow

    init {
        val isSelectable = savedStateHandle.get<Boolean>("isSelectable") == true
        _state.update { it.copy(isSelectable = isSelectable) }
        getStaff()
    }

    private fun getStaff() {
        // Access value from _state
        val staff = getStaffUseCase(
            searchQuery = _state.value.searchQuery
        ).cachedIn(viewModelScope)
        _state.update { // Used .update
            it.copy(staff = staff)
        }
    }

    fun onEvent(event: SearchStaffEvent) {
        when (event) {
            SearchStaffEvent.FetchStaff -> {
                _state.update {
                    it.copy(isFetchingStaff = true)
                }
                getStaff()
            }

            is SearchStaffEvent.SearchQueryChanged -> {
                _state.update {
                    it.copy(searchQuery = event.searchQuery)
                }
            }

            is SearchStaffEvent.SelectionModeChanged -> {
                _state.update { it.copy(isSelectable = event.isSelectionEnabled) }
            }
        }
    }
}