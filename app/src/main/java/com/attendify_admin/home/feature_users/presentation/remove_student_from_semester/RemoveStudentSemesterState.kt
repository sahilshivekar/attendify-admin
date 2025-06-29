package com.attendify_admin.home.feature_users.presentation.remove_student_from_semester

import com.attendify_admin.common.domain.model.Branch
import com.attendify_admin.common.domain.model.Semester
import com.attendify_admin.common.utils.DateTimeUtil
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

data class RemoveStudentSemesterState(
    val selectedSemesterNumber: Int? = null,
    val selectedSemester: Semester? = null,
    val selectedBranch: Branch? = null,
    val selectedAcademicYear: String? = null,
    val isSemesterDropdownExpanded: Boolean = false,
    val isBranchDropdownExpanded: Boolean = false,
    val isAcademicYearDropdownExpanded: Boolean = false,

    val students: PersistentList<StudentCard> = persistentListOf(),
    val branchOptions: ImmutableList<Branch> = persistentListOf(),
    val academicYearOfSemesterOptions: ImmutableList<String> = DateTimeUtil.getPastTenYears()
        .toMutableList()
        .map { year ->
            "${year.toInt() - 1} - $year"
        }.toImmutableList(),
    val semesterNumberOptions: PersistentList<Int> = persistentListOf(1, 2, 3, 4, 5, 6, 7, 8),
    val foundSemesters: ImmutableList<Semester> = persistentListOf(),
    val areSemestersLoading: Boolean = false,
    val areBranchesLoading: Boolean = true,
    val areStudentsLoading: Boolean = false,
)

data class StudentCard(
    val id: Int,
    val studentName: String,
    val studentImageUrl: String? = null,
    val isUnassigned: Boolean = false,
    val isFailedToUnassign: Boolean = false,
    val isUnassigning: Boolean = false,
    val studentSemesterId: Int? = null,
    val supportingText: String = ""
)