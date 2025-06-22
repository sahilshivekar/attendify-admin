package com.attendify_admin.feature_admin_mgt.presentation.admin_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.attendify_admin.common.presentation.UiConstants
import com.attendify_admin.common.presentation.components.AttendifyTextField
import com.attendify_admin.feature_admin_mgt.presentation.admin_details.AdminDetailsState

@Composable
fun PasswordContainer(
    state: AdminDetailsState,
    modifier: Modifier = Modifier,
    navigateToUpdatePassword: () -> Unit
) {
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
                text = "Password",
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
            )
            IconButton(
                onClick = { navigateToUpdatePassword() },
                enabled = !state.isSendingVerificationCode && !state.isLoggingOut && !state.isRemovingAccount && !state.isUpdatingDetails
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
}

