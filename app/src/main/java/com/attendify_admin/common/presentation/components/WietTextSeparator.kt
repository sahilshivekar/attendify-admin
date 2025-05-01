package com.attendify_admin.common.presentation.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.attendify_admin.R

@Composable
fun AttendifyTextDivider(
    modifier: Modifier = Modifier
) {
    Icon(
        painter = painterResource(id = R.drawable.baseline_circle_24),
        contentDescription = null,
        modifier = modifier.padding(horizontal = 5.dp).height(4.dp).width(4.dp)
    )
}