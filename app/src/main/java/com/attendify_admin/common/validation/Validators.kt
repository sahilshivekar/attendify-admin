package com.attendify_admin.common.validation

object Validators {

    fun validateEmail(email: String?): String? {
        return when {
            email.isNullOrEmpty() -> "Email cannot be empty"
            !email.matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) -> "Enter a valid email address"
            else -> null
        }
    }

    fun validateUsername(username: String?): String? {
        return when {
            username.isNullOrEmpty() -> "Username cannot be empty"
            username.contains(" ") -> "Username cannot contain spaces"
            username != username.lowercase() -> "Username must be in lowercase"
            username.length < 8 -> "Username must be at least 8 characters long"
            else -> null
        }
    }

    fun validatePassword(password: String?): String? {
        return when {
            password.isNullOrEmpty() -> "Password cannot be empty"
            password.contains(" ") -> "Password cannot contain spaces"
            password.length < 8 -> "Password must be at least 8 characters long"
            !password.any { it.isUpperCase() } -> "Password must contain at least one uppercase letter"
            !password.any { it.isDigit() } -> "Password must contain at least one number"
            !password.any { !it.isLetterOrDigit() } -> "Password must contain at least one special character"
            !password.any { "!@#$%^&*()_+-=[]{}|;:'\",.<>?/".contains(it) } ->
                "Password must contain at least one special character."
            else -> null
        }
    }
}