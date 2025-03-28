package com.edu.wiet_admin.common.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T : Enum<T>>  WietDropDownMenuBox(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    onDropDownVisibilityChanged: (Boolean) -> Unit,
    value: String,
    enabled: Boolean,
    supportingText: String? = null,
    options: List<T>,
    onSelectItem: (T) -> Unit,
    label: String,
    ) {

    val localFocusManager = LocalFocusManager.current
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { onDropDownVisibilityChanged(it) },
        modifier = modifier
//            .weight(.5f)
//            .padding(start = 4.dp)
    ) {
        WietTextField(
            value = value,
            onValueChange = {},
            label = label,
            readOnly = true,
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(MenuAnchorType.PrimaryEditable),
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded,
                    modifier = Modifier.menuAnchor(MenuAnchorType.SecondaryEditable),
                )
            },
            enabled = enabled,
            supportingText = supportingText,
            isError = supportingText != null
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                onDropDownVisibilityChanged(false)
                localFocusManager.clearFocus()
            },
            shape = MaterialTheme.shapes.medium,
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        onSelectItem(option)
                        onDropDownVisibilityChanged(false)
                        localFocusManager.clearFocus()
                    },
                    text = {
                        Text(text = option.toString(), color = MaterialTheme.colorScheme.onSurface)
                    }
                )
            }
        }
    }
}