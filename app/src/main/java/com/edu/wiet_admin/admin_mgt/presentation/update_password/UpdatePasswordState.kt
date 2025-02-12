package com.edu.wiet_admin.admin_mgt.presentation.update_password

data class UpdatePasswordState(
    val isUpdating: Boolean = false,
    val isUpdated: Boolean = false,

    val password: String = "",
    val confirmPassword: String = "",

    val passwordError: String? = null,
    val confirmPasswordError: String? = null,
    val isPasswordVisible: Boolean = false,

    val alertMessage: String? = null
)