package com.edu.wiet_admin.common.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.edu.wiet_admin.R

@Composable
fun WietOptionRow(
    onClick: () -> Unit,
    showDivider: Boolean = true, // to avoid the border below it
    optionText: String,
    isLoading: Boolean = false,
    isRisky: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onClick() }
            .padding(horizontal = 8.dp), // Make the row clickable
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = optionText,
            style = MaterialTheme.typography.bodyMedium,
            color = if(isRisky) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(vertical = 16.dp),
        )
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(24.dp),
                color = if(isRisky) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary,
                strokeWidth = 2.dp
            )
        }
    }
    if (showDivider) {
        HorizontalDivider(
            color = colorResource(R.color.divider),
            thickness = 1.dp,
        )
    }

}