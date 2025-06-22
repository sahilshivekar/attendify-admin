package com.attendify_admin.feature_admin_mgt.presentation.update_password

data class UpdatePasswordState(
    val isUpdating: Boolean = false,
    val isUpdated: Boolean = false,

    val password: String = "",
    val confirmPassword: String = "",

    val passwordError: String? = null,

    val isPasswordVisible: Boolean = false,
)