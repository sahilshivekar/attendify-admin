package com.presencify_admin.home.feature_users.presentation.unassign_subject_to_teacher

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
import com.presencify_admin.home.feature_users.presentation.unassign_subject_to_teacher.component.CourseCard
import com.presencify_admin.home.feature_users.presentation.unassign_subject_to_teacher.component.StaffCard

@Composable
fun UnassignSubjectTeacherScreen(
    modifier: Modifier = Modifier,
    state: UnassignSubjectTeacherState,
    onEvent: (UnassignSubjectTeacherEvent) -> Unit,
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
        Text(
            text = "Assigned courses",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        if (state.staffId == null) {
            Text(
                text = "Select staff member to load there assigned courses",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }
        LazyColumn {
            items(
                items = state.courses,
                key = {
                    it.courseId
                }
            ) { courseData ->
                CourseCard(
                    courseData = courseData,
                    onUnassignClick = {
                        onEvent(UnassignSubjectTeacherEvent.UnassignCourse(courseData.teacherCourseId))
                    }
                )
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}