package com.attendify_admin.home.feature_users.presentation.add_student

import android.net.Uri
import com.attendify_admin.common.domain.model.Branch
import com.attendify_admin.common.domain.model.Scheme
import com.attendify_admin.common.utils.DateTimeUtil
import com.attendify_admin.common.utils.PhoneUtil
import com.attendify_admin.common.utils.PhoneUtil.Country
import com.attendify_admin.common.utils.PhoneUtil.getIndiaAsDefaultCountry
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class AddStudentState(
    val prn: String = "",
    val firstName: String = "",
    val middleName: String = "",
    val lastName: String = "",
    val email: String = "",
    val parentEmail: String = "",
    val phoneNumber: String = "",
    val country: Country = getIndiaAsDefaultCountry(),
    val gender: String = "",
    val dob: String? = null,
    val admissionYear: String = "",
    val admissionType: AdmissionType = AdmissionType.FE,
    val studentId: Int? = null,
    val isLoadingInitialStudentDetails: Boolean = true,
    val studentImageUri: Uri? = null,
    val studentImageFileName: String? = null,
    val isGenderDropDownOpen: Boolean = false,
    val isCountryCodeDropDownOpen: Boolean = false,
    val countryCodeOptions: ImmutableList<Country> = PhoneUtil.getCountries(),
    val isDatePickerVisible: Boolean = false,
    val isSubmitting: Boolean = false,
    val isSubmitted: Boolean = false,
    val isAdmissionTypeDropDownOpen: Boolean = false,
    val isAdmissionYearDropDownOpen: Boolean = false,
    val admissionTypeOptions: ImmutableList<AdmissionType> = persistentListOf(
        AdmissionType.FE,
        AdmissionType.DSE
    ),
    val admissionYearOptions: ImmutableList<String> = DateTimeUtil.getPastTenYears(),
    val genderOptions: ImmutableList<String> = persistentListOf(
        Genders.MALE.displayName,
        Genders.FEMALE.displayName,
        Genders.OTHER.displayName
    ),
    val isSchemeDropDownOpen: Boolean = false,
    val isBranchDropDownOpen: Boolean = false,
    val branchOptions: ImmutableList<Branch>? = null,
    val schemeOptions: ImmutableList<Scheme>? = null,
    val selectedBranch: Branch? = null,
    val selectedScheme: Scheme? = null,
    val isImageVisible: Boolean = false,
    val isFirstNameError: String? = null,
    val isMiddleNameError: String? = null,
    val isLastNameError: String? = null,
    val isEmailError: String? = null,
    val isParentEmailError: String? = null,
    val isPhoneNumberError: String? = null,
    val isGenderError: String? = null,
    val isDobError: String? = null,
    val isAdmissionYearError: String? = null,
    val isAdmissionTypeError: String? = null,
    val isBranchError: String? = null,
    val isSchemeError: String? = null,
    val isPRNError: String? = null,
    val isCountryError: String? = null,
    val currStep: AddStudentSteps = AddStudentSteps.PERSONAL_DETAILS,
)



enum class AddStudentSteps {
    PERSONAL_DETAILS,
    CONTACT_DETAILS,
    ACADEMIC_DETAILS
}

enum class AdmissionType(val displayName: String) {
    FE("FE"),
    DSE("DSE");

    companion object {
        fun getTypeFromString(name: String): AdmissionType {
            return if (name == "FE")
                FE
            else
                DSE
        }
    }
}

enum class Genders(val displayName: String) {
    MALE("Male"),
    FEMALE("Female"),
    OTHER("Other");
}
