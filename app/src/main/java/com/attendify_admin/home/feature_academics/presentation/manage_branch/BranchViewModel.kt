package com.attendify_admin.home.feature_academics.presentation.manage_branch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.home.feature_academics.domain.use_case.GetBranchesUseCase
import com.attendify_admin.home.feature_academics.domain.use_case.RemoveBranchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

// ManageBranchViewModel.kt
@HiltViewModel
class ManageBranchViewModel @Inject constructor(
    private val getBranchesUseCase: GetBranchesUseCase,
    private val removeBranchUseCase: RemoveBranchUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(ManageBranchState())
    val state = _state.asStateFlow()

    init {
        fetchBranches()
    }

    fun onAction(action: ManageBranchAction) {
        when (action) {
            is ManageBranchAction.SearchQueryChanged -> {
                _state.update { it.copy(searchQuery = action.newQuery) }
                fetchBranches()
            }

            ManageBranchAction.FetchBranches -> fetchBranches()
            is ManageBranchAction.DeleteBranch -> deleteBranch(action.branchId)
            is ManageBranchAction.BranchListItemClick -> {}
            ManageBranchAction.FABClick -> {}
        }
    }

    private fun fetchBranches() {
        getBranchesUseCase(state.value.searchQuery.takeIf { it.isNotBlank() }).onEach { result ->
            when (result) {
                is Resource.Loading -> _state.update { it.copy(isLoading = true, error = null) }
                is Resource.Success -> _state.update {
                    it.copy(
                        branches = result.data.orEmpty().toPersistentList(),
                        isLoading = false
                    )
                }

                is Resource.Error -> _state.update {
                    it.copy(
                        error = result.message,
                        isLoading = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun deleteBranch(branchId: Int) {
        removeBranchUseCase(branchId).onEach { result ->
            when (result) {
                is Resource.Loading -> _state.update { it.copy(isLoading = true) }
                is Resource.Success -> fetchBranches()
                is Resource.Error -> _state.update {
                    it.copy(
                        error = result.message,
                        isLoading = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}
