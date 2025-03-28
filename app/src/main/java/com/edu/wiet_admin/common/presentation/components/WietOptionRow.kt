package com.edu.wiet_admin.common.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.edu.wiet_admin.R

@Composable
fun WietOptionRow(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    showDivider: Boolean = true, // to avoid the border below it
    optionText: String,
    isLoading: Boolean = false,
    isRisky: Boolean = false,
    @DrawableRes iconId: Int? = null
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(if(iconId != null) MaterialTheme.shapes.small else RectangleShape)
            .background(if(iconId != null) MaterialTheme.colorScheme.surface else Color.Transparent)
            .clickable { onClick() },
//            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {

            iconId?.let {
                Box(
                    modifier = Modifier.size(50.dp).background(if(isRisky) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary, shape = MaterialTheme.shapes.small),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        painter = painterResource(id = iconId),
                        contentDescription = null,
                        tint = if (isRisky) MaterialTheme.colorScheme.onError else MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(24.dp)
//                            .background(MaterialTheme.colorScheme.primary)
                    )
                }
                Spacer(
                    modifier = Modifier.width(8.dp)
                )
            }
            Text(
                text = optionText,
                style = MaterialTheme.typography.bodyMedium,
                color = if (isRisky && iconId == null) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(end = 8.dp),
            )
        }
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(24.dp),
                color = if (isRisky) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary,
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