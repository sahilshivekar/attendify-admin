package com.attendify_admin.home.feature_users.presentation.search_student

import com.attendify_admin.common.domain.model.Branch


sealed class SearchStudentEvent {
    data class SearchQueryChanged(val searchQuery: String) : SearchStudentEvent()

    data class BranchAdded(val branch: Branch) : SearchStudentEvent()
    data class BranchRemoved(val branch: Branch) : SearchStudentEvent()

    data class SemesterAdded(val semester: Int) : SearchStudentEvent()
    data class SemesterRemoved(val semester: Int) : SearchStudentEvent()

    data class AcademicStartYearOfSemesterChanged(val year: String) : SearchStudentEvent()
    data class AcademicEndYearOfSemesterChanged(val year: String) : SearchStudentEvent()


    data class AdmissionTypeAdded(val type: String) : SearchStudentEvent()
    data class AdmissionTypeRemoved(val type: String) : SearchStudentEvent()

    data object ResetFilters : SearchStudentEvent()
    data object ApplyFilters : SearchStudentEvent()

    data object FetchStudents : SearchStudentEvent()

    data class AcademicStartYearOfSemesterDropDownVisibilityChanged(val isVisible: Boolean) :
        SearchStudentEvent()

    data class AcademicEndYearOfSemesterDropDownVisibilityChanged(val isVisible: Boolean) :
        SearchStudentEvent()

    data class ShowAlertDialog(val message: String) : SearchStudentEvent()
    data class AdmissionYearChanged(val year: String?) : SearchStudentEvent()
    data class AdmissionYearDropDownVisibilityChanged(val isVisible: Boolean) : SearchStudentEvent()

    data class BottomSheetVisibilityChanged(val newVisibility: Boolean) : SearchStudentEvent()

    data class SearchExpandedChange(val isExpanded: Boolean) : SearchStudentEvent()
}