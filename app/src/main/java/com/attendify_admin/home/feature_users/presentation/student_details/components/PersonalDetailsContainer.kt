package com.attendify_admin.home.feature_users.presentation.student_details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.attendify_admin.common.presentation.UiConstants
import com.attendify_admin.home.feature_users.presentation.student_details.StudentDetailsState

@Composable
fun PersonalDetailsContainer(
    modifier: Modifier = Modifier,
    state: StudentDetailsState
) {
    //personal details card
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .widthIn(max = UiConstants.MAX_WIDTH)
            .padding(top = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Personal Details",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
        // full name
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Full Name",
                modifier = Modifier.weight(.5f),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
            )
            Text(
                text = ":",
                modifier = Modifier.weight(.05f),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
            )
            Text(
                text = "${state.student?.firstName} ${if (state.student?.middleName == null) "" else "${state.student.middleName} "}${state.student?.lastName}",
                modifier = Modifier.weight(.5f),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        // dob
        state.student?.dob?.let {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Date of Birth",
                    modifier = Modifier.weight(.5f),
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                )
                Text(
                    text = ":",
                    modifier = Modifier.weight(.05f),
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                )
                Text(
                    text = state.student.dob,
                    modifier = Modifier.weight(.5f),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        // gender
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Gender",
                modifier = Modifier.weight(.5f),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
            )
            Text(
                text = ":",
                modifier = Modifier.weight(.05f),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
            )
            Text(
                text = state.student?.gender ?: "",
                modifier = Modifier.weight(.5f),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}