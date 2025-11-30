package com.presencify_admin.home.feature_users.presentation.assign_student_to_semester

import com.presencify_admin.common.domain.model.Branch
import com.presencify_admin.common.domain.model.Semester
import com.presencify_admin.common.utils.DateTimeUtil
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

data class AssignStudentSemesterState(
    val selectedSemesterNumber: Int? = null,
    val selectedSemester: Semester? = null,
    val selectedBranch: Branch? = null,
    val selectedAcademicYear: String? = null,
    val selectedStudentIds: PersistentList<Int> = persistentListOf(),
    val isSemesterDropdownExpanded: Boolean = false,
    val isBranchDropdownExpanded: Boolean = false,
    val isAcademicYearDropdownExpanded: Boolean = false,
    val selectedStudents: PersistentList<StudentCard> = persistentListOf(),
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
    val studentsToLoad: Int = 0,
    val studentsAssignOrFailedToAssignCount: Int = 0,
    val isAssigningStudents: Boolean = false,
    val isAssigningDone: Boolean = false
)

data class StudentCard(
    val id: Int,
    val studentName: String,
//    val studentBranch: String,
//    val studentYear: String? = null,
    val studentImageUrl: String? = null,
    val isAdded: Boolean = false,
    val isFailedToAdd: Boolean = false,
    val supportingText: String = "",
    val isAdding: Boolean = false,
)