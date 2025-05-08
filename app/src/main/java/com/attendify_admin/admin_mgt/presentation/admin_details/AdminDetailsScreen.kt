package com.attendify_admin.admin_mgt.presentation.admin_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.attendify_admin.common.presentation.PreviewWrapper
import com.attendify_admin.common.presentation.ScreenPreview
import com.attendify_admin.common.presentation.components.AttendifyAlertDialog
import com.attendify_admin.common.presentation.components.AttendifyButton
import com.attendify_admin.common.presentation.components.AttendifyOptionRow
import com.attendify_admin.common.presentation.components.AttendifyOutlinedButton
import com.attendify_admin.common.presentation.components.AttendifyTextField
import com.attendify_admin.common.presentation.components.top_bar.AttendifyTopAppBar
import com.attendify_admin.common.presentation.components.top_bar.TopAppBarState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDetailsScreen(
    modifier: Modifier = Modifier,
    navigateToUpdatePassword: () -> Unit,
    navigateToVerifyCode: () -> Unit,
    navigateToAddAdmin: () -> Unit,
    state: AdminDetailsState,
    onEvent: (AdminDetailsEvent) -> Unit,
    onBackIconButtonClick: () -> Unit
) {

    if (state.isVerificationCodeSent) {
        navigateToVerifyCode()
    }

    if (state.showRemoveAccountConfirmationDialog) {
        AttendifyAlertDialog(
            dialogText = "Are you sure you want to remove your account? this action will also log you out",
            confirmButtonText = "Confirm",
            dismissButtonText = "Dismiss",
            onDismiss = { onEvent(AdminDetailsEvent.DismissRemoveAccountClicked) },
            onConfirm = { onEvent(AdminDetailsEvent.RemoveAdminConfirmed) }
        )
    }

    state.alertMessage?.let {
        AttendifyAlertDialog(
            dialogText = it,
            onDismiss = { onEvent(AdminDetailsEvent.DismissAlertDialog) }
        )
    }

    Scaffold(
        topBar = {
            AttendifyTopAppBar(
                topAppBarState = TopAppBarState(
                    title = "Admin Details",
                    isTopAppBarVisible = true,
                    isBackIconButtonVisible = true,
                    isProfileIconButtonVisible = false
                ),
                onBackIconButtonClick = onBackIconButtonClick
            )
        }
    ) { paddingValues ->
        if (state.isInitialDataLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        } else {
            Column(
                modifier = modifier
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // icon on top
                Spacer(modifier = Modifier.height(16.dp))
                Icon(
                    modifier = Modifier
                        .size(90.dp)
                        .clip(CircleShape),
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )


                // email and username
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.medium)
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(16.dp)
                        .wrapContentSize(),
                    horizontalAlignment = Alignment.Start
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
                            onClick = { onEvent(AdminDetailsEvent.EditDetailsClicked) }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    AttendifyTextField(
                        value = if (state.isEditingDetails) state.editableUsername else state.orgUsername,
                        onValueChange = { onEvent(AdminDetailsEvent.UsernameChanged(it)) },
                        isError = state.usernameError != null,
                        supportingText = state.usernameError,
                        label = "Username",
                        enabled = state.isUsernameEmailEnabled
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    AttendifyTextField(
                        value = if (state.isEditingDetails) state.editableEmail else state.orgEmail,
                        onValueChange = { onEvent(AdminDetailsEvent.EmailChanged(it)) },
                        isError = state.emailError != null,
                        supportingText = state.emailError,
                        label = "Email",
                        enabled = state.isUsernameEmailEnabled
                    )
                    if (state.isEditingDetails) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AttendifyOutlinedButton(
                                onClick = { onEvent(AdminDetailsEvent.CancelEditingDetailsClicked) },
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(end = 8.dp),
                                enabled = !state.isUpdatingDetails
                            ) {
                                Text(
                                    text = "Cancel",
                                    style = MaterialTheme.typography.bodyMedium,
                                )
                            }
                            AttendifyButton(
                                onClick = { onEvent(AdminDetailsEvent.UpdateDetailsClicked) },
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 8.dp),
                                text = "Update",
                                isLoading = state.isUpdatingDetails,
                                enabled = !state.isUpdatingDetails
                            )
                        }
                    }

                }


                // password
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.medium)
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(16.dp)
                        .wrapContentSize(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Password",
                            style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
                        )
                        IconButton(
                            onClick = { navigateToUpdatePassword() }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    AttendifyTextField(
                        value = "........",
                        onValueChange = {},
                        label = "Password",
                        visualTransformation = PasswordVisualTransformation(),
                        enabled = false
                    )
                }

                // account settings
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.medium)
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(16.dp)
                        .wrapContentSize(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp), // to match the height of the icons and continue the ux size
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Account settings",
                            style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    // options
                    AttendifyOptionRow(
                        onClick = navigateToAddAdmin,
                        optionText = "Add another admin",
                        isLoading = false
                    )
                    state.isVerified?.let {
                        if (!state.isVerified) {
                            AttendifyOptionRow(
                                onClick = {
                                    onEvent(AdminDetailsEvent.VerifyEmailClicked)
                                },
                                optionText = "Verify email address",
                                isLoading = state.isSendingVerificationCode
                            )
                        }
                    }
                    AttendifyOptionRow(
                        onClick = { onEvent(AdminDetailsEvent.LogoutClicked) },
                        optionText = "Log out",
                        isLoading = state.isLoggingOut
                    )
                    AttendifyOptionRow(
                        onClick = { onEvent(AdminDetailsEvent.RemoveAdminClicked) },
                        optionText = "Remove account",
                        isLoading = state.isRemovingAccount,
                        isRisky = true,
                        showDivider = false,
                    )
                }
            }
        }
    }
}


@ScreenPreview
@Composable
fun AdminDetailsScreenPreview() {
    PreviewWrapper {
        AdminDetailsScreen(
            navigateToUpdatePassword = {},
            navigateToAddAdmin = {},
            navigateToVerifyCode = {},
            state = AdminDetailsState(),
            onEvent = {},
            onBackIconButtonClick = {}
        )
    }
}