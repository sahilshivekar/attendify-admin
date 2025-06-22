package com.attendify_admin.home.feature_users.presentation.add_student.components

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.attendify_admin.common.presentation.PreviewWrapper
import com.attendify_admin.common.presentation.UiConstants
import com.attendify_admin.common.presentation.components.AttendifyTextField
import com.attendify_admin.home.feature_users.presentation.add_student.AddStudentEvent
import com.attendify_admin.home.feature_users.presentation.add_student.AddStudentState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactDetailsContainer(
    modifier: Modifier = Modifier,
    onEvent: (AddStudentEvent) -> Unit,
    state: AddStudentState,
) {
    val localFocusManager = LocalFocusManager.current
    Column(
        modifier = modifier
            .widthIn(max = UiConstants.MAX_WIDTH)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    localFocusManager.clearFocus()
                })
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = if (state.studentId == null) "Fill student's contact details" else "Edit student's contact details",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.Start)
        )

        ExposedDropdownMenuBox(
            expanded = state.isCountryCodeDropDownOpen,
            onExpandedChange = { onEvent(AddStudentEvent.CountryCodeDropDownVisibilityChanged(it)) },
        ) {

            AttendifyTextField(
                value = state.country?.name ?: "",
                onValueChange = { },
                label = "Country",
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(
                        MenuAnchorType.PrimaryEditable
                    ),
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = state.isCountryCodeDropDownOpen,
                        modifier = Modifier.menuAnchor(MenuAnchorType.SecondaryEditable),
                    )
                },
                isError = state.isCountryError != null,
                supportingText = state.isCountryError
            )
            val dropDownScrollState = rememberScrollState()
            ExposedDropdownMenu(
                expanded = state.isCountryCodeDropDownOpen,
                onDismissRequest = {
                    onEvent(
                        AddStudentEvent.CountryCodeDropDownVisibilityChanged(
                            false
                        )
                    )
                    localFocusManager.clearFocus()
                },
                shape = MaterialTheme.shapes.medium,
                containerColor = MaterialTheme.colorScheme.surface,
                scrollState = dropDownScrollState,
                modifier = Modifier.heightIn(max = 300.dp)
            ) {
                state.countryCodeOptions.forEach { option ->
                    DropdownMenuItem(
                        onClick = {
                            onEvent(AddStudentEvent.PhoneNumberCountryCodeChanged(option))
                            onEvent(AddStudentEvent.CountryCodeDropDownVisibilityChanged(false))
                            localFocusManager.clearFocus()
                        },
                        text = {
                            Text(
                                text = option.name,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        },
                        trailingIcon = {
                            Text(
                                text = option.phoneCode,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    )
                }
            }
        }

        val phoneRequester = remember { FocusRequester() }
        val emailRequester = remember { FocusRequester() }
        val parentEmailRequester = remember { FocusRequester() }

        AttendifyTextField(
            value = state.phoneNumber,
            onValueChange = { onEvent(AddStudentEvent.PhoneNumberChanged(it)) },
            label = "Phone Number",
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(phoneRequester),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { emailRequester.requestFocus() }
            ),
            enabled = !state.isSubmitting && !state.isSubmitted,
            supportingText = state.isPhoneNumberError,
            isError = state.isPhoneNumberError != null,
            prefix = {
                Text(state.country?.phoneCode ?: "")
            }
        )

        AttendifyTextField(
            value = state.email,
            onValueChange = { onEvent(AddStudentEvent.EmailChanged(it)) },
            label = "Email",
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(emailRequester),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { parentEmailRequester.requestFocus() }
            ),
            enabled = !state.isSubmitting && !state.isSubmitted,
            supportingText = state.isEmailError,
            isError = state.isEmailError != null
        )

        AttendifyTextField(
            value = state.parentEmail,
            onValueChange = { onEvent(AddStudentEvent.ParentEmailChanged(it)) },
            label = "Parent's email",
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(parentEmailRequester),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            enabled = !state.isSubmitting && !state.isSubmitted,
            supportingText = state.isParentEmailError,
            isError = state.isParentEmailError != null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ContactDetailsContainerPreview() {
    PreviewWrapper {
        ContactDetailsContainer(
            onEvent = {},
            state = AddStudentState(
                phoneNumber = "",
                email = ""
            )
        )
    }
}

