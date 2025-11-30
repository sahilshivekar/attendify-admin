package com.presencify_admin.home.feature_users.presentation.search_student

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import com.presencify_admin.common.domain.model.Batch
import com.presencify_admin.common.domain.model.Branch
import com.presencify_admin.common.domain.model.Division
import com.presencify_admin.common.domain.model.Scheme
import com.presencify_admin.common.utils.DateTimeUtil
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.PersistentSet
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentSetOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Stable
data class SearchStudentState(

    val isFetchingStudents: Boolean = true,
    val students: Flow<PagingData<StudentCard>> = emptyFlow(),
    val selectedStudentIds: PersistentSet<Int> = persistentSetOf(),
    val isSelectable: Boolean = false,

    val isSearchBarActive: Boolean = false,
    val searchQuery: String = "",

    val isAcademicEndYearOfSemesterDropDownVisible: Boolean = false,
    val isAcademicStartYearOfSemesterDropDownVisible: Boolean = false,

    val isAdmissionYearDropDownVisible: Boolean = false,

    val selectedDropoutYear: String? = null,
    val dropoutYearOptions: ImmutableList<String> = DateTimeUtil.getPastTenYears().toMutableList()
        .map { year ->
            "${year.toInt() - 1} - $year"
        }.toImmutableList(),

    val branchOptions: PersistentList<Branch>? = null,
    val semesterOptions: PersistentList<Int> = persistentListOf(1, 2, 3, 4, 5, 6, 7, 8),
    val academicYearOfSemesterOptions: ImmutableList<String> = DateTimeUtil.getPastTenYears()
        .toMutableList()
        .map { year ->
            "${year.toInt() - 1} - $year"
        }.toImmutableList(),

    val admissionTypeOptions: PersistentList<String>? = persistentListOf(
        "First Year",
        "Direct Second Year"
    ),
    val admissionYearOptions: ImmutableList<String>? = DateTimeUtil.getPastTenYears(),
    val selectedAcademicYearOfSemester: String? = null,
    val selectedAdmissionTypes: PersistentList<String> = persistentListOf(),
    val selectedAdmissionYear: String? = null,
    val isBottomSheetVisible: Boolean = false,
    val areBranchesLoading: Boolean = true,
    val isSearchExpanded: Boolean = true,
    val selectedBranches: PersistentList<Branch> = persistentListOf(),
    val selectedSemesters: PersistentList<Int> = persistentListOf(),

    val selectedScheme: Scheme? = null,
    val selectedDivision: Division? = null,
    val selectedBatch: Batch? = null,

    val schemeOptions: ImmutableList<Scheme> = persistentListOf(),
    val divisionOptions: ImmutableList<Division> = persistentListOf(),
    val batchOptions: ImmutableList<Batch> = persistentListOf(),

    val areDivisionsLoading: Boolean = false,
    val areBatchesLoading: Boolean = false,
    val areSchemesLoading: Boolean = false,
)

@Immutable
data class StudentCard(
    val id: Int,
    val studentName: String,
    val studentBranch: String,
    val studentYear: String? = null,
    val studentImageUrl: String? = null,
)


