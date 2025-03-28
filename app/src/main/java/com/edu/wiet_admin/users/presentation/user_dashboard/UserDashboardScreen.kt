package com.edu.wiet_admin.users.presentation.user_dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.edu.wiet_admin.R
import com.edu.wiet_admin.common.presentation.PreviewWrapper
import com.edu.wiet_admin.common.presentation.ScreenPreview
import com.edu.wiet_admin.common.presentation.components.OptionCard
import com.edu.wiet_admin.common.presentation.components.WietOptionRow


@Composable
fun UserDashboardScreen(
    onAddStudentClick: () -> Unit,
    onSearchStudentClick: () -> Unit,
    onAddStaffClick: () -> Unit,
    onSearchStaffClick: () -> Unit,
    onAssignStudentToSemesterClick: () -> Unit,
    onRemoveStudentFromSemesterClick: () -> Unit,
    onAssignStudentToDivisionClick: () -> Unit,
    onModifyStudentDivisionClick: () -> Unit,
    onAssignStudentToBatchClick: () -> Unit,
    onModifyStudentBatchClick: () -> Unit,
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
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
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
                    optionTitle = "Search Student",
                    icon = R.drawable.baseline_search_24,
                    modifier = Modifier
                        .weight(.5f),
                    onClick = onSearchStudentClick
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
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                WietOptionRow(
                    onClick = { },
                    showDivider = false,
                    optionText = "Assign semester",
                    isLoading = false,
                    isRisky = false,
                    iconId = R.drawable.add_to_semester,
                    modifier = Modifier.weight(.5f)
                )
                WietOptionRow(
                    onClick = { },
                    showDivider = false,
                    optionText = "Unassign semester",
                    isLoading = false,
                    isRisky = true,
                    iconId = R.drawable.add_to_semester,
                    modifier = Modifier.weight(.5f)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                WietOptionRow(
                    onClick = { },
                    showDivider = false,
                    optionText = "Assign division",
                    isLoading = false,
                    isRisky = false,
                    iconId = R.drawable.group_division,
                    modifier = Modifier.weight(.5f)
                )
                WietOptionRow(
                    onClick = { },
                    showDivider = false,
                    optionText = "Modify division",
                    isLoading = false,
                    isRisky = true,
                    iconId = R.drawable.group_division,
                    modifier = Modifier.weight(.5f)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                WietOptionRow(
                    onClick = { },
                    showDivider = false,
                    optionText = "Assign batch",
                    isLoading = false,
                    isRisky = false,
                    iconId = R.drawable.group_batch,
                    modifier = Modifier.weight(.5f)
                )
                WietOptionRow(
                    onClick = { },
                    showDivider = false,
                    optionText = "Modify batch",
                    isLoading = false,
                    isRisky = true,
                    iconId = R.drawable.group_batch,
                    modifier = Modifier.weight(.5f)
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
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
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
                    optionTitle = "Search Staff",
                    icon = R.drawable.baseline_search_24,
                    modifier = Modifier
                        .weight(.5f),
                    onClick = onSearchStaffClick
                )
            }
        }
        Text(
            text = "Allocate subjects to teacher",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(top = 16.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                WietOptionRow(
                    onClick = { },
                    showDivider = false,
                    optionText = "Assign subject",
                    isLoading = false,
                    isRisky = false,
                    iconId = R.drawable.round_menu_book_24,
                    modifier = Modifier.weight(.5f)
                )
                WietOptionRow(
                    onClick = { },
                    showDivider = false,
                    optionText = "Unassign subject",
                    isLoading = false,
                    isRisky = true,
                    iconId = R.drawable.round_menu_book_24,
                    modifier = Modifier.weight(.5f)
                )
            }
        }
    }
}


@ScreenPreview
@Composable
fun UserDashboardScreenPreview() {
    PreviewWrapper {
        UserDashboardScreen({}, {}, {}, {}, {}, {}, {}, {}, {}, {})
    }
}