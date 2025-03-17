package com.edu.wiet_admin.users.presentation.add_student

import android.net.Uri
import com.edu.wiet_admin.common.data.remote.response_dto.Branch
import com.edu.wiet_admin.common.data.remote.response_dto.Scheme
import com.edu.wiet_admin.common.utils.PhoneNumberUtil
import java.io.File
import java.time.LocalDate
import java.util.Calendar

data class AddStudentState(
    val prn: String = "",
    val firstName: String = "",
    val middleName: String = "",
    val lastName: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val phoneNumberCountryCode: String = "",
    val gender: String = "",
    val dob: LocalDate? = null,
    val admissionYear: String = "",
    val admissionType: String = "",

    val academicStatus: String = "",
    val studentImageFile: File? = null,
    val studentImageUri: Uri? = null,
    val studentImageFileName: String? = null,
    val isStudentFileUploading: Boolean = false,
    val isStudentImageFileUploading: Boolean = false,
    val isGenderDropDownOpen: Boolean = false,
    val isCountryCodeDropDownOpen: Boolean = false,
    val countryCodeOptions: List<PhoneNumberUtil.Country> = PhoneNumberUtil.getCountries(),
    val isDatePickerVisible: Boolean = false,
    val dialogText: String? = null,
    val isSubmitting: Boolean = false,
    val isSubmitted: Boolean = false,
    val isAdmissionTypeDropDownOpen: Boolean = false,
    val isAdmissionYearDropDownOpen: Boolean = false,
    val isAcademicStatusDropDownOpen: Boolean = false,
    val admissionTypeOptions: List<String> = listOf("FE", "DSE"),
    val admissionYearOptions: List<String> = getPastTwentyYears(),
    val academicStatusOptions: List<String> = listOf("Active", "Drop out", "Graduated"),

    val isSchemeDropDownOpen: Boolean = false,
    val isBranchDropDownOpen: Boolean = false,
    val branchOptions: List<Branch?> = emptyList(),
    val schemeOptions: List<Scheme?> = emptyList(),
    val selectedBranch: Branch? = null,
    val selectedScheme: Scheme? = null,

    val isFirstNameError: String? = null,
    val isMiddleNameError: String? = null,
    val isLastNameError: String? = null,
    val isEmailError: String? = null,
    val isPhoneNumberError: String? = null,
    val isGenderError: String? = null,
    val isDobError: String? = null,
    val isAdmissionYearError: String? = null,
    val isAdmissionTypeError: String? = null,
    val isBranchError: String? = null,
    val isSchemeError: String? = null,
    val isAcademicStatusError: String? = null,
    val isPRNError: String? = null,
)

fun getPastTwentyYears(): List<String> {
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    val pastTwentyYears = mutableListOf<String>()
    repeat(20) {
        pastTwentyYears.add((currentYear - it).toString())
    }
    return pastTwentyYears
}
