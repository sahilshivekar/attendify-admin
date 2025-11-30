package com.presencify_admin.home.feature_users.presentation.user_dashboard.components

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
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.presencify_admin.R
import com.presencify_admin.common.presentation.PreviewWrapper
import com.presencify_admin.common.presentation.UiConstants

@Composable
fun StaffSubjectAllocationContainer(
    modifier: Modifier = Modifier,
    onAssignSubjectToTeacher: () -> Unit,
    onUnassignSubjectToTeacher: () -> Unit,
) {
    Column(
        modifier = modifier
            .widthIn(max = UiConstants.MAX_WIDTH)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.Start
    ) {

        Text(
            text = "Allocate subjects to teacher",
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
                    headlineContent = { Text("Assign subject") },
                    leadingContent = {
                        Icon(
                            painter = painterResource(id = R.drawable.round_menu_book_24),
                            contentDescription = "Assign subject",
                            tint = MaterialTheme.colorScheme.primary,
                        )
                    },
                    modifier = Modifier
                        .weight(.5f)
                        .height(65.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .clickable { onAssignSubjectToTeacher() }
                )
                ListItem(
                    headlineContent = { Text("Unassign subject") },
                    leadingContent = {
                        Icon(
                            painter = painterResource(id = R.drawable.round_menu_book_24),
                            contentDescription = "Assign subject",
                            tint = MaterialTheme.colorScheme.error,
                        )
                    },
                    modifier = Modifier
                        .weight(.5f)
                        .height(65.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .clickable { onUnassignSubjectToTeacher() }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StaffSubjectAllocationContainerPreview() {
    PreviewWrapper {
        StaffSubjectAllocationContainer(
            onAssignSubjectToTeacher = {},
            onUnassignSubjectToTeacher = {}
        )
    }
}
