package com.attendify_admin.home.feature_users.presentation.user_dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.attendify_admin.R
import com.attendify_admin.common.presentation.PreviewWrapper
import com.attendify_admin.common.presentation.UiConstants

@Composable
fun StudentAllocationContainer(
    modifier: Modifier = Modifier,
    onAssignStudentToSemesterClick: () -> Unit,
    onRemoveStudentFromSemesterClick: () -> Unit,
    onAssignStudentToDivisionClick: () -> Unit,
    onModifyStudentDivisionClick: () -> Unit,
    onAssignStudentToBatchClick: () -> Unit,
    onModifyStudentBatchClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .widthIn(max = UiConstants.MAX_WIDTH)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.Start
    ) {

        Text(
            text = "Student Allocation",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ListItem(
                    headlineContent = { Text("Assign semester") },
                    leadingContent = {
                        Icon(
                            painter = painterResource(id = R.drawable.add_to_semester),
                            contentDescription = "Assign semester",
                            tint = MaterialTheme.colorScheme.primary,
                        )
                    },
                    modifier = Modifier
                        .weight(.5f)
                        .height(65.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .clickable { onAssignStudentToSemesterClick() }
                )
                ListItem(
                    headlineContent = { Text("Unassign semester") },
                    leadingContent = {
                        Icon(
                            painter = painterResource(id = R.drawable.add_to_semester),
                            contentDescription = "Unassign semester",
                            tint = MaterialTheme.colorScheme.error,
                        )
                    },
                    modifier = Modifier
                        .weight(.5f)
                        .height(65.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .clickable { onRemoveStudentFromSemesterClick() }
                )
            }

        }

        Spacer(Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ListItem(
                headlineContent = { Text("Assign division") },
                leadingContent = {
                    Icon(
                        painter = painterResource(id = R.drawable.group_division),
                        contentDescription = "Assign division",
                        tint = MaterialTheme.colorScheme.primary,
                    )
                },
                modifier = Modifier
                    .weight(.5f)
                    .height(65.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable { onAssignStudentToDivisionClick() }
            )
            ListItem(
                headlineContent = { Text("Change division") },
                leadingContent = {
                    Icon(
                        painter = painterResource(id = R.drawable.group_division),
                        contentDescription = "Change division",
                        tint = MaterialTheme.colorScheme.error,
                    )
                },
                modifier = Modifier
                    .weight(.5f)
                    .height(65.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable { onModifyStudentDivisionClick() }
            )
        }

        Spacer(Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ListItem(
                headlineContent = { Text("Assign batch") },
                leadingContent = {
                    Icon(
                        painter = painterResource(id = R.drawable.group_batch),
                        contentDescription = "Assign batch",
                        tint = MaterialTheme.colorScheme.primary,
                    )
                },
                modifier = Modifier
                    .weight(.5f)
                    .height(65.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable { onAssignStudentToBatchClick() }
            )
            ListItem(
                headlineContent = { Text("Change batch") },
                leadingContent = {
                    Icon(
                        painter = painterResource(id = R.drawable.group_batch),
                        contentDescription = "Change batch",
                        tint = MaterialTheme.colorScheme.error,
                    )
                },
                modifier = Modifier
                    .weight(.5f)
                    .height(65.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable { onModifyStudentBatchClick() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StudentAllocationContainerPreview() {
    PreviewWrapper {

        StudentAllocationContainer(
            onAssignStudentToSemesterClick = {},
            onRemoveStudentFromSemesterClick = {},
            onAssignStudentToDivisionClick = {},
            onModifyStudentDivisionClick = {},
            onAssignStudentToBatchClick = {},
            onModifyStudentBatchClick = {}
        )
    }
}




