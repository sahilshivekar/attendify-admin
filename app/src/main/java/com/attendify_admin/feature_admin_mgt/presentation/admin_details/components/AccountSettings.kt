package com.attendify_admin.feature_admin_mgt.presentation.admin_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.attendify_admin.common.presentation.PreviewWrapper
import com.attendify_admin.common.presentation.UiConstants
import com.attendify_admin.feature_admin_mgt.presentation.admin_details.AdminDetailsEvent
import com.attendify_admin.feature_admin_mgt.presentation.admin_details.AdminDetailsState

@Composable
fun AccountSettings(
    modifier: Modifier = Modifier,
    onEvent: (AdminDetailsEvent) -> Unit,
    state: AdminDetailsState,
    navigateToAddAdmin: () -> Unit,
) {
    Column(
        modifier = modifier
            .widthIn(max = UiConstants.MAX_WIDTH)
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.surface)
            ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), // to match the height of the icons and continue the ux size
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Account settings",
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
            )
        }
        Spacer(modifier = Modifier.height(8.dp))


        if (state.isVerified == false) {
            ListItem(
                headlineContent = {
                    Text(text = "Verify email address")
                },
                trailingContent = {
                    if (state.isSendingVerificationCode) {
                        CircularProgressIndicator(
                            strokeWidth = 2.dp,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                modifier = Modifier.clickable(
                    !state.isSendingVerificationCode && !state.isLoggingOut && !state.isRemovingAccount && !state.isUpdatingDetails
                ) {
                    if (!state.isSendingVerificationCode && !state.isLoggingOut && !state.isRemovingAccount && !state.isUpdatingDetails)
                        onEvent(AdminDetailsEvent.VerifyEmailClicked)
                }
            )
        }
        if (state.isVerified == false) {
            HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
        }
        ListItem(
            headlineContent = {
                Text(text = "Add admin")
            },
            modifier = Modifier.clickable(
                !state.isSendingVerificationCode && !state.isLoggingOut && !state.isRemovingAccount && !state.isUpdatingDetails
            ) {
                navigateToAddAdmin()
            }
        )
        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))

        ListItem(
            headlineContent = {
                Text(text = "Log out")
            },
            trailingContent = {
                if (state.isLoggingOut) {
                    CircularProgressIndicator(strokeWidth = 2.dp, modifier = Modifier.size(24.dp))
                }
            },
            modifier = Modifier.clickable(
                !state.isSendingVerificationCode && !state.isLoggingOut && !state.isRemovingAccount && !state.isUpdatingDetails
            ) {
                onEvent(AdminDetailsEvent.LogoutClicked)
            }
        )
        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))

        ListItem(
            headlineContent = {
                Text(
                    text = "Remove account",
                    color = MaterialTheme.colorScheme.error
                )
            },
            trailingContent = {
                if (state.isRemovingAccount) {
                    CircularProgressIndicator(strokeWidth = 2.dp, modifier = Modifier.size(24.dp))
                }
            },
            modifier = Modifier.clickable(
                !state.isSendingVerificationCode && !state.isLoggingOut && !state.isRemovingAccount && !state.isUpdatingDetails
            ) {
                onEvent(AdminDetailsEvent.RemoveAdminClicked)
            }
        )

        Spacer(Modifier.height(16.dp))
    }
}


@Preview
@Composable
fun AccountSettingsPreview() {
    PreviewWrapper {
        AccountSettings(
            onEvent = {},
            state = AdminDetailsState(),
            navigateToAddAdmin = {}
        )
    }
}