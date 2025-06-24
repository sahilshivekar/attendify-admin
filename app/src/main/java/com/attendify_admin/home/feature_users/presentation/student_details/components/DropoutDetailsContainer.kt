package com.attendify_admin.home.feature_users.presentation.student_details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.attendify_admin.common.presentation.UiConstants
import com.attendify_admin.home.feature_users.presentation.student_details.StudentDetailsState

@Composable
fun DropoutDetailsContainer(modifier: Modifier = Modifier, state: StudentDetailsState) {
    if (state.dropoutDetails.isNotEmpty()) {
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Dropout Years",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                state.dropoutDetails.forEach { dropout ->
                    Text(
                        text = "${dropout.academicStartYear}-${dropout.academicEndYear}",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }

}