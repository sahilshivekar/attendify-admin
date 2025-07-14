package com.attendify_admin.home.feature_academics.presentation.manage_university

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
import com.attendify_admin.home.feature_academics.domain.use_case.GetUniversitiesUseCase
import com.attendify_admin.home.feature_academics.domain.use_case.UpdateBranchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class ManageUniversityViewModel @Inject constructor(
    private val getUniversitiesUseCase: GetUniversitiesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ManageUniversityState())
    val state = _state.asStateFlow()

    init {
        fetchUniversities()
    }

    fun onAction(action: ManageUniversityAction) {
        when (action) {
            is ManageUniversityAction.FABClick -> Unit
            is ManageUniversityAction.UniversityListItemClick -> Unit
        }
    }

    private fun fetchUniversities() {
        getUniversitiesUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> _state.update { it.copy(isLoading = true, error = null) }

                is Resource.Success -> _state.update {
                    it.copy(
                        universities = result.data ?: emptyList(),
                        isLoading = false,
                        error = null
                    )
                }

                is Resource.Error -> _state.update {
                    it.copy(isLoading = false, error = result.message)
                }
            }
        }.launchIn(viewModelScope)
    }
}
