package com.presencify_admin.home.feature_academics.presentation.add_scheme

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.presencify_admin.common.data.remote.Resource
import com.presencify_admin.common.presentation.components.global_snackbar.SnackbarController
import com.presencify_admin.common.presentation.components.global_snackbar.SnackbarEvent
import com.presencify_admin.home.feature_academics.data.remote.dto.request.AddSchemeRequest
import com.presencify_admin.home.feature_academics.data.remote.dto.request.UpdateSchemeRequest
import com.presencify_admin.home.feature_academics.domain.use_case.AddSchemeUseCase
import com.presencify_admin.home.feature_academics.domain.use_case.GetSchemeByIdUseCase
import com.presencify_admin.home.feature_academics.domain.use_case.GetUniversitiesUseCase
import com.presencify_admin.home.feature_academics.domain.use_case.UpdateSchemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AddSchemeViewModel @Inject constructor(
    private val addSchemeUseCase: AddSchemeUseCase,
    private val updateSchemeUseCase: UpdateSchemeUseCase,
    private val getSchemeByIdUseCase: GetSchemeByIdUseCase,
    private val getUniversitiesUseCase: GetUniversitiesUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(AddSchemeState())
    val state = _state.asStateFlow()

    init {
        savedStateHandle.get<Int>("schemeId")?.let { id ->
            _state.update { it.copy(schemeId = id) }
            getScheme(id)
        }
        getUniversities()
    }

    fun onAction(action: AddSchemeAction) {
        when (action) {
            is AddSchemeAction.NameChanged -> _state.update { it.copy(name = action.newName, nameError = null) }
            is AddSchemeAction.AbbreviationChanged -> _state.update { it.copy(abbreviation = action.newAbbreviation, abbreviationError = null) }
            is AddSchemeAction.UniversityChanged -> _state.update { it.copy(selectedUniversity = action.newUniversity, universityError = null) }
            is AddSchemeAction.UniversityDropDownVisibilityChanged -> _state.update { it.copy(isUniversityDropDownOpen = action.isVisible) }
            AddSchemeAction.SubmitClicked -> {
                if (validate()) {
                    if (state.value.schemeId == null) addScheme() else updateScheme()
                }
            }
            AddSchemeAction.OnAddUpdateSuccessNavigation -> {}
        }
    }

    private fun getScheme(schemeId: Int) {
        getSchemeByIdUseCase(schemeId).onEach { result ->
            when (result) {
                is Resource.Success -> result.data?.let { scheme ->
                    _state.update {
                        it.copy(
                            name = scheme.name,
                            selectedUniversity = scheme.university,
                            isLoading = false
                        )
                    }
                }
                is Resource.Error -> SnackbarController.sendEvent(SnackbarEvent(result.message ?: "Failed to load scheme"))
                is Resource.Loading -> _state.update { it.copy(isLoading = true) }
            }
        }.launchIn(viewModelScope)
    }

    private fun getUniversities() {
        getUniversitiesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> _state.update { it.copy(universityOptions = result.data.orEmpty().toImmutableList()) }
                is Resource.Error -> SnackbarController.sendEvent(SnackbarEvent(result.message ?: "Failed to fetch universities"))
                else -> Unit
            }
        }.launchIn(viewModelScope)
    }

    private fun validate(): Boolean {
        var valid = true
        if (state.value.name.isBlank()) {
            _state.update { it.copy(nameError = "Scheme name is required") }
            valid = false
        }
        if (state.value.abbreviation.isBlank()) {
            _state.update { it.copy(abbreviationError = "Abbreviation is required") }
            valid = false
        }
        if (state.value.selectedUniversity == null) {
            _state.update { it.copy(universityError = "University is required") }
            valid = false
        }
        return valid
    }

    private fun addScheme() {
        addSchemeUseCase(
            AddSchemeRequest(
                name = state.value.name,
                universityId = state.value.selectedUniversity!!.id
            )
        ).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    SnackbarController.sendEvent(SnackbarEvent("Scheme added successfully"))
                    _state.update { it.copy(isSubmitted = true, isLoading = false) }
                }
                is Resource.Error -> {
                    SnackbarController.sendEvent(SnackbarEvent(result.message ?: "Failed to add scheme"))
                    _state.update { it.copy(isLoading = false) }
                }
                is Resource.Loading -> _state.update { it.copy(isLoading = true) }
            }
        }.launchIn(viewModelScope)
    }

    private fun updateScheme() {
        updateSchemeUseCase(
            UpdateSchemeRequest(
                id = state.value.schemeId.toString(),
                name = state.value.name,
            )
        ).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    SnackbarController.sendEvent(SnackbarEvent("Scheme updated successfully"))
                    _state.update { it.copy(isSubmitted = true, isLoading = false) }
                }
                is Resource.Error -> {
                    SnackbarController.sendEvent(SnackbarEvent(result.message ?: "Failed to update scheme"))
                    _state.update { it.copy(isLoading = false) }
                }
                is Resource.Loading -> _state.update { it.copy(isLoading = true) }
            }
        }.launchIn(viewModelScope)
    }
}
