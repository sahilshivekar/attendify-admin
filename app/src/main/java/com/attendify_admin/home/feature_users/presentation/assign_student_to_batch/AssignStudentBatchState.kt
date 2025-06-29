package com.attendify_admin.home.feature_users.presentation.assign_student_to_batch

import com.attendify_admin.common.domain.model.Batch
import com.attendify_admin.common.domain.model.Branch
import com.attendify_admin.common.utils.DateTimeUtil
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

data class AssignStudentBatchState(
    val selectedSemesterNumber: Int? = null,
    val selectedBatch: Batch? = null,
    val selectedBranch: Branch? = null,
    val selectedAcademicYear: String? = null,
    val selectedStudentIds: PersistentList<Int> = persistentListOf(),
    val isSemesterDropdownExpanded: Boolean = false,
    val isBranchDropdownExpanded: Boolean = false,
    val isAcademicYearDropdownExpanded: Boolean = false,
    val selectedStudents: PersistentList<StudentCard> = persistentListOf(),
    val branchOptions: ImmutableList<Branch> = persistentListOf(),
    val academicYearOfBatchOptions: ImmutableList<String> = DateTimeUtil.getPastTenYears()
        .toMutableList()
        .map { year ->
            "${year.toInt() - 1} - $year"
        }.toImmutableList(),
    val semesterNumberOptions: PersistentList<Int> = persistentListOf(1, 2, 3, 4, 5, 6, 7, 8),
    val foundBatches: ImmutableList<Batch> = persistentListOf(),
    val areBatchesLoading: Boolean = false,
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
    val studentImageUrl: String? = null,
    val isAdded: Boolean = false,
    val isFailedToAdd: Boolean = false,
    val supportingText: String = "",
    val isAdding: Boolean = false,
)