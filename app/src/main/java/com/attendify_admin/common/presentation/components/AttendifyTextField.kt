package com.attendify_admin.common.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import com.attendify_admin.R
import com.attendify_admin.common.presentation.ComponentPreview
import com.attendify_admin.common.presentation.PreviewWrapper
import com.attendify_admin.common.presentation.UiConstants
import com.attendify_admin.ui.theme.Typography


@Composable
fun AttendifyTextField(
    modifier: Modifier = Modifier
        .widthIn(max = UiConstants.MAX_WIDTH)
        .fillMaxWidth(),
    value: String,
    onValueChange: (String) -> Unit,
    label: String? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = Typography.bodyLarge,
    isError: Boolean = false,
    supportingText: String? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    shape: Shape = MaterialTheme.shapes.medium,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors().copy(
        focusedTextColor = colorResource(R.color.normal_text),
        unfocusedTextColor = colorResource(R.color.normal_text),
        disabledTextColor = colorResource(R.color.normal_text).copy(alpha = 0.70f),

        focusedLabelColor = MaterialTheme.colorScheme.primary,
        unfocusedLabelColor = colorResource(R.color.text_field_border_label),
        disabledLabelColor = colorResource(R.color.text_field_border_label).copy(alpha = 0.30f),
        errorLabelColor = MaterialTheme.colorScheme.error,

        focusedIndicatorColor = MaterialTheme.colorScheme.primary,
        unfocusedIndicatorColor = colorResource(R.color.text_field_border_label),
        disabledIndicatorColor = colorResource(R.color.text_field_border_label).copy(alpha = 0.30f),
        errorIndicatorColor = MaterialTheme.colorScheme.error,

        focusedSupportingTextColor = MaterialTheme.colorScheme.primary,
        unfocusedSupportingTextColor = colorResource(R.color.text_field_border_label),
        disabledSupportingTextColor = colorResource(R.color.text_field_border_label).copy(alpha = 0.30f),
        errorSupportingTextColor = MaterialTheme.colorScheme.error,
    ),
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        label = {
            label?.let {
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelLarge,
                    maxLines = 1,
//                    overflow = TextOverflow.Visible,
                    modifier = Modifier.wrapContentWidth(unbounded = true)
                )
            }
        },
        isError = isError,
        supportingText = {
            AnimatedVisibility(
                visible = supportingText != null,
                enter = expandVertically(),
                exit = shrinkVertically()
                ) {
                Text(
                    text = supportingText ?: "",
                    style = MaterialTheme.typography.labelSmall
                )
            }
        },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        prefix = prefix,
        suffix = suffix,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        maxLines = maxLines,
        minLines = minLines,
        shape = shape,
        colors = colors,
        placeholder = placeholder,
        singleLine = singleLine
    )
}

@ComponentPreview
@Composable
fun AttendifyPrimaryTextFieldPreview() {
    PreviewWrapper {
        AttendifyTextField(
            value = "sahilshivekar124",
            onValueChange = {},
            label = "Username",
        )
    }
}
