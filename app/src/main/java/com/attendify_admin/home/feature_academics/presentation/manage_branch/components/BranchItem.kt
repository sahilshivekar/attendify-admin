package com.attendify_admin.home.feature_academics.presentation.manage_branch.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.attendify_admin.common.domain.model.Branch

@Composable
fun BranchListItem(
    branch: Branch,
    onClick: (Branch) -> Unit,
    modifier: Modifier = Modifier
) {
    ListItem(
        headlineContent = { Text(branch.name) },
        supportingContent = { Text(branch.abbreviation) },
        modifier = modifier.clip(MaterialTheme.shapes.medium).clickable { onClick(branch) },
        colors = ListItemDefaults.colors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    )
}