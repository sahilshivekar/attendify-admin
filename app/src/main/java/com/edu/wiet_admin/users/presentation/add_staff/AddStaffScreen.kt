package com.edu.wiet_admin.users.presentation.add_staff

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.edu.wiet_admin.R
import com.edu.wiet_admin.common.presentation.components.WietAlertDialog
import com.edu.wiet_admin.common.presentation.components.WietButton
import com.edu.wiet_admin.common.presentation.components.WietDropDownMenuBox
import com.edu.wiet_admin.common.presentation.components.WietOutlinedButton
import com.edu.wiet_admin.common.presentation.components.WietTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddStaffScreen(
    modifier: Modifier = Modifier,
    onEvent: (AddStaffEvent) -> Unit,
    state: AddStaffState,
    onAddStaffSuccess: () -> Unit
) {
    val scrollState = rememberScrollState()
    val localFocusManager = LocalFocusManager.current

    if (state.isSubmitted && state.dialogText == null) {
        onAddStaffSuccess()
    }

    if (state.dialogText != null) {
        WietAlertDialog(
            dialogText = state.dialogText,
            onDismiss = { onEvent(AddStaffEvent.DismissAlertDialog) }
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            onEvent(AddStaffEvent.StaffImageUriUpdated(uri))
        }
    )


    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    localFocusManager.clearFocus()
                })
            },
    ) {
        Text(
            text = "Fill staff member details",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .padding(top = 16.dp)
                .align(Alignment.Start)
        )

        // Name Fields
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            WietTextField(
                value = state.firstName,
                onValueChange = { onEvent(AddStaffEvent.FirstNameChanged(it)) },
                label = "First Name",
                modifier = Modifier.weight(1f),
                supportingText = state.isFirstNameError,
                isError = state.isFirstNameError != null,
                enabled = !state.isSubmitted && !state.isSubmitting
            )
            WietTextField(
                value = state.middleName,
                onValueChange = { onEvent(AddStaffEvent.MiddleNameChanged(it)) },
                label = "Middle Name",
                modifier = Modifier.weight(1f),
                enabled = !state.isSubmitted && !state.isSubmitting
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            WietTextField(
                value = state.lastName,
                onValueChange = { onEvent(AddStaffEvent.LastNameChanged(it)) },
                label = "Last Name",
                modifier = Modifier.weight(1f),
                supportingText = state.isLastNameError,
                isError = state.isLastNameError != null,
                enabled = !state.isSubmitted && !state.isSubmitting
            )

            WietTextField(
                modifier = Modifier.weight(1f),
                enabled = !state.isSubmitting && !state.isSubmitted,
                value = "",
                onValueChange = { },
                readOnly = true,
                trailingIcon = {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = state.staffImageFileName ?: "Add Image",
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .weight(.7f),
                            color = if (state.staffImageFileName != null) MaterialTheme.colorScheme.onBackground else colorResource(
                                id = R.color.text_field_border_label
                            ),
                            style = if (state.staffImageFileName != null) MaterialTheme.typography.bodyLarge else MaterialTheme.typography.bodyMedium,
                        )

                        IconButton(
                            onClick = {
                                if (state.isStaffFileUploading) {
                                    // if loading do nothing
                                } else if (state.staffImageUri == null) {
                                    launcher.launch("image/*")
                                } else {
                                    onEvent(AddStaffEvent.StaffImageUriUpdated(null))
                                }
                            },
                            modifier = Modifier.weight(.3f)
                        ) {
                            if (!state.isStaffFileUploading) {
                                Icon(
                                    imageVector = if (state.staffImageFileName != null) Icons.Default.Close else Icons.Default.Add,
                                    contentDescription = null
                                )
                            } else {
                                CircularProgressIndicator(
                                    strokeWidth = 2.dp,
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier
                                        .padding(end = 16.dp)
                                        .size(20.dp)
                                )
                            }
                        }
                    }
                }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // gender dropdown
            WietDropDownMenuBox(
                modifier = Modifier.weight(1f),
                expanded = state.isGenderDropDownOpen,
                onDropDownVisibilityChanged = {
                    onEvent(AddStaffEvent.GenderDropDownVisibilityChanged(it))
                },
                value = state.gender?.displayName ?: "",
                enabled = !state.isSubmitted && !state.isSubmitting,
                supportingText = state.isGenderError,
                options = Gender.entries,
                onSelectItem = {
                    onEvent(AddStaffEvent.GenderChanged(it))
                },
                label = "Gender"
            )

            //role dropdown
            WietDropDownMenuBox(
                modifier = Modifier.weight(1f),
                expanded = state.isRoleDropDownOpen,
                onDropDownVisibilityChanged = {
                    onEvent(AddStaffEvent.RoleDropDownVisibilityChanged(it))
                },
                value = state.role?.displayName ?: "",
                enabled = !state.isSubmitted && !state.isSubmitting,
                supportingText = state.isRoleError,
                options = StaffRole.entries,
                onSelectItem = {
                    onEvent(AddStaffEvent.RoleChanged(it))
                },
                label = "Role"
            )

        }

        // Contact Information
        WietTextField(
            value = state.email,
            onValueChange = { onEvent(AddStaffEvent.EmailChanged(it)) },
            label = "Email",
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            supportingText = state.isEmailError,
            isError = state.isEmailError != null,
            enabled = !state.isSubmitted && !state.isSubmitting
        )

        WietTextField(
            value = state.phoneNumber,
            onValueChange = { onEvent(AddStaffEvent.PhoneNumberChanged(it)) },
            label = "Phone Number",
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            supportingText = state.isPhoneNumberError,
            isError = state.isPhoneNumberError != null,
            enabled = !state.isSubmitted && !state.isSubmitting
        )

        // Qualification
        WietTextField(
            value = state.highestQualification,
            onValueChange = { onEvent(AddStaffEvent.HighestQualificationChanged(it)) },
            label = "Highest Qualification",
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isSubmitted && !state.isSubmitting
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 600.dp)
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            WietOutlinedButton(
                onClick = {
                    onEvent(AddStaffEvent.ResetClicked)
                },
                enabled = !state.isSubmitting && !state.isSubmitted,
                modifier = Modifier
                    .weight(.5f)
                    .padding(end = 4.dp)
            ) {
                Text(
                    text = "Reset",
                )
            }
            // Submit Button
            WietButton(
                onClick = { onEvent(AddStaffEvent.SubmitClicked) },
                text = "Add Staff",
                modifier = Modifier
                    .weight(.5f)
                    .padding(start = 4.dp),
                isLoading = state.isSubmitting,
                enabled = !state.isSubmitting && !state.isSubmitted,
            )
        }
    }

}







