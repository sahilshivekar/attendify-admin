package com.attendify_admin.home.feature_academics.presentation.add_branch

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.presentation.components.global_snackbar.SnackbarController
import com.attendify_admin.common.presentation.components.global_snackbar.SnackbarEvent
import com.attendify_admin.home.feature_academics.data.remote.dto.request.AddBranchRequest
import com.attendify_admin.home.feature_academics.data.remote.dto.request.UpdateBranchRequest
import com.attendify_admin.home.feature_academics.domain.use_case.AddBranchUseCase
import com.attendify_admin.home.feature_academics.domain.use_case.GetBranchByIdUseCase
import com.attendify_admin.home.feature_academics.domain.use_case.UpdateBranchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AddBranchViewModel @Inject constructor(
    private val addBranchUseCase: AddBranchUseCase,
    private val getBranchByIdUseCase: GetBranchByIdUseCase,
    private val updateBranchUseCase: UpdateBranchUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(AddBranchState())
    val state = _state.asStateFlow()

    init {
        savedStateHandle.get<Int>("branchId")?.let { branchId ->
            _state.update { it.copy(branchId = branchId) }
            getBranch(branchId)
        }
    }

    private fun getBranch(branchId: Int) {
        getBranchByIdUseCase(branchId).onEach { result ->
            when (result) {
                is Resource.Loading -> _state.update { it.copy(isLoading = true) }
                is Resource.Success -> {
                    result.data?.let { branch ->
                        _state.update {
                            it.copy(
                                isLoading = false,
                                name = branch.name,
                                abbreviation = branch.abbreviation
                            )
                        }
                    }
                }
                is Resource.Error -> {
                    _state.update { it.copy(isLoading = false) }
                    SnackbarController.sendEvent(
                        SnackbarEvent(result.message ?: "Failed to load branch")
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onAction(action: AddBranchAction) {
        when (action) {
            is AddBranchAction.NameChanged -> {
                _state.update { it.copy(name = action.newName, nameError = null) }
            }

            is AddBranchAction.AbbreviationChanged -> {
                _state.update { it.copy(abbreviation = action.newAbbreviation, abbreviationError = null) }
            }

            AddBranchAction.SubmitClicked -> {
                if (validate()) {
                    if (state.value.branchId == null) addBranch()
                    else updateBranch()
                }
            }

            AddBranchAction.OnAddUpdateSuccessNavigation -> {}
        }
    }

    private fun validate(): Boolean {
        var isValid = true

        if (state.value.name.isBlank()) {
            _state.update { it.copy(nameError = "Name is required") }
            isValid = false
        }

        if (state.value.abbreviation.isBlank()) {
            _state.update { it.copy(abbreviationError = "Abbreviation is required") }
            isValid = false
        }

        return isValid
    }

    private fun addBranch() {
        addBranchUseCase(
            AddBranchRequest(
                name = state.value.name,
                abbreviation = state.value.abbreviation
            )
        ).onEach { result ->
            when (result) {
                is Resource.Loading -> _state.update { it.copy(isLoading = true) }
                is Resource.Success -> {
                    _state.update { it.copy(isLoading = false, isSubmitted = true) }
                    SnackbarController.sendEvent(SnackbarEvent("Branch added successfully"))
                }
                is Resource.Error -> {
                    _state.update { it.copy(isLoading = false) }
                    SnackbarController.sendEvent(SnackbarEvent(result.message ?: "Failed to add branch"))
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun updateBranch() {
        updateBranchUseCase(
            UpdateBranchRequest(
                id = state.value.branchId!!.toString(),
                name = state.value.name,
                abbreviation = state.value.abbreviation
            )
        ).onEach { result ->
            when (result) {
                is Resource.Loading -> _state.update { it.copy(isLoading = true) }
                is Resource.Success -> {
                    _state.update { it.copy(isLoading = false, isSubmitted = true) }
                    SnackbarController.sendEvent(SnackbarEvent("Branch updated successfully"))
                }
                is Resource.Error -> {
                    _state.update { it.copy(isLoading = false) }
                    SnackbarController.sendEvent(SnackbarEvent(result.message ?: "Failed to update branch"))
                }
            }
        }.launchIn(viewModelScope)
    }
}