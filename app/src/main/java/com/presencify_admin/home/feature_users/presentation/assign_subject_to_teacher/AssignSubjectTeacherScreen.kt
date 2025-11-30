package com.presencify_admin.home.feature_users.presentation.assign_subject_to_teacher

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.presencify_admin.common.presentation.components.PresencifyButton
import com.presencify_admin.common.presentation.components.PresencifySearchBar
import com.presencify_admin.home.feature_users.presentation.assign_subject_to_teacher.component.CourseCard
import com.presencify_admin.home.feature_users.presentation.assign_subject_to_teacher.component.StaffCard

@Composable
fun AssignSubjectTeacherScreen(
    modifier: Modifier = Modifier,
    state: AssignSubjectTeacherState,
    onEvent: (AssignSubjectTeacherEvent) -> Unit,
    onSelectStaffMember: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
    ) {
        Text(
            text = "Select staff member",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        if (state.staffId == null) {
            PresencifyButton(
                onClick = onSelectStaffMember
            ) {

                Text("Select staff member")
            }
        } else {
            StaffCard(
                name = state.staffName ?: "",
                role = state.staffRole ?: "",
                highestQualification = state.staffHighesQualification,
                imageUrl = state.staffImageUrl
            )
        }
        Spacer(Modifier.height(16.dp))
        PresencifySearchBar(
            modifier = Modifier
                .padding(vertical = 16.dp),
            searchQuery = state.searchQuery,
            onSearchQueryValueChange = {
                onEvent(AssignSubjectTeacherEvent.SearchQueryChanged(it))
                onEvent(AssignSubjectTeacherEvent.FetchCourses)
            },
            onSearchIconClick = { onEvent(AssignSubjectTeacherEvent.FetchCourses) },
            showFilterIcon = false,
            searchBarPlaceholder = "Search courses",
        )
        LazyColumn {
            items(
                items = state.courses,
                key = {
                    it.courseId
                }
            ) { courseData ->
                CourseCard(
                    courseData = courseData,
                    onAssignClick = {
                        onEvent(AssignSubjectTeacherEvent.AssignCourse(courseData.courseId))
                    }
                )
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}
