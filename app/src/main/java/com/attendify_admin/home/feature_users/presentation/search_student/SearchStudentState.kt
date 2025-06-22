package com.attendify_admin.home.feature_users.presentation.search_student

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import com.attendify_admin.common.domain.model.Branch
import com.attendify_admin.common.domain.model.Scheme
import com.attendify_admin.common.utils.DateTimeUtil
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Stable
data class SearchStudentState(

    val isFetchingStudents: Boolean = true,
    val students: Flow<PagingData<StudentCard>> = emptyFlow(),

    val isSearchBarActive: Boolean = false,
    val searchQuery: String = "",

    val isAcademicEndYearOfSemesterDropDownVisible: Boolean = false,
    val isAcademicStartYearOfSemesterDropDownVisible: Boolean = false,

    val isAdmissionYearDropDownVisible: Boolean = false,


    val branchOptions: PersistentList<Branch>? = null,
    val semesterOptions: PersistentList<Int> = persistentListOf(1, 2, 3, 4, 5, 6, 7, 8),
    val academicStartYearOfSemesterOptions: ImmutableList<String> = DateTimeUtil.getPastTenYears(),
    val academicEndYearOfSemesterOptions: ImmutableList<String> = DateTimeUtil.getPastTenYears(),
    val admissionTypeOptions: PersistentList<String>? = persistentListOf("First Year", "Direct Second Year"),
    val schemeOptions: PersistentList<Scheme>? = null,
    val divisionOptions: PersistentList<String>? = null, // will show distinct divisions
    val batchOptions: PersistentList<String>? = null, // will show distinct batches
    val admissionYearOptions: ImmutableList<String>? = DateTimeUtil.getPastTenYears(),
    val selectedBranches: PersistentList<Branch> = persistentListOf(),
    val selectedSemesters: PersistentList<Int> = persistentListOf(),
    val selectedAcademicStartYearOfSemester: String? = null,
    val selectedAcademicEndYearOfSemester: String? = null,
    val selectedAdmissionTypes: PersistentList<String> = persistentListOf(),
    val selectedAdmissionYear: String? = null,
    val isBottomSheetVisible: Boolean = false,
    val areBranchesLoading: Boolean = true,
    val isSearchExpanded: Boolean = true
)

@Immutable
data class StudentCard(
    val id: Int,
    val studentName: String,
    val studentBranch: String,
    val studentYear: String? = null,
    val studentImageUrl: String? = null,
)


