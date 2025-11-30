package com.presencify_admin.feature_admin_mgt.presentation.admin_details.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.presencify_admin.common.presentation.PreviewWrapper
import com.presencify_admin.common.presentation.UiConstants
import com.presencify_admin.common.presentation.components.PresencifyButton
import com.presencify_admin.common.presentation.components.PresencifyOutlinedButton
import com.presencify_admin.common.presentation.components.PresencifyTextField
import com.presencify_admin.feature_admin_mgt.presentation.admin_details.AdminDetailsEvent
import com.presencify_admin.feature_admin_mgt.presentation.admin_details.AdminDetailsState

@Composable
fun EmailAndPasswordContainer(
    modifier: Modifier = Modifier,
    onEvent: (AdminDetailsEvent) -> Unit,
    state: AdminDetailsState,
) {

    val emailFocusRequester = remember { FocusRequester() }

    Spacer(
        modifier = Modifier.height(16.dp)
    )
    Column(
        modifier = modifier
            .widthIn(max = UiConstants.MAX_WIDTH)
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Admin Details",
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
            )
            IconButton(
                onClick = { onEvent(AdminDetailsEvent.EditDetailsClicked) },
                enabled = !state.isSendingVerificationCode && !state.isLoggingOut && !state.isRemovingAccount && !state.isUpdatingDetails

            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        PresencifyTextField(
            value = if (state.isEditingDetails) state.editableUsername else state.orgUsername,
            onValueChange = { onEvent(AdminDetailsEvent.UsernameChanged(it)) },
            isError = state.usernameError != null,
            supportingText = state.usernameError,
            label = "Username",
            enabled = !state.isSendingVerificationCode && !state.isLoggingOut && !state.isRemovingAccount && !state.isUpdatingDetails && state.isEditingDetails,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = { emailFocusRequester.requestFocus() })
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        PresencifyTextField(
            value = if (state.isEditingDetails) state.editableEmail else state.orgEmail,
            onValueChange = { onEvent(AdminDetailsEvent.EmailChanged(it)) },
            isError = state.emailError != null,
            supportingText = state.emailError,
            label = "Email",
            enabled = !state.isSendingVerificationCode && !state.isLoggingOut && !state.isRemovingAccount && !state.isUpdatingDetails && state.isEditingDetails,
            modifier = Modifier.focusRequester(emailFocusRequester).widthIn(max = UiConstants.MAX_WIDTH)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { onEvent(AdminDetailsEvent.UpdateDetailsClicked) })
        )
        AnimatedVisibility(
            visible = state.isEditingDetails,
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            Spacer(
                modifier = Modifier.height(8.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                PresencifyOutlinedButton(
                    onClick = { onEvent(AdminDetailsEvent.CancelEditingDetailsClicked) },
                    modifier = Modifier.Companion
                        .weight(1f)
                        .padding(end = 8.dp),
                    enabled = !state.isSendingVerificationCode && !state.isLoggingOut && !state.isRemovingAccount && !state.isUpdatingDetails
                ) {
                    Text(
                        text = "Cancel",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
                PresencifyButton(
                    onClick = { onEvent(AdminDetailsEvent.UpdateDetailsClicked) },
                    modifier = Modifier.Companion
                        .weight(1f)
                        .padding(start = 8.dp),
                    text = "Update",
                    isLoading = state.isUpdatingDetails,
                    enabled = !state.isSendingVerificationCode && !state.isLoggingOut && !state.isRemovingAccount && !state.isUpdatingDetails
                )
            }
        }

    }
}

@Preview
@Composable
fun EmailAndPasswordContainerPreview() {
    PreviewWrapper {
        EmailAndPasswordContainer(
            onEvent = {},
            state = AdminDetailsState(),
        )
    }
}