package com.attendify_admin.home.feature_academics.presentation.manage_university.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.attendify_admin.common.domain.model.University

@Composable
fun UniversityListItem(
    university: University,
    onClick: (University) -> Unit,
    modifier: Modifier = Modifier
) {
    ListItem(
        headlineContent = { Text(university.name) },
        supportingContent = { Text(university.abbreviation) },
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .clickable { onClick(university) },
        colors = ListItemDefaults.colors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    )
}
