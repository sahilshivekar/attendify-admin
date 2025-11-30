package com.presencify_admin.home.feature_users.presentation.modify_student_batch

import com.presencify_admin.common.domain.model.Batch
import com.presencify_admin.common.domain.model.Branch
import com.presencify_admin.common.utils.DateTimeUtil
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

data class ModifyStudentBatchState(

    val selectedCurrentBatch: Batch? = null,

    val selectedSemesterNumberForCurrentBatch: Int? = null,
    val selectedBranchForCurrentBatch: Branch? = null,
    val selectedAcademicYearForCurrentBatch: String? = null,
    val isSemesterDropdownExpandedForCurrentBatch: Boolean = false,
    val isBranchDropdownExpandedForCurrentBatch: Boolean = false,
    val isAcademicYearDropdownExpandedForCurrentBatch: Boolean = false,
    val foundBatchesForCurrentBatch: ImmutableList<Batch> = persistentListOf(),
    val areBatchesLoadingForCurrentBatch: Boolean = false,

    val selectedNewBatch: Batch? = null,
    val selectedSemesterNumberForNewBatch: Int? = null,
    val selectedBranchForNewBatch: Branch? = null,
    val selectedAcademicYearForNewBatch: String? = null,
    val isSemesterDropdownExpandedForNewBatch: Boolean = false,
    val isBranchDropdownExpandedForNewBatch: Boolean = false,
    val isAcademicYearDropdownExpandedForNewBatch: Boolean = false,
    val foundBatchesForNewBatch: ImmutableList<Batch> = persistentListOf(),
    val areBatchesLoadingForNewBatch: Boolean = false,

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
    val newBatchStartDate: String? = null,
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
    val studentBatchId: Int? = null,
    val supportingText: String = "",
    val currentBatchStartDate: String? = null
)