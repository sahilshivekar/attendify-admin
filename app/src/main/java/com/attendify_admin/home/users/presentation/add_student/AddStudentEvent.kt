package com.attendify_admin.home.users.presentation.add_student

import android.net.Uri
import com.attendify_admin.common.data.remote.response_dto.Branch
import com.attendify_admin.common.data.remote.response_dto.Scheme
import java.io.File
import java.time.LocalDate

sealed class AddStudentEvent {
    data class PrnChanged(val newPrn: String) : AddStudentEvent()
    data class FirstNameChanged(val newFirstName: String) : AddStudentEvent()
    data class MiddleNameChanged(val newMiddleName: String) : AddStudentEvent()
    data class LastNameChanged(val newLastName: String) : AddStudentEvent()
    data class EmailChanged(val newEmail: String) : AddStudentEvent()
    data class PhoneNumberChanged(val newPhoneNumber: String) : AddStudentEvent()
    data class PhoneNumberCountryCodeChanged(val newPhoneNumberCountryCode: String) : AddStudentEvent()
    data class GenderChanged(val newGender: String) : AddStudentEvent()
    data class DobChanged(val newDob: LocalDate) : AddStudentEvent()
    data class AdmissionYearChanged(val newAdmissionYear: String) : AddStudentEvent()
    data class AdmissionTypeChanged(val newAdmissionType: String) : AddStudentEvent()
    data class BranchChanged(val newBranch: Branch?) : AddStudentEvent()
    data class SchemeChanged(val newScheme: Scheme?) : AddStudentEvent()
    data object SubmitClicked : AddStudentEvent()
    data object DismissAlertDialog : AddStudentEvent()
    data object ResetClicked : AddStudentEvent()
    data object DatePickerVisibilityChanged : AddStudentEvent()
    data class GenderDropDownVisibilityChanged(val newVisibility: Boolean) : AddStudentEvent()
    data class CountryCodeDropDownVisibilityChanged(val newVisibility: Boolean) : AddStudentEvent()
    data class AdmissionTypeDropDownVisibilityChanged(val newVisibility: Boolean) : AddStudentEvent()
    data class BranchDropDownVisibilityChanged(val newVisibility: Boolean) : AddStudentEvent()
    data class SchemeDropDownVisibilityChanged(val newVisibility: Boolean) : AddStudentEvent()
    data class AdmissionYearDropDownVisibilityChanged(val newVisibility: Boolean): AddStudentEvent()
    data class StudentImageUriUpdated(val updatedUri: Uri?): AddStudentEvent()
    data class StudentImageChanged(val newStudentImage: File?) : AddStudentEvent()
}
