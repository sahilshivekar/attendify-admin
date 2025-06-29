package com.attendify_admin.home.feature_users.presentation.assign_student_to_semester

import com.attendify_admin.common.domain.model.Branch
import com.attendify_admin.common.domain.model.Semester

sealed class AssignStudentSemesterEvent {
    data class SemesterSelected(val semester: Semester) : AssignStudentSemesterEvent()
    data class BranchSelected(val branch: Branch) : AssignStudentSemesterEvent()
    data class AcademicYearSelected(val year: String) : AssignStudentSemesterEvent()

    data class SemesterDropdownVisibilityChanged(val expanded: Boolean) : AssignStudentSemesterEvent()
    data class BranchDropdownVisibilityChanged(val expanded: Boolean) : AssignStudentSemesterEvent()
    data class AcademicYearDropdownVisibilityChanged(val expanded: Boolean) : AssignStudentSemesterEvent()
    data object FetchMatchingSemesters : AssignStudentSemesterEvent()
    data class SemesterNumberSelected(val semesterNumber: Int): AssignStudentSemesterEvent()
    data object AssignStudentsClicked: AssignStudentSemesterEvent()
    data class RemoveStudentClicked(val studentId: Int): AssignStudentSemesterEvent()
}