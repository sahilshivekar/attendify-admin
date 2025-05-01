package com.attendify_admin.common.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.attendify_admin.R
import com.attendify_admin.common.data.remote.response_dto.Branch
import com.attendify_admin.common.presentation.ComponentPreview
import com.attendify_admin.common.presentation.PreviewWrapper


@Composable
fun <T> AttendifyFilterOption(
    modifier: Modifier = Modifier,
    option: T,
    optionText: String,
    onSelect: () -> Unit,
    onUnselect: () -> Unit,
    isSelected: Boolean = false
) {
    Button(
        onClick = {
            if (isSelected) onUnselect() else onSelect()
        },
        modifier = modifier
            .wrapContentWidth()
            .height(30.dp)
            .border(
                width = 1.dp,
                color = if (isSelected) MaterialTheme.colorScheme.primary else colorResource(id = R.color.text_field_border_label),
                shape = MaterialTheme.shapes.extraLarge
            )
            .clip(MaterialTheme.shapes.extraLarge),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
            contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
        ),
        contentPadding = PaddingValues(horizontal = 12.dp)

    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.wrapContentWidth()
        ) {
            Icon(
                imageVector = if (isSelected) Icons.Default.Check else Icons.Default.Close,
                contentDescription = null,
                modifier = Modifier
                    .height(20.dp)
                    .width(20.dp)
                    .padding(end = 8.dp)
            )
            Text(
                text = optionText,
                style = MaterialTheme.typography.labelMedium
            )

        }
    }
}


@ComponentPreview
@Composable
fun AttendifyFilterOptionPreview() {
    PreviewWrapper {
        AttendifyFilterOption(
            option = Branch(
                id = 1,
                abbreviation = "Comp. Engg.",
                name = "Computer Engineering",
                createdAt = "00",
                updatedAt = "00"
            ),
            optionText = "1",
            onSelect = {},
            onUnselect = {}
        )
    }
}
