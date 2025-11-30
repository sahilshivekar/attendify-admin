package com.presencify_admin.common.validation

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
