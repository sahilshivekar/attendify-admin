package com.presencify_admin.home.feature_academics.presentation.manage_scheme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.presencify_admin.common.data.remote.Resource
import com.presencify_admin.home.feature_academics.domain.use_case.GetSchemesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManageSchemeViewModel @Inject constructor(
    private val getSchemesUseCase: GetSchemesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(ManageSchemeState())
    val state = _state.asStateFlow()

    init {
        fetchSchemes()
    }

    fun onAction(action: ManageSchemeAction) {
        when (action) {
            is ManageSchemeAction.SearchQueryChanged -> {
                _state.update { it.copy(searchQuery = action.query) }
            }

            is ManageSchemeAction.FetchSchemes -> {
                fetchSchemes()
            }

            is ManageSchemeAction.FABClick -> Unit // handled in Root
            is ManageSchemeAction.SchemeListItemClick -> Unit // handled in Root
        }
    }

    private fun fetchSchemes() {
        viewModelScope.launch {
            getSchemesUseCase(_state.value.searchQuery).collect { result ->
                when (result) {
                    is Resource.Loading -> _state.update { it.copy(isLoading = true, error = null) }
                    is Resource.Success -> _state.update {
                        it.copy(schemes = result.data ?: emptyList(), isLoading = false)
                    }

                    is Resource.Error -> _state.update {
                        it.copy(error = result.message, isLoading = false)
                    }
                }
            }
        }
    }
}
