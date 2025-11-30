package com.presencify_admin.home.feature_users.presentation.remove_from_dropout

import com.presencify_admin.common.utils.DateTimeUtil
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

data class RemoveFromDropoutState(
    val selectedStudentIds: PersistentList<Int> = persistentListOf(),
    val selectedStudents: PersistentList<StudentData> = persistentListOf(),
    val areStudentsLoading: Boolean = false,
    val selectedDropoutAcademicYear: String? = null,
    val isDropoutAcademicYearDropdownExpanded: Boolean = false,
    val academicYearOfDropoutOptions: ImmutableList<String> = DateTimeUtil.getPastTenYears()
        .toMutableList()
        .map { year ->
            "${year.toInt() - 1} - $year"
        }.toImmutableList(),
)


data class StudentData(
    val id: Int,
    val studentName: String,
    val studentImageUrl: String? = null,
    val isRemoved: Boolean = false,
    val isFailedToRemove: Boolean = false,
    val supportingText: String = "",
    val isRemoving: Boolean = false,
)