package com.attendify_admin.home.users.presentation.add_staff

import android.net.Uri
import java.io.File

data class AddStaffState(
    val firstName: String = "",
    val middleName: String = "",
    val lastName: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val gender: Gender? = null,
    val highestQualification: String = "",
    val role: StaffRole? = null,
    val staffImageFile: File? = null,
    val staffImageUri: Uri? = null,
    val staffImageFileName: String? = null,
    val isStaffFileUploading: Boolean = false,
    val dialogText: String? = null,
    val isSubmitting: Boolean = false,
    val isSubmitted: Boolean = false,
    val isGenderDropDownOpen: Boolean = false,
    val isRoleDropDownOpen: Boolean = false,
    val isFirstNameError: String? = null,
    val isLastNameError: String? = null,
    val isEmailError: String? = null,
    val isPhoneNumberError: String? = null,
    val isGenderError: String? = null,
    val isRoleError: String? = null
)

