package com.attendify_admin.users.presentation.add_staff

import android.net.Uri
import java.io.File

sealed class AddStaffEvent {
    data class FirstNameChanged(val newFirstName: String) : AddStaffEvent()
    data class MiddleNameChanged(val newMiddleName: String) : AddStaffEvent()
    data class LastNameChanged(val newLastName: String) : AddStaffEvent()
    data class EmailChanged(val newEmail: String) : AddStaffEvent()
    data class PhoneNumberChanged(val newPhoneNumber: String) : AddStaffEvent()
    data class GenderChanged(val newGender: Gender) : AddStaffEvent()
    data class HighestQualificationChanged(val newQualification: String) : AddStaffEvent()
    data class RoleChanged(val newRole: StaffRole) : AddStaffEvent()
    data object SubmitClicked : AddStaffEvent()
    data object DismissAlertDialog : AddStaffEvent()
    data object ResetClicked : AddStaffEvent()

    data class GenderDropDownVisibilityChanged(val newVisibility: Boolean) : AddStaffEvent()
    data class RoleDropDownVisibilityChanged(val newVisibility: Boolean) : AddStaffEvent()

    data class StaffImageUriUpdated(val updatedUri: Uri?) : AddStaffEvent()
    data class StaffImageChanged(val newStaffImage: File?) : AddStaffEvent()
}

enum class Gender(val displayName: String) {
    MALE("Male"),
    FEMALE("Female"),
    OTHER("Other");

    override fun toString(): String = displayName
}

enum class StaffRole(val displayName: String) {
    TEACHER("Teacher"),
    HEAD_OF_DEPARTMENT("Head of Department"),
    PRINCIPAL("Principal");

    override fun toString(): String = displayName
}