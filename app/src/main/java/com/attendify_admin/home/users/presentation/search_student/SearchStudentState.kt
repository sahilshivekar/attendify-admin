package com.attendify_admin.home.users.presentation.search_student

import androidx.paging.PagingData
import com.attendify_admin.common.data.dto.response.Branch
import com.attendify_admin.common.data.dto.response.Scheme
import com.attendify_admin.common.data.dto.response.Student
import com.attendify_admin.common.utils.TimeUtil
import kotlinx.coroutines.flow.Flow

data class SearchStudentState(

    val isFetchingStudents: Boolean = true,
    val dialogText: String? = null,
    val students: Flow<PagingData<Student>>? = null,

    val isSearchBarActive: Boolean = false,
    val searchQuery: String = "",

    val isAcademicEndYearOfSemesterDropDownVisible: Boolean = false,
    val isAcademicStartYearOfSemesterDropDownVisible: Boolean = false,

    val isAdmissionYearDropDownVisible: Boolean = false,


    val branchOptions: List<Branch?> = emptyList(),
    val semesterOptions: List<Int> = listOf(1, 2, 3, 4, 5, 6, 7, 8),
    val academicStartYearOfSemesterOptions: List<String> = TimeUtil.getPastTwentyYears(),
    val academicEndYearOfSemesterOptions: List<String> = TimeUtil.getPastTwentyYears(),
    val academicStatusOptions: List<String?> = listOf("Active", "Drop out", "Graduated"),
    val admissionTypeOptions: List<String?> = listOf("First Year", "Direct Second Year"),
    val schemeOptions: List<Scheme?> = emptyList(),
    val divisionOptions: List<String> = emptyList(), // will show distinct divisions
    val batchOptions: List<String> = emptyList(), // will show distinct batches
    val admissionYearOptions: List<String?> = TimeUtil.getPastTwentyYears(),

    val selectedBranches: List<Branch> = emptyList(),
    val selectedSemesters: List<Int> = emptyList(),
    val selectedAcademicStartYearOfSemester: String? = null,
    val selectedAcademicEndYearOfSemester: String? = null,
    val selectedAcademicStatuses: List<String> = emptyList(),
    val selectedAdmissionTypes: List<String> = emptyList(),
    val selectedSchemes: List<Scheme> = emptyList(),
    val selectedDivisions: List<String> = emptyList(),
    val selectedBatches: List<String> = emptyList(),
    val selectedAdmissionYear: String? = null,

    val isBottomSheetVisible: Boolean = false,
    val areBranchesLoading: Boolean = true,
    val isSearchExpanded: Boolean = true
)


