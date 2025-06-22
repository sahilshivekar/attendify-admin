package com.attendify_admin.home.feature_users.presentation.user_dashboard

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.attendify_admin.common.presentation.PreviewWrapper
import com.attendify_admin.home.feature_users.presentation.user_dashboard.components.StaffManagementContainer
import com.attendify_admin.home.feature_users.presentation.user_dashboard.components.StaffSubjectAllocationContainer
import com.attendify_admin.home.feature_users.presentation.user_dashboard.components.StudentAllocationContainer
import com.attendify_admin.home.feature_users.presentation.user_dashboard.components.StudentManagementContainer


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
    onAssignSubjectToTeacher: () -> Unit,
    onUnassignSubjectToTeacher: () -> Unit,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier.height(16.dp))

        StudentManagementContainer(
            onAddStudentClick = onAddStudentClick,
            onSearchStudentClick = onSearchStudentClick
        )
        Spacer(Modifier.height(16.dp))

        StudentAllocationContainer(
            onAssignStudentToSemesterClick = onAssignStudentToSemesterClick,
            onRemoveStudentFromSemesterClick = onRemoveStudentFromSemesterClick,
            onAssignStudentToDivisionClick = onAssignStudentToDivisionClick,
            onModifyStudentDivisionClick = onModifyStudentDivisionClick,
            onAssignStudentToBatchClick = onAssignStudentToBatchClick,
            onModifyStudentBatchClick = onModifyStudentBatchClick
        )

        Spacer(Modifier.height(16.dp))

        StaffManagementContainer(
            onAddStaffClick = onAddStaffClick,
            onSearchStaffClick = onSearchStaffClick
        )

        Spacer(Modifier.height(16.dp))

        StaffSubjectAllocationContainer(
            onAssignSubjectToTeacher = onAssignSubjectToTeacher,
            onUnassignSubjectToTeacher = onUnassignSubjectToTeacher
        )

        Spacer(Modifier.height(16.dp))

    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun UserDashboardScreenPreview() {
    PreviewWrapper {
        UserDashboardScreen({}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {})
    }
}