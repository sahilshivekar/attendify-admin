package com.presencify_admin.home.feature_academics.presentation.academics_dashboard

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
import androidx.navigation.NavHostController
import com.presencify_admin.home.feature_academics.navigation.AcademicsDestination.ManageBatch
import com.presencify_admin.home.feature_academics.navigation.AcademicsDestination.ManageBranch
import com.presencify_admin.home.feature_academics.navigation.AcademicsDestination.ManageCourse
import com.presencify_admin.home.feature_academics.navigation.AcademicsDestination.ManageDivision
import com.presencify_admin.home.feature_academics.navigation.AcademicsDestination.ManageScheme
import com.presencify_admin.home.feature_academics.navigation.AcademicsDestination.ManageSemester
import com.presencify_admin.home.feature_academics.navigation.AcademicsDestination.ManageUniversity
import com.presencify_admin.home.feature_academics.presentation.academics_dashboard.components.AcademicsGroupingContainer
import com.presencify_admin.home.feature_academics.presentation.academics_dashboard.components.CurriculumStructureContainer
import com.presencify_admin.ui.theme.PresencifyAdminTheme

@Composable
fun AcademicsDashboardRoot(
    navController: NavHostController,
) {

    AcademicsDashboardScreen(
        onAction = { action ->
            when (action) {
                AcademicsDashboardAction.OnManageBranchClick -> navController.navigate(ManageBranch.route)
                AcademicsDashboardAction.OnManageCourseClick -> navController.navigate(ManageCourse.route)
                AcademicsDashboardAction.OnManageSchemeClick -> navController.navigate(ManageScheme.route)
                AcademicsDashboardAction.OnManageUniversityClick -> navController.navigate(
                    ManageUniversity.route
                )

                AcademicsDashboardAction.OnManageSemesterClick -> navController.navigate(
                    ManageSemester.route
                )

                AcademicsDashboardAction.OnManageDivisionClick -> navController.navigate(
                    ManageDivision.route
                )

                AcademicsDashboardAction.OnManageBatchClick -> navController.navigate(ManageBatch.route)
            }
        }
    )
}

@Composable
fun AcademicsDashboardScreen(
    onAction: (AcademicsDashboardAction) -> Unit,
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

        CurriculumStructureContainer(
            onAction = onAction
        )

        Spacer(Modifier.height(16.dp))

        AcademicsGroupingContainer(
            onAction = onAction
        )

        Spacer(Modifier.height(16.dp))
    }
}

@Preview
@Composable
private fun Preview() {
    PresencifyAdminTheme {
        AcademicsDashboardScreen(
            onAction = {}
        )
    }
}