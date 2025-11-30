package com.presencify_admin.home.feature_users.presentation.modify_student_batch

import com.presencify_admin.common.domain.model.Batch
import com.presencify_admin.common.domain.model.Branch

sealed class ModifyStudentBatchEvent {
    data class CurrentBatchSelected(val batch: Batch) : ModifyStudentBatchEvent()
    data class BranchSelectedForCurrentBatch(val branch: Branch) : ModifyStudentBatchEvent()
    data class AcademicYearSelectedForCurrentBatch(val year: String) : ModifyStudentBatchEvent()
    data class SemesterDropdownVisibilityChangedForCurrentBatch(val expanded: Boolean) : ModifyStudentBatchEvent()
    data class BranchDropdownVisibilityChangedForCurrentBatch(val expanded: Boolean) : ModifyStudentBatchEvent()
    data class AcademicYearDropdownVisibilityChangedForCurrentBatch(val expanded: Boolean) : ModifyStudentBatchEvent()
    data object FetchMatchingBatchesForCurrentBatch : ModifyStudentBatchEvent()
    data class SemesterNumberSelectedForCurrentBatch(val semesterNumber: Int): ModifyStudentBatchEvent()

    data class NewBatchSelected(val batch: Batch) : ModifyStudentBatchEvent()
    data class BranchSelectedForNewBatch(val branch: Branch) : ModifyStudentBatchEvent()
    data class AcademicYearSelectedForNewBatch(val year: String) : ModifyStudentBatchEvent()
    data class SemesterDropdownVisibilityChangedForNewBatch(val expanded: Boolean) : ModifyStudentBatchEvent()
    data class BranchDropdownVisibilityChangedForNewBatch(val expanded: Boolean) : ModifyStudentBatchEvent()
    data class AcademicYearDropdownVisibilityChangedForNewBatch(val expanded: Boolean) : ModifyStudentBatchEvent()
    data object FetchMatchingBatchesForNewBatch : ModifyStudentBatchEvent()
    data class SemesterNumberSelectedForNewBatch(val semesterNumber: Int): ModifyStudentBatchEvent()

    data object BackClicked: ModifyStudentBatchEvent()
    data object NextClicked: ModifyStudentBatchEvent()
    data class DateChanged(val date: String): ModifyStudentBatchEvent()
    data class ChangeStudentBatchClicked(val studentBatchId: Int): ModifyStudentBatchEvent()
    data object DatePickerVisibilityChanged: ModifyStudentBatchEvent()
}