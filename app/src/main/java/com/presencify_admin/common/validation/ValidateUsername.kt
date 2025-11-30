package com.presencify_admin.common.validation

import javax.inject.Inject

class ValidateUsername @Inject constructor() {

    operator fun invoke(username: String): ValidationResult {
        if (username.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Username cannot be empty"
            )
        }
        if (username.contains(" ")) {
            return ValidationResult(
                successful = false,
                errorMessage = "Username cannot contain spaces"
            )
        }
        if (username != username.lowercase()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Username must be in lowercase"
            )
        }
        if (username.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = "Username must be at least 8 characters long"
            )
        }

        return ValidationResult(successful = true)
    }
}