package com.attendify_admin.home.feature_academics.presentation.add_semester

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.presentation.components.global_snackbar.SnackbarController
import com.attendify_admin.common.presentation.components.global_snackbar.SnackbarEvent
import com.attendify_admin.home.feature_academics.data.remote.dto.request.AddSemesterRequest
import com.attendify_admin.home.feature_academics.data.remote.dto.request.UpdateSemesterRequest
import com.attendify_admin.home.feature_academics.domain.use_case.AddSemesterUseCase
import com.attendify_admin.home.feature_academics.domain.use_case.GetBranchesUseCase
import com.attendify_admin.home.feature_academics.domain.use_case.GetSchemesUseCase
import com.attendify_admin.home.feature_academics.domain.use_case.GetSemesterByIdUseCase
import com.attendify_admin.home.feature_academics.domain.use_case.UpdateSemesterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AddSemesterViewModel @Inject constructor(
    private val addSemesterUseCase: AddSemesterUseCase,
    private val updateSemesterUseCase: UpdateSemesterUseCase,
    private val getSemesterByIdUseCase: GetSemesterByIdUseCase,
    private val getBranchesUseCase: GetBranchesUseCase,
    private val getSchemesUseCase: GetSchemesUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(AddSemesterState())
    val state = _state.asStateFlow()

    init {
        savedStateHandle.get<Int>("semesterId")?.let { id ->
            _state.update { it.copy(semesterId = id) }
            getSemester(id)
        }
        getBranches()
        getSchemes()
    }

    fun onAction(action: AddSemesterAction) {
        when (action) {
            is AddSemesterAction.BranchChanged -> _state.update { it.copy(selectedBranch = action.branch, branchError = null) }
            is AddSemesterAction.AcademicYearChanged -> _state.update { it.copy(selectedAcademicYear = action.year, academicYearError = null) }
            is AddSemesterAction.SemesterNumberChanged -> _state.update { it.copy(selectedSemesterNumber = action.number, semesterNumberError = null) }
            is AddSemesterAction.SchemeChanged -> _state.update { it.copy(selectedScheme = action.scheme, schemeError = null) }
            is AddSemesterAction.StartDateChanged -> _state.update { it.copy(startDate = action.date, startDateError = null) }
            is AddSemesterAction.EndDateChanged -> _state.update { it.copy(endDate = action.date, endDateError = null) }

            is AddSemesterAction.BranchDropdownVisibilityChanged -> _state.update { it.copy(isBranchDropdownOpen = action.visible) }
            is AddSemesterAction.AcademicYearDropdownVisibilityChanged -> _state.update { it.copy(isAcademicYearDropdownOpen = action.visible) }
            is AddSemesterAction.SemesterNumberDropdownVisibilityChanged -> _state.update { it.copy(isSemesterNumberDropdownOpen = action.visible) }
            is AddSemesterAction.SchemeDropdownVisibilityChanged -> _state.update { it.copy(isSchemeDropdownOpen = action.visible) }

            AddSemesterAction.ShowStartDatePicker -> _state.update { it.copy(isStartDatePickerVisible = true) }
            AddSemesterAction.ShowEndDatePicker -> _state.update { it.copy(isEndDatePickerVisible = true) }
            AddSemesterAction.HideStartDatePicker -> _state.update { it.copy(isStartDatePickerVisible = false) }
            AddSemesterAction.HideEndDatePicker -> _state.update { it.copy(isEndDatePickerVisible = false) }

            AddSemesterAction.SubmitClicked -> {
                if (validate()) {
                    if (state.value.semesterId == null) addSemester()
                    else updateSemester()
                }
            }

            AddSemesterAction.OnAddUpdateSuccessNavigation -> {}
        }
    }

    private fun validate(): Boolean {
        var valid = true
        val s = state.value

        if (s.selectedBranch == null) {
            _state.update { it.copy(branchError = "Branch required") }
            valid = false
        }
        if (s.selectedAcademicYear.isBlank()) {
            _state.update { it.copy(academicYearError = "Academic year required") }
            valid = false
        }
        if (s.selectedSemesterNumber == null) {
            _state.update { it.copy(semesterNumberError = "Semester number required") }
            valid = false
        }
        if (s.selectedScheme == null) {
            _state.update { it.copy(schemeError = "Scheme required") }
            valid = false
        }
        if (s.startDate.isNullOrBlank()) {
            _state.update { it.copy(startDateError = "Start date required") }
            valid = false
        }
        if (s.endDate.isNullOrBlank()) {
            _state.update { it.copy(endDateError = "End date required") }
            valid = false
        }

        return valid
    }

    private fun addSemester() {
        val academicYears = state.value.selectedAcademicYear.split("-")
        val startYear = academicYears[0].trim().toInt()
        val endYear = academicYears[1].trim().toInt()

        val request = AddSemesterRequest(
            branchId = state.value.selectedBranch!!.id,
            semesterNumber = state.value.selectedSemesterNumber!!,
            academicStartYear = startYear,
            academicEndYear = endYear,
            startDate = state.value.startDate!!,
            endDate = state.value.endDate!!,
            schemeId = state.value.selectedScheme!!.id,
            optionalCourseIds = null // Assuming it's not handled yet
        )

        addSemesterUseCase(request).onEach { result ->
            when (result) {
                is Resource.Loading -> _state.update { it.copy(isLoading = true) }
                is Resource.Success -> {
                    _state.update { it.copy(isSubmitted = true, isLoading = false) }
                    SnackbarController.sendEvent(SnackbarEvent("Semester added successfully"))
                }
                is Resource.Error -> {
                    _state.update { it.copy(isLoading = false) }
                    SnackbarController.sendEvent(SnackbarEvent(result.message ?: "Failed to add semester"))
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun updateSemester() {
        updateSemesterUseCase(
            UpdateSemesterRequest(
                semesterId = state.value.semesterId!!,
                startDate = state.value.startDate!!,
                endDate = state.value.endDate!!
            )
        ).onEach { result ->
            when (result) {
                is Resource.Loading -> _state.update { it.copy(isLoading = true) }
                is Resource.Success -> {
                    _state.update { it.copy(isSubmitted = true, isLoading = false) }
                    SnackbarController.sendEvent(SnackbarEvent("Semester updated successfully"))
                }
                is Resource.Error -> {
                    _state.update { it.copy(isLoading = false) }
                    SnackbarController.sendEvent(SnackbarEvent(result.message ?: "Failed to update semester"))
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getSemester(id: Int) {
        getSemesterByIdUseCase(id).onEach { result ->
            when (result) {
                is Resource.Loading -> _state.update { it.copy(isLoading = true) }
                is Resource.Success -> {
                    result.data?.let { semester ->
                        val academicYear = "${semester.academicStartYear} - ${semester.academicEndYear}"
                        _state.update {
                            it.copy(
                                selectedBranch = semester.branch,
                                selectedAcademicYear = academicYear,
                                selectedSemesterNumber = semester.semesterNumber,
                                startDate = semester.startDate,
                                endDate = semester.endDate,
                                selectedScheme = semester.scheme,
                                isLoading = false
                            )
                        }
                    }
                }
                is Resource.Error -> {
                    _state.update { it.copy(isLoading = false) }
                    SnackbarController.sendEvent(SnackbarEvent(result.message ?: "Failed to load semester"))
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getBranches() {
        getBranchesUseCase(null).onEach { result ->
            when (result) {
                is Resource.Success -> _state.update { it.copy(branchOptions = result.data ?: emptyList()) }
                is Resource.Error -> SnackbarController.sendEvent(SnackbarEvent(result.message ?: "Failed to load branches"))
                else -> Unit
            }
        }.launchIn(viewModelScope)
    }

    private fun getSchemes() {
        getSchemesUseCase(null).onEach { result ->
            when (result) {
                is Resource.Success -> _state.update { it.copy(schemeOptions = result.data ?: emptyList()) }
                is Resource.Error -> SnackbarController.sendEvent(SnackbarEvent(result.message ?: "Failed to load schemes"))
                else -> Unit
            }
        }.launchIn(viewModelScope)
    }
}