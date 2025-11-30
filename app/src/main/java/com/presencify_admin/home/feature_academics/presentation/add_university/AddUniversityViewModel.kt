package com.presencify_admin.home.feature_academics.presentation.add_university

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.presencify_admin.common.data.remote.Resource
import com.presencify_admin.common.presentation.components.global_snackbar.SnackbarController
import com.presencify_admin.common.presentation.components.global_snackbar.SnackbarEvent
import com.presencify_admin.home.feature_academics.data.remote.dto.request.AddUniversityRequest
import com.presencify_admin.home.feature_academics.data.remote.dto.request.UpdateUniversityRequest
import com.presencify_admin.home.feature_academics.domain.use_case.AddUniversityUseCase
import com.presencify_admin.home.feature_academics.domain.use_case.GetUniversityByIdUseCase
import com.presencify_admin.home.feature_academics.domain.use_case.UpdateUniversityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject
@HiltViewModel
class AddUniversityViewModel @Inject constructor(
    private val addUniversityUseCase: AddUniversityUseCase,
    private val updateUniversityUseCase: UpdateUniversityUseCase,
    private val getUniversityByIdUseCase: GetUniversityByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(AddUniversityState())
    val state = _state.asStateFlow()

    init {
        savedStateHandle.get<Int>("universityId")?.let { id ->
            _state.update { it.copy(universityId = id) }
            getUniversity(id)
        }
    }

    fun onAction(action: AddUniversityAction) {
        when (action) {
            is AddUniversityAction.NameChanged -> {
                _state.update { it.copy(name = action.newName, nameError = null) }
            }

            is AddUniversityAction.AbbreviationChanged -> {
                _state.update { it.copy(abbreviation = action.newAbbreviation, abbreviationError = null) }
            }

            AddUniversityAction.SubmitClicked -> {
                if (validate()) {
                    if (state.value.universityId == null) addUniversity()
                    else updateUniversity()
                }
            }

            AddUniversityAction.OnAddUpdateSuccessNavigation -> {}
        }
    }

    private fun getUniversity(id: Int) {
        getUniversityByIdUseCase(id).onEach { result ->
            when (result) {
                is Resource.Loading -> _state.update { it.copy(isLoading = true) }

                is Resource.Success -> {
                    result.data?.let { university ->
                        _state.update {
                            it.copy(
                                isLoading = false,
                                name = university.name,
                                abbreviation = university.abbreviation
                            )
                        }
                    }
                }

                is Resource.Error -> {
                    _state.update { it.copy(isLoading = false) }
                    SnackbarController.sendEvent(SnackbarEvent(result.message ?: "Failed to load university"))
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun validate(): Boolean {
        var valid = true

        if (state.value.name.isBlank()) {
            _state.update { it.copy(nameError = "Name is required") }
            valid = false
        }

        if (state.value.abbreviation.isBlank()) {
            _state.update { it.copy(abbreviationError = "Abbreviation is required") }
            valid = false
        }

        return valid
    }

    private fun addUniversity() {
        addUniversityUseCase(
            AddUniversityRequest(
                name = state.value.name,
                abbreviation = state.value.abbreviation
            )
        ).onEach { result ->
            when (result) {
                is Resource.Loading -> _state.update { it.copy(isLoading = true) }

                is Resource.Success -> {
                    _state.update { it.copy(isLoading = false, isSubmitted = true) }
                    SnackbarController.sendEvent(SnackbarEvent("University added successfully"))
                }

                is Resource.Error -> {
                    _state.update { it.copy(isLoading = false) }
                    SnackbarController.sendEvent(SnackbarEvent(result.message ?: "Failed to add university"))
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun updateUniversity() {
        updateUniversityUseCase(
            UpdateUniversityRequest(
                id = state.value.universityId.toString(),
                name = state.value.name,
                abbreviation = state.value.abbreviation
            )
        ).onEach { result ->
            when (result) {
                is Resource.Loading -> _state.update { it.copy(isLoading = true) }

                is Resource.Success -> {
                    _state.update { it.copy(isLoading = false, isSubmitted = true) }
                    SnackbarController.sendEvent(SnackbarEvent("University updated successfully"))
                }

                is Resource.Error -> {
                    _state.update { it.copy(isLoading = false) }
                    SnackbarController.sendEvent(SnackbarEvent(result.message ?: "Failed to update university"))
                }
            }
        }.launchIn(viewModelScope)
    }
}
