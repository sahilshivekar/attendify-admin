package com.attendify_admin.home.feature_academics.presentation.manage_course

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.attendify_admin.common.data.remote.Resource
import com.attendify_admin.home.feature_academics.domain.use_case.GetBranchesUseCase
import com.attendify_admin.home.feature_academics.domain.use_case.GetCoursesUseCase
import com.attendify_admin.home.feature_academics.domain.use_case.GetSchemesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ManageCourseViewModel @Inject constructor(
    private val getCoursesUseCase: GetCoursesUseCase,
    private val getBranchesUseCase: GetBranchesUseCase,
    private val getSchemesUseCase: GetSchemesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(ManageCourseState())
    val state = _state.asStateFlow()

    private fun getCourses() {
        val courses = getCoursesUseCase(
            searchQuery = state.value.searchQuery,
            branchId = state.value.selectedBranch?.id,
            semesterNumber = state.value.selectedSemester,
            schemeId = state.value.selectedScheme?.id
        ).cachedIn(viewModelScope)

        _state.update {
            it.copy(
                courses = courses
            )
        }
    }

    init {
        fetchBranches()
        fetchSchemes()
        getCourses()
    }

    fun onAction(action: ManageCourseAction) {
        when (action) {
            is ManageCourseAction.FABClick -> Unit
            is ManageCourseAction.CourseClick -> Unit

            is ManageCourseAction.SearchQueryChanged -> {
                _state.update { it.copy(searchQuery = action.query) }
                getCourses()
            }

            is ManageCourseAction.FetchCourses -> {
                getCourses()
            }

            is ManageCourseAction.SelectedBranchChanged -> {
                _state.update { it.copy(selectedBranch = action.branch) }
            }

            is ManageCourseAction.SelectedSemesterChanged -> {
                _state.update { it.copy(selectedSemester = action.semester) }
            }

            is ManageCourseAction.SelectedSchemeChanged -> {
                _state.update { it.copy(selectedScheme = action.scheme) }
            }

            is ManageCourseAction.ApplyFilters -> {
                _state.update { it.copy(isBottomSheetVisible = false) }
            }

            is ManageCourseAction.BottomSheetDismissed -> {
                _state.update { it.copy(isBottomSheetVisible = false) }
            }

            is ManageCourseAction.ResetFilters -> {
                _state.update {
                    it.copy(
                        selectedBranch = null,
                        selectedSemester = null,
                        selectedScheme = null,
                        isBottomSheetVisible = false
                    )
                }
            }
        }
    }

    private fun fetchBranches() {
        getBranchesUseCase(null).onEach { result ->
            _state.update {
                when (result) {
                    is Resource.Success -> it.copy(branches = result.data ?: emptyList())
                    is Resource.Loading -> it.copy(isBranchesLoading = true)
                    is Resource.Error -> it.copy(isBranchesLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun fetchSchemes() {
        getSchemesUseCase(null).onEach { result ->
            _state.update {
                when (result) {
                    is Resource.Success -> it.copy(schemes = result.data ?: emptyList())
                    is Resource.Loading -> it.copy(isSchemesLoading = true)
                    is Resource.Error -> it.copy(isSchemesLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }
}
