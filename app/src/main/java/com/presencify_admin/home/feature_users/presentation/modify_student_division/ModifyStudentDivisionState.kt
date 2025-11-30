package com.presencify_admin.home.feature_users.presentation.modify_student_division

import com.presencify_admin.common.domain.model.Branch
import com.presencify_admin.common.domain.model.Division
import com.presencify_admin.common.utils.DateTimeUtil
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

data class ModifyStudentDivisionState(
    val selectedCurrentDivision: Division? = null,

    val selectedSemesterNumberForCurrentDivision: Int? = null,
    val selectedBranchForCurrentDivision: Branch? = null,
    val selectedAcademicYearForCurrentDivision: String? = null,
    val isSemesterDropdownExpandedForCurrentDivision: Boolean = false,
    val isBranchDropdownExpandedForCurrentDivision: Boolean = false,
    val isAcademicYearDropdownExpandedForCurrentDivision: Boolean = false,
    val foundDivisionsForCurrentDivision: ImmutableList<Division> = persistentListOf(),
    val areDivisionsLoadingForCurrentDivision: Boolean = false,

    val selectedNewDivision: Division? = null,
    val selectedSemesterNumberForNewDivision: Int? = null,
    val selectedBranchForNewDivision: Branch? = null,
    val selectedAcademicYearForNewDivision: String? = null,
    val isSemesterDropdownExpandedForNewDivision: Boolean = false,
    val isBranchDropdownExpandedForNewDivision: Boolean = false,
    val isAcademicYearDropdownExpandedForNewDivision: Boolean = false,
    val foundDivisionsForNewDivision: ImmutableList<Division> = persistentListOf(),
    val areDivisionsLoadingForNewDivision: Boolean = false,

    val students: PersistentList<StudentCardData> = persistentListOf(),
    val branchOptions: ImmutableList<Branch> = persistentListOf(),
    val semesterNumberOptions: PersistentList<Int> = persistentListOf(1, 2, 3, 4, 5, 6, 7, 8),
    val academicYearOfSemesterOptions: ImmutableList<String> = DateTimeUtil.getPastTenYears()
        .toMutableList()
        .map { year ->
            "${year.toInt() - 1} - $year"
        }.toImmutableList(),
    val areBranchesLoading: Boolean = true,
    val areStudentsLoading: Boolean = false,
    val newDivisionStartDate: String? = null,
    val isDatePickerVisible: Boolean = false,
    val currStep: Int = 1,
)

data class StudentCardData(
    val id: Int,
    val studentName: String,
    val studentImageUrl: String? = null,
    val isChanged: Boolean = false,
    val isFailedToChange: Boolean = false,
    val isChanging: Boolean = false,
    val studentDivisionId: Int? = null,
    val supportingText: String = "",
    val currentDivisionStartDate: String? = null
)