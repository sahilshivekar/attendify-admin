package com.attendify_admin.home.feature_users.presentation.staff_details.components

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
import com.attendify_admin.home.feature_users.presentation.staff_details.StaffDetailsState

@Composable
fun StaffDetailsContainer(modifier: Modifier = Modifier, state: StaffDetailsState) {
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .padding(top = 16.dp)
            .widthIn(max = UiConstants.MAX_WIDTH),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Full Name",
                modifier = Modifier.weight(.5f),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
            )
            Text(":", modifier = Modifier.weight(.05f))
            Text(
                text = "${state.staff?.firstName} ${state.staff?.middleName ?: ""} ${state.staff?.lastName}",
                modifier = Modifier.weight(.5f),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "Gender",
                modifier = Modifier.weight(.5f),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
            )
            Text(":", modifier = Modifier.weight(.05f))
            Text(
                state.staff?.gender ?: "",
                modifier = Modifier.weight(.5f),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "Email",
                modifier = Modifier.weight(.5f),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
            )
            Text(":", modifier = Modifier.weight(.05f))
            Text(
                state.staff?.email ?: "",
                modifier = Modifier.weight(.5f),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "Phone",
                modifier = Modifier.weight(.5f),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
            )
            Text(":", modifier = Modifier.weight(.05f))
            Text(
                state.staff?.phoneNumber ?: "",
                modifier = Modifier.weight(.5f),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "Qualification",
                modifier = Modifier.weight(.5f),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
            )
            Text(":", modifier = Modifier.weight(.05f))
            Text(
                state.staff?.highestQualification ?: "Not added",
                modifier = Modifier.weight(.5f),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "Role",
                modifier = Modifier.weight(.5f),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
            )
            Text(":", modifier = Modifier.weight(.05f))
            Text(
                state.staff?.role ?: "",
                modifier = Modifier.weight(.5f),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}