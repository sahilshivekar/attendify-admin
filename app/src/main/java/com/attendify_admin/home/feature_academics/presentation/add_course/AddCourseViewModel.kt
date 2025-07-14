package com.attendify_admin.home.feature_academics.presentation.add_course

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.presentation.components.global_snackbar.SnackbarController
import com.attendify_admin.common.presentation.components.global_snackbar.SnackbarEvent
import com.attendify_admin.home.feature_academics.data.remote.dto.request.AddCourseRequest
import com.attendify_admin.home.feature_academics.data.remote.dto.request.UpdateCourseRequest
import com.attendify_admin.home.feature_academics.domain.use_case.AddCourseUseCase
import com.attendify_admin.home.feature_academics.domain.use_case.GetCourseByIdUseCase
import com.attendify_admin.home.feature_academics.domain.use_case.GetSchemesUseCase
import com.attendify_admin.home.feature_academics.domain.use_case.UpdateCourseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AddCourseViewModel @Inject constructor(
    private val addCourseUseCase: AddCourseUseCase,
    private val updateCourseUseCase: UpdateCourseUseCase,
    private val getSchemesUseCase: GetSchemesUseCase,
    private val getCourseByIdUseCase: GetCourseByIdUseCase, // if you want to edit
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(AddCourseState())
    val state = _state.asStateFlow()

    init {
        savedStateHandle.get<Int>("courseId")?.let { id ->
            _state.update { it.copy(courseId = id) }
            getCourse(id)
        }
        getSchemes()
    }

    fun onAction(action: AddCourseAction) {
        when (action) {
            is AddCourseAction.CodeChanged -> _state.update { it.copy(code = action.newCode, codeError = null) }
            is AddCourseAction.NameChanged -> _state.update { it.copy(name = action.newName, nameError = null) }
            is AddCourseAction.OptionalSubjectChanged -> _state.update { it.copy(optionalSubject = action.newOptionalSubject, optionalSubjectError = null) }
            is AddCourseAction.SchemeChanged -> _state.update { it.copy(selectedScheme = action.newScheme, isSchemeError = null) }
            is AddCourseAction.SchemeDropDownVisibilityChanged -> _state.update { it.copy(isSchemeDropDownOpen = action.isVisible) }
            AddCourseAction.SubmitClicked -> {
                if (validate()) {
                    if (state.value.courseId == null) addCourse() else updateCourse()
                }
            }
            AddCourseAction.OnAddUpdateSuccessNavigation -> {}
        }
    }

    private fun getSchemes() {
        getSchemesUseCase(null).onEach { result ->
            when (result) {
                is Resource.Error -> SnackbarController.sendEvent(SnackbarEvent(result.message ?: "Error fetching schemes"))
                is Resource.Success -> _state.update { it.copy(schemeOptions = result.data.orEmpty().toImmutableList()) }
                else -> Unit
            }
        }.launchIn(viewModelScope)
    }

    private fun getCourse(courseId: Int) {
        getCourseByIdUseCase(courseId).onEach { result ->
            when (result) {
                is Resource.Success -> result.data?.let { course ->
                    _state.update {
                        it.copy(
                            name = course.name,
                            code = course.code,
                            optionalSubject = course.optionalSubject ?: "",
                            selectedScheme = course.scheme,
                            isLoading = false
                        )
                    }
                }
                is Resource.Error -> SnackbarController.sendEvent(SnackbarEvent(result.message ?: "Failed to load course"))
                is Resource.Loading -> _state.update { it.copy(isLoading = true) }
            }
        }.launchIn(viewModelScope)
    }

    private fun validate(): Boolean {
        var valid = true
        if (state.value.name.isBlank()) {
            _state.update { it.copy(nameError = "Course name is required") }
            valid = false
        }
        if (state.value.code.isBlank()) {
            _state.update { it.copy(codeError = "Course code is required") }
            valid = false
        }
        if (state.value.optionalSubject.isBlank()) {
            _state.update { it.copy(optionalSubjectError = "OptionalSubject is required") }
            valid = false
        }
        if (state.value.selectedScheme == null) {
            _state.update { it.copy(isSchemeError = "Scheme is required") }
            valid = false
        }
        return valid
    }

    private fun addCourse() {
        addCourseUseCase(
            AddCourseRequest(
                name = state.value.name,
                optionalSubject = state.value.optionalSubject.ifBlank { null },
                code = state.value.code,
                schemeId = state.value.selectedScheme!!.id
            )
        ).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    SnackbarController.sendEvent(SnackbarEvent("Course added successfully"))
                    _state.update { it.copy(isSubmitted = true, isLoading = false) }
                }
                is Resource.Error -> {
                    SnackbarController.sendEvent(SnackbarEvent(result.message ?: "Failed to add course"))
                    _state.update { it.copy(isLoading = false) }
                }
                is Resource.Loading -> _state.update { it.copy(isLoading = true) }
            }
        }.launchIn(viewModelScope)
    }

    private fun updateCourse() {
        updateCourseUseCase(
            UpdateCourseRequest(
                id = state.value.courseId.toString(),
                code = state.value.code,
                name = state.value.name,
                optionalSubject = state.value.optionalSubject,
                schemeId = state.value.selectedScheme!!.id
            )
        ).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    SnackbarController.sendEvent(SnackbarEvent("Course updated successfully"))
                    _state.update { it.copy(isSubmitted = true, isLoading = false) }
                }
                is Resource.Error -> {
                    SnackbarController.sendEvent(SnackbarEvent(result.message ?: "Failed to update course"))
                    _state.update { it.copy(isLoading = false) }
                }
                is Resource.Loading -> _state.update { it.copy(isLoading = true) }
            }
        }.launchIn(viewModelScope)
    }
}
