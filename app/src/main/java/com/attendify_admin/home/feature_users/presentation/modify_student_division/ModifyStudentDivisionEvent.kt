package com.attendify_admin.home.feature_users.presentation.modify_student_division

import com.attendify_admin.common.domain.model.Branch
import com.attendify_admin.common.domain.model.Division

sealed class ModifyStudentDivisionEvent {
    data class CurrentDivisionSelected(val division: Division) : ModifyStudentDivisionEvent()
    data class BranchSelectedForCurrentDivision(val branch: Branch) : ModifyStudentDivisionEvent()
    data class AcademicYearSelectedForCurrentDivision(val year: String) : ModifyStudentDivisionEvent()
    data class SemesterDropdownVisibilityChangedForCurrentDivision(val expanded: Boolean) : ModifyStudentDivisionEvent()
    data class BranchDropdownVisibilityChangedForCurrentDivision(val expanded: Boolean) : ModifyStudentDivisionEvent()
    data class AcademicYearDropdownVisibilityChangedForCurrentDivision(val expanded: Boolean) : ModifyStudentDivisionEvent()
    data object FetchMatchingDivisionsForCurrentDivision : ModifyStudentDivisionEvent()
    data class SemesterNumberSelectedForCurrentDivision(val semesterNumber: Int): ModifyStudentDivisionEvent()

    data class NewDivisionSelected(val division: Division) : ModifyStudentDivisionEvent()
    data class BranchSelectedForNewDivision(val branch: Branch) : ModifyStudentDivisionEvent()
    data class AcademicYearSelectedForNewDivision(val year: String) : ModifyStudentDivisionEvent()
    data class SemesterDropdownVisibilityChangedForNewDivision(val expanded: Boolean) : ModifyStudentDivisionEvent()
    data class BranchDropdownVisibilityChangedForNewDivision(val expanded: Boolean) : ModifyStudentDivisionEvent()
    data class AcademicYearDropdownVisibilityChangedForNewDivision(val expanded: Boolean) : ModifyStudentDivisionEvent()
    data object FetchMatchingDivisionsForNewDivision : ModifyStudentDivisionEvent()
    data class SemesterNumberSelectedForNewDivision(val semesterNumber: Int): ModifyStudentDivisionEvent()

    data object BackClicked: ModifyStudentDivisionEvent()
    data object NextClicked: ModifyStudentDivisionEvent()
    data class DateChanged(val date: String): ModifyStudentDivisionEvent()
    data class ChangeStudentDivisionClicked(val studentDivisionId: Int): ModifyStudentDivisionEvent()
    data object DatePickerVisibilityChanged: ModifyStudentDivisionEvent()
}