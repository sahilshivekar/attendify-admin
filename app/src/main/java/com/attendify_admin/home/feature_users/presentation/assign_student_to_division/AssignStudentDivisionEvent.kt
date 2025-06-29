package com.attendify_admin.home.feature_users.presentation.assign_student_to_division

import com.attendify_admin.common.domain.model.Branch
import com.attendify_admin.common.domain.model.Division

sealed class AssignStudentDivisionEvent {
    data class DivisionSelected(val division: Division) : AssignStudentDivisionEvent()
    data class BranchSelected(val branch: Branch) : AssignStudentDivisionEvent()
    data class AcademicYearSelected(val year: String) : AssignStudentDivisionEvent()

    data class SemesterDropdownVisibilityChanged(val expanded: Boolean) :
        AssignStudentDivisionEvent()

    data class BranchDropdownVisibilityChanged(val expanded: Boolean) : AssignStudentDivisionEvent()
    data class AcademicYearDropdownVisibilityChanged(val expanded: Boolean) :
        AssignStudentDivisionEvent()

    data object FetchMatchingDivisions : AssignStudentDivisionEvent()
    data class SemesterNumberSelected(val semesterNumber: Int) : AssignStudentDivisionEvent()
    data object AssignStudentsClicked : AssignStudentDivisionEvent()
    data class RemoveStudentClicked(val studentId: Int) : AssignStudentDivisionEvent()
}