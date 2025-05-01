package com.attendify_admin.users.presentation.search_student

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.attendify_admin.academics.domain.use_case.GetBranchesUseCase
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.users.domain.use_case.GetStudentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchStudentViewModel @Inject constructor(
    private val getStudentsUseCase: GetStudentsUseCase,
    getBranchesUseCase: GetBranchesUseCase
) : ViewModel() {

    var state = MutableStateFlow(SearchStudentState())
        private set

    init {
        getBranchesUseCase(searchQuery = null).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    state.value =
                        state.value.copy(dialogText = result.message, areBranchesLoading = false)
                }

                is Resource.Loading -> {
                    // do nothing let it load in the background until the user is filling above details
                }

                is Resource.Success -> {
                    state.value = state.value.copy(
                        branchOptions = result.data?.data ?: emptyList(),
                        areBranchesLoading = false
                    )
                }
            }
        }.launchIn(viewModelScope)
        getStudents()
    }


    private fun getStudents() {
        val students = getStudentsUseCase(
            searchQuery = state.value.searchQuery,
            branchIds = state.value.selectedBranches.map { branch ->
                branch.id
            },
            semesterNumbers = state.value.selectedSemesters,
            academicStartYearOfSemester = state.value.selectedAcademicStartYearOfSemester?.toIntOrNull(),
            academicEndYearOfSemester = state.value.selectedAcademicEndYearOfSemester?.toIntOrNull(),
            academicStatuses = state.value.selectedAcademicStatuses,
            admissionTypes = state.value.selectedAdmissionTypes.map { admissionType ->
                when (admissionType) {
                    "First Year" -> "FE"
                    "Direct Second Year" -> "DSE"
                    else -> ""
                }
            },
            admissionYear = state.value.selectedAdmissionYear?.toIntOrNull(),
            currentSemester = true,
        ).cachedIn(viewModelScope)
        state.value = state.value.copy(students = students)
    }

    fun onEvent(event: SearchStudentEvent) {
        when (event) {

            is SearchStudentEvent.AcademicEndYearOfSemesterDropDownVisibilityChanged -> {
                state.value =
                    state.value.copy(isAcademicEndYearOfSemesterDropDownVisible = event.isVisible)
            }


            is SearchStudentEvent.AcademicStartYearOfSemesterDropDownVisibilityChanged -> {
                state.value =
                    state.value.copy(isAcademicStartYearOfSemesterDropDownVisible = event.isVisible)
            }

            SearchStudentEvent.ApplyFilters -> {
                getStudents()
                state.value = state.value.copy(
                    isBottomSheetVisible = false
                )
            }


            SearchStudentEvent.ResetFilters -> {
                state.value = state.value.copy(
                    selectedBranches = emptyList(),
                    selectedSemesters = emptyList(),
                    selectedAcademicStartYearOfSemester = null,
                    selectedAcademicEndYearOfSemester = null,
                    selectedAcademicStatuses = emptyList(),
                    selectedAdmissionTypes = emptyList(),
                    selectedSchemes = emptyList(),
                    selectedDivisions = emptyList(),
                    selectedBatches = emptyList(),
                    selectedAdmissionYear = null,
                )
            }

            SearchStudentEvent.FetchStudents -> {
                state.value = state.value.copy(
                    isFetchingStudents = true
                )
                getStudents()
            }

            is SearchStudentEvent.SearchQueryChanged -> {
                state.value = state.value.copy(searchQuery = event.searchQuery)
            }


            SearchStudentEvent.DismissAlertDialog -> {
                state.value = state.value.copy(dialogText = null)
            }

            is SearchStudentEvent.AcademicEndYearOfSemesterChanged -> {
                val startYear = state.value.selectedAcademicStartYearOfSemester?.toIntOrNull()
                val endYear = event.year.toIntOrNull()

                if (startYear != null && endYear != null && startYear >= endYear) {
                    state.value = state.value.copy(
                        dialogText = "Academic start year should be less than academic end year"
                    )
                    return
                }
                state.value = state.value.copy(selectedAcademicEndYearOfSemester = event.year)

            }

            is SearchStudentEvent.AcademicStartYearOfSemesterChanged -> {
                val startYear = event.year.toIntOrNull()
                val endYear = state.value.selectedAcademicEndYearOfSemester?.toIntOrNull()

                if (startYear != null && endYear != null && startYear >= endYear) {
                    state.value = state.value.copy(
                        dialogText = "Academic start year should be less than academic end year"
                    )
                    return
                }
                state.value = state.value.copy(selectedAcademicStartYearOfSemester = event.year)
            }

            is SearchStudentEvent.AcademicStatusAdded -> {
                state.value = state.value.copy(
                    selectedAcademicStatuses = state.value.selectedAcademicStatuses.plus(
                        event.academicStatus
                    )
                )
            }

            is SearchStudentEvent.AcademicStatusRemoved -> {
                state.value = state.value.copy(
                    selectedAcademicStatuses = state.value.selectedAcademicStatuses.minus(
                        event.academicStatus
                    )
                )
            }

            is SearchStudentEvent.AdmissionTypeAdded -> {
                state.value = state.value.copy(
                    selectedAdmissionTypes = state.value.selectedAdmissionTypes.plus(
                        event.type
                    )
                )
            }

            is SearchStudentEvent.AdmissionTypeRemoved -> {
                state.value = state.value.copy(
                    selectedAdmissionTypes = state.value.selectedAdmissionTypes.minus(
                        event.type
                    )
                )
            }

//            is SearchStudentEvent.BatchAdded -> {
//                state.value = state.value.copy(
//                    selectedBatches = state.value.selectedBatches.plus(
//                        event.batch
//                    )
//                )
//            }
//
//            is SearchStudentEvent.BatchRemoved -> {
//                state.value = state.value.copy(
//                    selectedBatches = state.value.selectedBatches.minus(
//                        event.batch
//                    )
//                )
//            }

            is SearchStudentEvent.BranchAdded -> {
                state.value = state.value.copy(
                    selectedBranches = state.value.selectedBranches.plus(
                        event.branch
                    )
                )
            }

            is SearchStudentEvent.BranchRemoved -> {
                state.value = state.value.copy(
                    selectedBranches = state.value.selectedBranches.minus(
                        event.branch
                    )
                )
            }

//            is SearchStudentEvent.DivisionAdded -> {
//                state.value = state.value.copy(
//                    selectedDivisions = state.value.selectedDivisions.plus(
//                        event.division
//                    )
//                )
//            }
//
//            is SearchStudentEvent.DivisionRemoved -> {
//                state.value = state.value.copy(
//                    selectedDivisions = state.value.selectedDivisions.minus(
//                        event.division
//                    )
//                )
//            }

//            is SearchStudentEvent.SchemeAdded -> {
//                state.value = state.value.copy(
//                    selectedSchemes = state.value.selectedSchemes.plus(
//                        event.scheme
//                    )
//                )
//            }
//
//            is SearchStudentEvent.SchemeRemoved -> {
//                state.value = state.value.copy(
//                    selectedSchemes = state.value.selectedSchemes.minus(
//                        event.scheme
//                    )
//                )
//            }

            is SearchStudentEvent.SemesterAdded -> {
                state.value = state.value.copy(
                    selectedSemesters = state.value.selectedSemesters.plus(
                        event.semester
                    )
                )
            }

            is SearchStudentEvent.SemesterRemoved -> {
                state.value = state.value.copy(
                    selectedSemesters = state.value.selectedSemesters.minus(
                        event.semester
                    )
                )
            }

            is SearchStudentEvent.AdmissionYearChanged -> {
                state.value = state.value.copy(selectedAdmissionYear = event.year)
            }

            is SearchStudentEvent.AdmissionYearDropDownVisibilityChanged -> {
                state.value =
                    state.value.copy(isAdmissionYearDropDownVisible = event.isVisible)
            }

            is SearchStudentEvent.BottomSheetVisibilityChanged -> {
                state.value = state.value.copy(isBottomSheetVisible = event.newVisibility)
            }


            is SearchStudentEvent.SearchExpandedChange -> {
                state.value = state.value.copy(isSearchExpanded = event.isExpanded)
            }

            is SearchStudentEvent.ShowAlertDialog -> {
                state.value = state.value.copy(dialogText = event.message)
            }
        }
    }
}