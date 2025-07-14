package com.attendify_admin.home.feature_users.presentation.search_student

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.common.presentation.components.global_snackbar.SnackbarController
import com.attendify_admin.common.presentation.components.global_snackbar.SnackbarEvent
import com.attendify_admin.home.feature_academics.domain.use_case.GetAllBatchesUseCase
import com.attendify_admin.home.feature_academics.domain.use_case.GetAllDivisionsUseCase
import com.attendify_admin.home.feature_academics.domain.use_case.GetBranchesUseCase
import com.attendify_admin.home.feature_academics.domain.use_case.GetSchemesUseCase
import com.attendify_admin.home.feature_users.domain.use_case.GetStudentsUseCase
import com.attendify_admin.home.feature_users.domain.utils.StudentUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
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
    private val getAllDivisionsUseCase: GetAllDivisionsUseCase,
    private val getAllBatchesUseCase: GetAllBatchesUseCase,
    getSchemesUseCase: GetSchemesUseCase,
    getBranchesUseCase: GetBranchesUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = MutableStateFlow(SearchStudentState())
    val state: StateFlow<SearchStudentState> = _state.asStateFlow()

    init {
        val isSelectable = savedStateHandle.get<Boolean>("isSelectable") == true
        _state.update { it.copy(isSelectable = isSelectable) }

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

        getSchemesUseCase(searchQuery = null).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.update { it.copy(areSchemesLoading = true) }
                }

                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            schemeOptions = result.data.orEmpty().toImmutableList(),
                            areSchemesLoading = false
                        )
                    }
                }

                is Resource.Error -> {
                    _state.update { it.copy(areSchemesLoading = false) }

                    viewModelScope.launch {
                        SnackbarController.sendEvent(
                            SnackbarEvent(
                                message = result.message ?: "Error fetching schemes"
                            )
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)

        getStudents()
    }

    private fun loadDivisionsAndBatchesIfReady() {

        val state = _state.value

        val semester = state.selectedSemesters.firstOrNull()
        val branchId = state.selectedBranches.firstOrNull()?.id
        val semesterAcademicYear = state.selectedAcademicYearOfSemester

        if (semester != null && branchId != null && semesterAcademicYear != null) {
            Log.d("loadDivisionsAndBatchesIfReady", "$semester $branchId $semesterAcademicYear")
            getAllDivisionsUseCase(
                semesterNumber = semester,
                branchId = branchId,
                academicStartYear = semesterAcademicYear.split("-")[0].trim().toInt(),
                academicEndYear = semesterAcademicYear.split("-")[1].trim().toInt(),
                searchQuery = null
            ).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.update {
                            it.copy(areDivisionsLoading = false)
                        }
                        viewModelScope.launch {
                            SnackbarController.sendEvent(
                                SnackbarEvent(
                                    message = result.message ?: "Error fetching divisions"
                                )
                            )
                        }
                    }

                    is Resource.Loading -> {
                        // You can set a loading flag here if needed
                        _state.update { it.copy(areDivisionsLoading = true) }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                divisionOptions = result.data ?: persistentListOf(),
                                areDivisionsLoading = false
                            )
                        }
                        Log.d("div", _state.value.divisionOptions.toString())
                    }
                }
            }.launchIn(viewModelScope)

            getAllBatchesUseCase(
                semesterNumber = semester,
                branchId = branchId,
                academicStartYear = semesterAcademicYear.split("-")[0].trim().toInt(),
                academicEndYear = semesterAcademicYear.split("-")[1].trim().toInt(),
                searchQuery = null
            ).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.update {
                            it.copy(areBatchesLoading = false)
                        }
                        viewModelScope.launch {
                            SnackbarController.sendEvent(
                                SnackbarEvent(
                                    message = result.message ?: "Error fetching batches"
                                )
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _state.update { it.copy(areBatchesLoading = true) }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                batchOptions = result.data ?: persistentListOf(),
                                areBatchesLoading = false
                            )
                        }
                    }
                }
            }.launchIn(viewModelScope)

        }
    }


    private fun getStudents() {
        val students = getStudentsUseCase(
            searchQuery = _state.value.searchQuery,
            branchIds = _state.value.selectedBranches.map { branch ->
                branch.id
            },
            semesterNumbers = _state.value.selectedSemesters,
            academicStartYearOfSemester = _state.value.selectedAcademicYearOfSemester?.split("-")[0]?.trim()
                ?.toIntOrNull(),
            academicEndYearOfSemester = _state.value.selectedAcademicYearOfSemester?.split("-")[1]?.trim()
                ?.toIntOrNull(),
            admissionTypes = _state.value.selectedAdmissionTypes.map { admissionType ->
                when (admissionType) {
                    "First Year" -> "FE"
                    "Direct Second Year" -> "DSE"
                    else -> ""
                }
            },
            admissionYear = _state.value.selectedAdmissionYear?.toIntOrNull(),
            currentSemester = true,
            dropoutAcademicStartYear = _state.value.selectedDropoutYear?.split("-")[0]?.trim(),
            dropoutAcademicEndYear = _state.value.selectedDropoutYear?.split("-")[1]?.trim(),
            currentBatch = true,
            currentDivision = true,
            divisionId = _state.value.selectedDivision?.id,
            batchId = _state.value.selectedBatch?.id,
            schemeId = _state.value.selectedScheme?.id
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
                            student.studentSemesters.first().semester!!.semesterNumber
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
                        selectedAcademicYearOfSemester = null,
                        selectedAdmissionTypes = persistentListOf(),
                        selectedAdmissionYear = null,
                        selectedDropoutYear = null,
                        selectedScheme = null,
                        selectedBatch = null,
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

            is SearchStudentEvent.AcademicYearOfSemesterChanged -> {
                _state.update { it.copy(selectedAcademicYearOfSemester = event.year) }
                loadDivisionsAndBatchesIfReady()
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
                loadDivisionsAndBatchesIfReady()
            }

            is SearchStudentEvent.BranchRemoved -> {
                _state.update { currentState ->
                    currentState.copy(
                        selectedBranches = currentState.selectedBranches.remove(
                            event.branch
                        )
                    )
                }
                loadDivisionsAndBatchesIfReady()
            }


            is SearchStudentEvent.SemesterAdded -> {
                _state.update { currentState ->
                    currentState.copy(
                        selectedSemesters = currentState.selectedSemesters.add(
                            event.semester
                        )
                    )
                }
                loadDivisionsAndBatchesIfReady()
            }

            is SearchStudentEvent.SemesterRemoved -> {
                _state.update { currentState ->
                    currentState.copy(
                        selectedSemesters = currentState.selectedSemesters.remove(
                            event.semester
                        )
                    )
                }
                loadDivisionsAndBatchesIfReady()
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

            is SearchStudentEvent.DropoutYearChanged -> {
                _state.update { it.copy(selectedDropoutYear = event.dropoutYear) }
            }

            is SearchStudentEvent.SelectedSchemeChanged -> {
                _state.update { it.copy(selectedScheme = event.scheme) }
            }

            is SearchStudentEvent.SelectedDivisionChanged -> {
                _state.update { it.copy(selectedDivision = event.division) }
            }

            is SearchStudentEvent.SelectedBatchChanged -> {
                _state.update { it.copy(selectedBatch = event.batch) }
            }

            is SearchStudentEvent.StudentSelected -> {
                _state.update { it.copy(selectedStudentIds = it.selectedStudentIds.add(event.id)) }
            }

            is SearchStudentEvent.StudentDeselected -> {
                _state.update { it.copy(selectedStudentIds = it.selectedStudentIds.remove(event.id)) }
            }

            is SearchStudentEvent.SelectionModeChanged -> {
                _state.update { it.copy(isSelectable = event.enabled) }
            }

        }
    }
}