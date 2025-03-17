package com.edu.wiet_admin.users.presentation.user_dashboard

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.edu.wiet_admin.R
import com.edu.wiet_admin.common.presentation.PreviewWrapper
import com.edu.wiet_admin.common.presentation.ScreenPreview
import com.edu.wiet_admin.common.presentation.components.OptionCard


@Composable
fun UserDashboardScreen(
    onAddStudentClick: () -> Unit,
    onEditStudentClick: () -> Unit,
    onRemoveStudentClick: () -> Unit,
    onSearchStudentClick: () -> Unit,
    onAddStaffClick: () -> Unit,
    onEditStaffClick: () -> Unit,
    onRemoveStaffClick: () -> Unit,
    onSearchStaffClick: () -> Unit,
    onAssignStudentToSemesterClick: () -> Unit,
    onRemoveStudentFromSemesterClick: () -> Unit,
    onAssignStudentToDivisionClick: () -> Unit,
    onModifyStudentDivisionClick: () -> Unit,
    onRemoveStudentFromDivisionClick: () -> Unit,
    onAssignStudentToBatchClick: () -> Unit,
    onModifyStudentBatchClick: () -> Unit,
    onRemoveStudentFromBatchClick: () -> Unit,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Student Management",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(top = 16.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OptionCard(
                    optionTitle = "Add Student",
                    icon = R.drawable.add,
                    modifier = Modifier
                        .weight(.5f)
                        .padding(end = 8.dp),
                    onClick = onAddStudentClick
                )
                OptionCard(
                    optionTitle = "Edit Student",
                    icon = R.drawable.edit_outlined,
                    modifier = Modifier
                        .weight(.5f),
                    onClick = onEditStudentClick
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OptionCard(
                    optionTitle = "Search Student",
                    icon = R.drawable.baseline_search_24,
                    modifier = Modifier
                        .weight(.5f)
                        .padding(end = 8.dp),
                    onClick = onSearchStudentClick
                )
                OptionCard(
                    optionTitle = "Remove Student",
                    icon = R.drawable.outline_delete_outline_24,
                    modifier = Modifier
                        .weight(.5f),
                    onClick = onRemoveStudentClick
                )
            }
        }
        Text(
            text = "Staff Management",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(top = 16.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OptionCard(
                    optionTitle = "Add Staff",
                    icon = R.drawable.add,
                    modifier = Modifier
                        .weight(.5f)
                        .padding(end = 8.dp),
                    onClick = onAddStaffClick
                )
                OptionCard(
                    optionTitle = "Edit Staff",
                    icon = R.drawable.edit_outlined,
                    modifier = Modifier
                        .weight(.5f),
                    onClick = onEditStaffClick
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OptionCard(
                    optionTitle = "Search Staff",
                    icon = R.drawable.baseline_search_24,
                    modifier = Modifier
                        .weight(.5f)
                        .padding(end = 8.dp),
                    onClick = onSearchStaffClick
                )
                OptionCard(
                    optionTitle = "Remove Staff",
                    icon = R.drawable.outline_delete_outline_24,
                    modifier = Modifier
                        .weight(.5f),
                    onClick = onRemoveStaffClick
                )
            }
        }
        Text(
            text = "Student Allocation",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(top = 16.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OptionCard(
                    optionTitle = "Assign student    to semester",
                    icon = R.drawable.add_to_semester,
                    modifier = Modifier
                        .weight(.5f)
                        .padding(end = 8.dp),
                    onClick = onAddStaffClick
                )
                OptionCard(
                    optionTitle = "Remove student from semester",
                    icon = R.drawable.student_semester_remove,
                    modifier = Modifier
                        .weight(.5f),
                    onClick = onEditStaffClick
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OptionCard(
                    optionTitle = "Assign student   to division",
                    icon = R.drawable.group_division,
                    modifier = Modifier
                        .weight(.5f)
                        .padding(end = 8.dp),
                    onClick = onAddStaffClick
                )
                OptionCard(
                    optionTitle = "Modify division of student",
                    icon = R.drawable.edit_outlined,
                    modifier = Modifier
                        .weight(.5f),
                    onClick = onEditStaffClick
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OptionCard(
                    optionTitle = "Assign student   to batch",
                    icon = R.drawable.group_batch,
                    modifier = Modifier
                        .weight(.5f)
                        .padding(end = 8.dp),
                    onClick = onSearchStaffClick
                )
                OptionCard(
                    optionTitle = "Modify batch of student",
                    icon = R.drawable.edit_outlined,
                    modifier = Modifier
                        .weight(.5f),
                    onClick = onRemoveStaffClick
                )
            }
        }
    }
}


@ScreenPreview
@Composable
fun UserDashboardScreenPreview() {
    PreviewWrapper {
        UserDashboardScreen({}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {})
    }
}