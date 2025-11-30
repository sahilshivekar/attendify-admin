package com.presencify_admin.home.feature_users.presentation.search_student

import com.presencify_admin.common.domain.model.Batch
import com.presencify_admin.common.domain.model.Branch
import com.presencify_admin.common.domain.model.Division
import com.presencify_admin.common.domain.model.Scheme


sealed class SearchStudentEvent {
    data class SearchQueryChanged(val searchQuery: String) : SearchStudentEvent()

    data class BranchAdded(val branch: Branch) : SearchStudentEvent()
    data class BranchRemoved(val branch: Branch) : SearchStudentEvent()

    data class SemesterAdded(val semester: Int) : SearchStudentEvent()
    data class SemesterRemoved(val semester: Int) : SearchStudentEvent()

    data class AcademicYearOfSemesterChanged(val year: String?) : SearchStudentEvent()
    data class DropoutYearChanged(val dropoutYear: String?) : SearchStudentEvent()

    data class AdmissionTypeAdded(val type: String) : SearchStudentEvent()
    data class AdmissionTypeRemoved(val type: String) : SearchStudentEvent()

    data object ResetFilters : SearchStudentEvent()
    data object ApplyFilters : SearchStudentEvent()

    data object FetchStudents : SearchStudentEvent() data class AdmissionYearChanged(val year: String?) : SearchStudentEvent()
    data class AdmissionYearDropDownVisibilityChanged(val isVisible: Boolean) : SearchStudentEvent()

    data class BottomSheetVisibilityChanged(val newVisibility: Boolean) : SearchStudentEvent()

    data class SearchExpandedChange(val isExpanded: Boolean) : SearchStudentEvent()

    data class SelectedSchemeChanged(val scheme: Scheme?) : SearchStudentEvent()
    data class SelectedDivisionChanged(val division: Division?) : SearchStudentEvent()
    data class SelectedBatchChanged(val batch: Batch?) : SearchStudentEvent()

    data class StudentSelected(val id: Int) : SearchStudentEvent()
    data class StudentDeselected(val id: Int) : SearchStudentEvent()
    data class SelectionModeChanged(val enabled: Boolean) : SearchStudentEvent()

}