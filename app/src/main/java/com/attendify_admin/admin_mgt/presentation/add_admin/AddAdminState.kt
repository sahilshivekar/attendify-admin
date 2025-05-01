package com.attendify_admin.admin_mgt.presentation.add_admin

data class AddAdminState(
    val email: String = "",
    val username: String = "",
    val password: String = "",
    val confirmPassword: String = "",

    val emailError: String? = null,
    val usernameError: String? = null,
    val passwordError: String? = null,
    val confirmPasswordError: String? = null,

    val isAdding: Boolean = false,
    val isAdded: Boolean = false,
    val alertMessage: String? = null,

    val isPasswordVisible: Boolean = false
)