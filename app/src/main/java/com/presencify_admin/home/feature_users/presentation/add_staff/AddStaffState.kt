package com.presencify_admin.home.feature_users.presentation.add_staff

import android.net.Uri
import com.presencify_admin.common.utils.PhoneUtil
import com.presencify_admin.common.utils.PhoneUtil.Country
import com.presencify_admin.common.utils.PhoneUtil.getIndiaAsDefaultCountry
import kotlinx.collections.immutable.ImmutableList

data class AddStaffState(
    val staffId: Int? = null,
    val isLoadingInitialStaffDetails: Boolean = false,
    val firstName: String = "",
    val middleName: String = "",
    val lastName: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val gender: String? = null,
    val highestQualification: String = "",
    val role: String? = null,
    val staffImageUri: Uri? = null,
    val staffImageFileName: String? = null,
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
    val isRoleError: String? = null,
    val isImageVisible: Boolean = false,
    val isCountryCodeDropDownOpen: Boolean = false,
    val isCountryError: String? = null,
    val country: Country = getIndiaAsDefaultCountry(),
    val countryCodeOptions: ImmutableList<Country> = PhoneUtil.getCountries(),
)

