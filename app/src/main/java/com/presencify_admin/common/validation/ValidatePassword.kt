package com.presencify_admin.common.validation

import javax.inject.Inject

class ValidatePassword @Inject constructor() {

    operator fun invoke(password: String): ValidationResult {
        if (password.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password cannot be empty"
            )
        }
        if (password.contains(" ")) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password cannot contain spaces"
            )
        }
        if (password.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password must be at least 8 characters long"
            )
        }
        if (!password.any { it.isUpperCase() }) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password must contain at least one uppercase letter"
            )
        }
        if (!password.any { it.isDigit() }) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password must contain at least one number"
            )
        }
        if (!password.any { !it.isLetterOrDigit() }) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password must contain at least one special character"
            )
        }

        return ValidationResult(successful = true)
    }
}