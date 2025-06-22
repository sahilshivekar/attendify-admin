package com.attendify_admin.home.feature_users.presentation.search_student

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.presentation.components.global_snackbar.SnackbarController
import com.attendify_admin.common.presentation.components.global_snackbar.SnackbarEvent
import com.attendify_admin.home.feature_academics.domain.use_case.GetBranchesUseCase
import com.attendify_admin.home.feature_users.domain.use_case.GetStudentsUseCase
import com.attendify_admin.home.feature_users.domain.utils.StudentUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchStudentViewModel @Inject constructor(
    private val getStudentsUseCase: GetStudentsUseCase,
    getBranchesUseCase: GetBranchesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(SearchStudentState())
    val state: StateFlow<SearchStudentState> = _state.asStateFlow()

    init {
        getBranchesUseCase(searchQuery = null).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.update {
                        it.copy(areBranchesLoading = false)
                    }
                    // Send Snackbar event instead of updating dialogText
                    viewModelScope.launch {
                        SnackbarController.sendEvent(
                            SnackbarEvent(
                                message = result.message ?: "Error fetching branches"
                            )
                        )
                    }
                }

                is Resource.Loading -> {
                    // do nothing let it load in the background until the user is filling above details
                }

                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            branchOptions = result.data,
                            areBranchesLoading = false
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
        getStudents()
    }

    private fun getStudents() {
        val students = getStudentsUseCase(
            searchQuery = _state.value.searchQuery,
            branchIds = _state.value.selectedBranches.map { branch ->
                branch.id
            },
            semesterNumbers = _state.value.selectedSemesters,
            academicStartYearOfSemester = _state.value.selectedAcademicStartYearOfSemester?.toIntOrNull(),
            academicEndYearOfSemester = _state.value.selectedAcademicEndYearOfSemester?.toIntOrNull(),
            admissionTypes = _state.value.selectedAdmissionTypes.map { admissionType ->
                when (admissionType) {
                    "First Year" -> "FE"
                    "Direct Second Year" -> "DSE"
                    else -> ""
                }
            },
            admissionYear = _state.value.selectedAdmissionYear?.toIntOrNull(),
            currentSemester = true,
        ).cachedIn(viewModelScope)

        val studentCards = students.map { pagingData ->
            pagingData.map { student ->
                StudentCard(
                    id = student.id,
                    studentName = "${student.firstName} ${if (student.middleName != null) student.middleName + " " else ""}${student.lastName}",
                    studentBranch = student.branch?.abbreviation ?: "",
                    studentYear = if (
                        (student.studentSemesters?.size ?: 0) > 0 &&
                        student.studentSemesters?.first()?.semester?.semesterNumber != null
                    )
                        StudentUtils.getCurrentYearFromSem(
                            student.studentSemesters.first().semester.semesterNumber
                        ) else null,
                    studentImageUrl = student.studentImgUrl
                )
            }

        }
        _state.update {
            it.copy(
                students = studentCards
            )
        }
    }

    fun onEvent(event: SearchStudentEvent) {
        when (event) {

            is SearchStudentEvent.AcademicEndYearOfSemesterDropDownVisibilityChanged -> {
                _state.update {
                    it.copy(isAcademicEndYearOfSemesterDropDownVisible = event.isVisible)
                }
            }


            is SearchStudentEvent.AcademicStartYearOfSemesterDropDownVisibilityChanged -> {
                _state.update {
                    it.copy(isAcademicStartYearOfSemesterDropDownVisible = event.isVisible)
                }
            }

            SearchStudentEvent.ApplyFilters -> {
                getStudents()
                _state.update {
                    it.copy(
                        isBottomSheetVisible = false
                    )
                }
            }


            SearchStudentEvent.ResetFilters -> {
                _state.update {
                    it.copy(
                        selectedBranches = persistentListOf(),
                        selectedSemesters = persistentListOf(),
                        selectedAcademicStartYearOfSemester = null,
                        selectedAcademicEndYearOfSemester = null,
                        selectedAdmissionTypes = persistentListOf(),
                        selectedAdmissionYear = null,
                    )
                }
            }

            SearchStudentEvent.FetchStudents -> {
                _state.update {
                    it.copy(
                        isFetchingStudents = true // This might be better managed by observing PagingData load states
                    )
                }
                getStudents()
            }

            is SearchStudentEvent.SearchQueryChanged -> {
                _state.update { it.copy(searchQuery = event.searchQuery) }
            }


            is SearchStudentEvent.AcademicEndYearOfSemesterChanged -> {
                val startYear = _state.value.selectedAcademicStartYearOfSemester?.toIntOrNull()
                val endYear = event.year.toIntOrNull()

                if (startYear != null && endYear != null && startYear >= endYear) {
                    viewModelScope.launch {
                        SnackbarController.sendEvent(SnackbarEvent("Academic start year should be less than academic end year"))
                    }
                    return
                }
                _state.update { it.copy(selectedAcademicEndYearOfSemester = event.year) }

            }

            is SearchStudentEvent.AcademicStartYearOfSemesterChanged -> {
                val startYear = event.year.toIntOrNull()
                val endYear = _state.value.selectedAcademicEndYearOfSemester?.toIntOrNull()

                if (startYear != null && endYear != null && startYear >= endYear) {
                    viewModelScope.launch {
                        SnackbarController.sendEvent(SnackbarEvent("Academic start year should be less than academic end year"))
                    }
                    return
                }
                _state.update { it.copy(selectedAcademicStartYearOfSemester = event.year) }
            }


            is SearchStudentEvent.AdmissionTypeAdded -> {
                _state.update { currentState ->
                    currentState.copy(
                        selectedAdmissionTypes = currentState.selectedAdmissionTypes.add(
                            event.type
                        )
                    )
                }
            }

            is SearchStudentEvent.AdmissionTypeRemoved -> {
                _state.update { currentState ->
                    currentState.copy(
                        selectedAdmissionTypes = currentState.selectedAdmissionTypes.remove(
                            event.type
                        )
                    )
                }
            }

            is SearchStudentEvent.BranchAdded -> {
                _state.update { currentState ->
                    currentState.copy(
                        selectedBranches = currentState.selectedBranches.add(
                            event.branch
                        )
                    )
                }
            }

            is SearchStudentEvent.BranchRemoved -> {
                _state.update { currentState ->
                    currentState.copy(
                        selectedBranches = currentState.selectedBranches.remove(
                            event.branch
                        )
                    )
                }
            }


            is SearchStudentEvent.SemesterAdded -> {
                _state.update { currentState ->
                    currentState.copy(
                        selectedSemesters = currentState.selectedSemesters.add(
                            event.semester
                        )
                    )
                }
            }

            is SearchStudentEvent.SemesterRemoved -> {
                _state.update { currentState ->
                    currentState.copy(
                        selectedSemesters = currentState.selectedSemesters.remove(
                            event.semester
                        )
                    )
                }
            }

            is SearchStudentEvent.AdmissionYearChanged -> {
                _state.update { it.copy(selectedAdmissionYear = event.year) }
            }

            is SearchStudentEvent.AdmissionYearDropDownVisibilityChanged -> {
                _state.update {
                    it.copy(isAdmissionYearDropDownVisible = event.isVisible)
                }
            }

            is SearchStudentEvent.BottomSheetVisibilityChanged -> {
                _state.update { it.copy(isBottomSheetVisible = event.newVisibility) }
            }


            is SearchStudentEvent.SearchExpandedChange -> {
                _state.update { it.copy(isSearchExpanded = event.isExpanded) }
            }

            is SearchStudentEvent.ShowAlertDialog -> {
                // Replace dialogText update with Snackbar event
                viewModelScope.launch {
                    SnackbarController.sendEvent(SnackbarEvent(event.message))
                }
            }
        }
    }
}