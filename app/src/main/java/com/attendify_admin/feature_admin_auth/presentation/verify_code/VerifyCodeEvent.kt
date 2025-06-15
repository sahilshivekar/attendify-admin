package com.attendify_admin.feature_admin_auth.presentation.verify_code


sealed class VerifyCodeEvent {
    data class CodeChanged(val code: String) : VerifyCodeEvent()
    data object VerifyCodeClicked : VerifyCodeEvent()
    data object DismissAlertDialog: VerifyCodeEvent()
}