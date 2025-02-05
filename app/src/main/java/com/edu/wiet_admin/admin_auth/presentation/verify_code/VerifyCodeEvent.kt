package com.edu.wiet_admin.admin_auth.presentation.verify_code

import com.edu.wiet_admin.admin_auth.presentation.login.LoginEvent

sealed class VerifyCodeEvent {
    data class CodeChanged(val code: String) : VerifyCodeEvent()
    data object VerifyCodeClicked : VerifyCodeEvent()
    data object DismissAlertDialog: VerifyCodeEvent()
}