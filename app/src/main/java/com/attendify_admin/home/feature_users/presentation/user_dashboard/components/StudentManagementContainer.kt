package com.attendify_admin.home.feature_users.presentation.user_dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.attendify_admin.common.presentation.PreviewWrapper
import com.attendify_admin.common.presentation.UiConstants

@Composable
fun StudentManagementContainer(
    modifier: Modifier = Modifier,
    onAddStudentClick: () -> Unit,
    onSearchStudentClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .widthIn(max = UiConstants.MAX_WIDTH)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Student Management",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ListItem(
                headlineContent = { Text("Add") },
                trailingContent = {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "Add Student",
                    )
                },
                modifier = Modifier
                    .weight(.5f)
                    .height(65.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable(onClick = onAddStudentClick),
                colors = ListItemDefaults.colors(
                    trailingIconColor = MaterialTheme.colorScheme.primary
                )
            )
            ListItem(
                headlineContent = { Text("Search") },
                trailingContent = {
                    Icon(
                        Icons.Filled.Search,
                        contentDescription = "Search Student",
                    )
                },
                modifier = Modifier
                    .weight(.5f)
                    .height(65.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable(onClick = onSearchStudentClick),
                colors = ListItemDefaults.colors(
                    trailingIconColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    }

}

@Preview
@Composable
fun StudentManagementContainerPreview() {
    PreviewWrapper {
        StudentManagementContainer(
            onAddStudentClick = {},
            onSearchStudentClick = {}
        )
    }
}
