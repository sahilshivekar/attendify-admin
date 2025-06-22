package com.attendify_admin.common.validation

import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import jakarta.inject.Inject

class ValidatePhoneNumber @Inject constructor() {
    private val phoneUtil = PhoneNumberUtil.getInstance()
    operator fun invoke(
        phoneNumber: String,
        regionCode: String,
    ): ValidationResult {
        val numberProto = runCatching {
            phoneUtil.parse(phoneNumber, regionCode)
        }

        var errorMessage: String? = null
        numberProto.onFailure { e ->
            errorMessage = if (e is NumberParseException) {
                when (e.errorType) {
                    NumberParseException.ErrorType.INVALID_COUNTRY_CODE ->
                        "The country code is not valid."

                    NumberParseException.ErrorType.NOT_A_NUMBER ->
                        "This doesn't look like a valid phone number."

                    NumberParseException.ErrorType.TOO_SHORT_AFTER_IDD ->
                        "The phone number is too short after the international prefix."

                    NumberParseException.ErrorType.TOO_SHORT_NSN ->
                        "The phone number is too short."

                    NumberParseException.ErrorType.TOO_LONG ->
                        "The phone number is too long."

                    else -> // Should not happen with the defined enum types
                        "Invalid phone number format. Please check and try again."
                }
            } else {
                "Phone number is not valid"
            }
        }
        return ValidationResult(
            successful = errorMessage == null,
            errorMessage = errorMessage
        )
    }
}
