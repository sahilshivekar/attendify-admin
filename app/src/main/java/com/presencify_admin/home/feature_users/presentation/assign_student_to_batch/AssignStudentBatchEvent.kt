package com.presencify_admin.home.feature_users.presentation.assign_student_to_batch

import com.presencify_admin.common.domain.model.Batch
import com.presencify_admin.common.domain.model.Branch

sealed class AssignStudentBatchEvent {
    data class BatchSelected(val batch: Batch) : AssignStudentBatchEvent()
    data class BranchSelected(val branch: Branch) : AssignStudentBatchEvent()
    data class AcademicYearSelected(val year: String) : AssignStudentBatchEvent()

    data class SemesterDropdownVisibilityChanged(val expanded: Boolean) :
        AssignStudentBatchEvent()

    data class BranchDropdownVisibilityChanged(val expanded: Boolean) : AssignStudentBatchEvent()
    data class AcademicYearDropdownVisibilityChanged(val expanded: Boolean) :
        AssignStudentBatchEvent()

    data object FetchMatchingBatches : AssignStudentBatchEvent()
    data class SemesterNumberSelected(val semesterNumber: Int) : AssignStudentBatchEvent()
    data object AssignStudentsClicked : AssignStudentBatchEvent()
    data class RemoveStudentClicked(val studentId: Int) : AssignStudentBatchEvent()
}