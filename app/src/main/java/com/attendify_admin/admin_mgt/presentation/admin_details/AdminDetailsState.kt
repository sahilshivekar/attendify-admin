package com.attendify_admin.admin_mgt.presentation.admin_details

data class AdminDetailsState (
    val editableEmail: String = "",
    val editableUsername: String = "",

    val orgEmail: String = "",
    val orgUsername: String = "",

    val emailError: String? = null,
    val usernameError: String? = null,

    val isEditingDetails: Boolean = false,
    val isUpdatingDetails: Boolean = false,

    val isUsernameEmailEnabled: Boolean = false,

    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val alertMessage: String? = null,

    val isInitialDataLoading: Boolean = true,

    val isVerified: Boolean? = null,
    val isSendingVerificationCode: Boolean = false,
    val isVerificationCodeSent: Boolean = false,

    val isLoggingOut: Boolean = false,
    val isLoggedOut: Boolean = false,

    val isRemovingAccount: Boolean = false, // no need to keep track if its done or not as it will automatically navigate to login screen

    val showRemoveAccountConfirmationDialog: Boolean = false,
)