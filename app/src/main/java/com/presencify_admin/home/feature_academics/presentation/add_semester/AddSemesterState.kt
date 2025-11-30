package com.presencify_admin.home.feature_academics.presentation.add_semester

import com.presencify_admin.common.domain.model.Branch
import com.presencify_admin.common.domain.model.Scheme
import com.presencify_admin.common.utils.DateTimeUtil

data class AddSemesterState(
    val semesterId: Int? = null,
    val selectedBranch: Branch? = null,
    val branchOptions: List<Branch> = emptyList(),
    val isBranchDropdownOpen: Boolean = false,

    val selectedAcademicYear: String = "",
    val academicYearOptions: List<String> = DateTimeUtil.getPastTenYears()
        .map { year -> "${year.toInt() - 1} - $year" },
    val isAcademicYearDropdownOpen: Boolean = false,

    val selectedSemesterNumber: Int? = null,
    val semesterNumberOptions: List<Int> = listOf(1, 2, 3, 4, 5, 6, 7, 8),
    val isSemesterNumberDropdownOpen: Boolean = false,

    val selectedScheme: Scheme? = null,
    val schemeOptions: List<Scheme> = emptyList(),
    val isSchemeDropdownOpen: Boolean = false,

    val startDate: String? = null,
    val endDate: String? = null,
    val isStartDatePickerVisible: Boolean = false,
    val isEndDatePickerVisible: Boolean = false,

    val isLoading: Boolean = false,
    val isSubmitted: Boolean = false,

    val branchError: String? = null,
    val academicYearError: String? = null,
    val semesterNumberError: String? = null,
    val schemeError: String? = null,
    val startDateError: String? = null,
    val endDateError: String? = null,
)