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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.attendify_admin.common.presentation.UiConstants


@Composable
fun DropoutManagementContainer(
    modifier: Modifier = Modifier,
    onAddToDropout: () -> Unit,
    onRemoveFromDropout: () -> Unit,
) {
    Column(
        modifier = modifier
            .widthIn(max = UiConstants.MAX_WIDTH)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.Start
    ) {

        Text(
            text = "Dropout students management",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ListItem(
                    headlineContent = { Text("Add to dropout") },
                    trailingContent = {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add student",
                            tint = MaterialTheme.colorScheme.primary,
                        )
                    },
                    modifier = Modifier
                        .weight(.5f)
                        .height(65.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .clickable { onAddToDropout() }
                )
                ListItem(
                    headlineContent = { Text("Remove from dropout") },
                    trailingContent = {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Remove student",
                            tint = MaterialTheme.colorScheme.primary,
                        )
                    },
                    modifier = Modifier
                        .weight(.5f)
                        .height(65.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .clickable { onRemoveFromDropout() }
                )
            }
        }
    }
}