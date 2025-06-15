package com.attendify_admin.common.validation

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
