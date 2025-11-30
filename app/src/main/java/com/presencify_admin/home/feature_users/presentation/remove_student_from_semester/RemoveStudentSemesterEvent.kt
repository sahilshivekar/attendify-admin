package com.presencify_admin.home.feature_users.presentation.remove_student_from_semester

import com.presencify_admin.common.domain.model.Branch
import com.presencify_admin.common.domain.model.Semester

sealed class RemoveStudentSemesterEvent {
    data class SemesterSelected(val semester: Semester) : RemoveStudentSemesterEvent()
    data class BranchSelected(val branch: Branch) : RemoveStudentSemesterEvent()
    data class AcademicYearSelected(val year: String) : RemoveStudentSemesterEvent()

    data class SemesterDropdownVisibilityChanged(val expanded: Boolean) : RemoveStudentSemesterEvent()
    data class BranchDropdownVisibilityChanged(val expanded: Boolean) : RemoveStudentSemesterEvent()
    data class AcademicYearDropdownVisibilityChanged(val expanded: Boolean) : RemoveStudentSemesterEvent()
    data object FetchMatchingSemesters : RemoveStudentSemesterEvent()
    data class SemesterNumberSelected(val semesterNumber: Int): RemoveStudentSemesterEvent()
    data class UnassignStudentClicked(val studentSemesterId: Int): RemoveStudentSemesterEvent()
}