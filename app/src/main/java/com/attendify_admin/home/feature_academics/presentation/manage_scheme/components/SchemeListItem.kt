package com.attendify_admin.home.feature_academics.presentation.manage_scheme.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.attendify_admin.common.domain.model.Scheme

@Composable
fun SchemeListItem(
    scheme: Scheme,
    onClick: (Scheme) -> Unit,
    modifier: Modifier = Modifier
) = ListItem(
    headlineContent = { Text(scheme.name) },
    modifier = modifier
        .clip(MaterialTheme.shapes.medium)
        .clickable { onClick(scheme) },
    colors = ListItemDefaults.colors(
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    )
)
