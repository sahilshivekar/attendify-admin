package com.presencify_admin.home.feature_academics.presentation.academics_dashboard.components

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
import com.presencify_admin.home.feature_academics.presentation.academics_dashboard.AcademicsDashboardAction

@Composable
fun CurriculumStructureContainer(
    onAction: (AcademicsDashboardAction) -> Unit
) {
    Column(
        modifier = Modifier
            .widthIn(max = UiConstants.MAX_WIDTH)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Curriculum Structure",
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
                    headlineContent = { Text("Manage Branch") },
                    leadingContent = {
                        Icon(
                            painter = painterResource(id = R.drawable.branch_24),
                            contentDescription = "Manage Branch",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    modifier = Modifier
                        .weight(.5f)
                        .height(65.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .clickable { onAction(AcademicsDashboardAction.OnManageBranchClick) }
                )
                ListItem(
                    headlineContent = { Text("Manage Course") },
                    leadingContent = {
                        Icon(
                            painter = painterResource(id = R.drawable.round_menu_book_24),
                            contentDescription = "Manage Course",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    modifier = Modifier
                        .weight(.5f)
                        .height(65.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .clickable { onAction(AcademicsDashboardAction.OnManageCourseClick) }
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ListItem(
                    headlineContent = { Text("Manage Scheme") },
                    leadingContent = {
                        Icon(
                            painter = painterResource(id = R.drawable.scheme_24),
                            contentDescription = "Manage Scheme",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    modifier = Modifier
                        .weight(.5f)
                        .height(65.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .clickable { onAction(AcademicsDashboardAction.OnManageSchemeClick) }
                )
                ListItem(
                    headlineContent = { Text("Manage University") },
                    leadingContent = {
                        Icon(
                            painter = painterResource(id = R.drawable.apartment_24),
                            contentDescription = "Manage University",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    modifier = Modifier
                        .weight(.5f)
                        .height(65.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .clickable { onAction(AcademicsDashboardAction.OnManageUniversityClick) }
                )
            }
        }
    }
}

@Preview
@Composable
fun CurriculumStructureContainerPreview() {
    PreviewWrapper {
        CurriculumStructureContainer(
            onAction = {}
        )
    }
}