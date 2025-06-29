package com.attendify_admin.home.feature_users.presentation.add_to_dropout

import com.attendify_admin.common.utils.DateTimeUtil
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

data class AddToDropoutState(
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
    val isAdded: Boolean = false,
    val isFailedToAdd: Boolean = false,
    val supportingText: String = "",
    val isAdding: Boolean = false,
)