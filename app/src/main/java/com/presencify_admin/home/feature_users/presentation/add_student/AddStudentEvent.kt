package com.presencify_admin.home.feature_users.presentation.add_student

import android.net.Uri
import com.presencify_admin.common.domain.model.Branch
import com.presencify_admin.common.domain.model.Scheme
import com.presencify_admin.common.utils.PhoneUtil.Country

sealed interface AddStudentEvent {
    data class PrnChanged(val newPrn: String) : AddStudentEvent
    data class FirstNameChanged(val newFirstName: String) : AddStudentEvent
    data class MiddleNameChanged(val newMiddleName: String) : AddStudentEvent
    data class LastNameChanged(val newLastName: String) : AddStudentEvent
    data class EmailChanged(val newEmail: String) : AddStudentEvent
    data class ParentEmailChanged(val newEmail: String) : AddStudentEvent
    data class PhoneNumberChanged(val newPhoneNumber: String) : AddStudentEvent
    data class PhoneNumberCountryCodeChanged(val country: Country) : AddStudentEvent
    data class GenderChanged(val newGender: String) : AddStudentEvent
    data class DobChanged(val newDob: String) : AddStudentEvent
    data class AdmissionYearChanged(val newAdmissionYear: String) : AddStudentEvent
    data class AdmissionTypeChanged(val newAdmissionType: String) : AddStudentEvent
    data class BranchChanged(val newBranch: Branch?) : AddStudentEvent
    data class SchemeChanged(val newScheme: Scheme?) : AddStudentEvent
    data object SubmitClicked : AddStudentEvent
    data object BackClicked : AddStudentEvent
    data object DatePickerVisibilityChanged : AddStudentEvent
    data class GenderDropDownVisibilityChanged(val newVisibility: Boolean) : AddStudentEvent
    data class CountryCodeDropDownVisibilityChanged(val newVisibility: Boolean) : AddStudentEvent
    data class AdmissionTypeDropDownVisibilityChanged(val newVisibility: Boolean) : AddStudentEvent
    data class BranchDropDownVisibilityChanged(val newVisibility: Boolean) : AddStudentEvent
    data class SchemeDropDownVisibilityChanged(val newVisibility: Boolean) : AddStudentEvent
    data class AdmissionYearDropDownVisibilityChanged(val newVisibility: Boolean): AddStudentEvent
    data class StudentImageUriUpdated(val updatedUri: Uri?): AddStudentEvent
    data object ShowImageClicked: AddStudentEvent
    data object CloseImageClicked: AddStudentEvent
    data object ValidateFields: AddStudentEvent
}
