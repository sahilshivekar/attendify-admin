package com.edu.wiet_admin.common.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.edu.wiet_admin.common.presentation.PreviewWrapper
import com.edu.wiet_admin.common.presentation.ScreenPreview

@ExperimentalMaterial3Api
@Composable
fun WietAlertDialog(
    modifier: Modifier = Modifier,
    dialogText: String,
    dismissButtonText: String = "Ok",
    confirmButtonText: String? = null,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Transparent),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BasicAlertDialog(
            onDismissRequest = onDismiss,
            modifier = modifier,
        ) {
            Column(
                modifier = Modifier
                    .widthIn(min = 300.dp, max = 400.dp)
                    .clip(shape = MaterialTheme.shapes.medium)
                    .wrapContentHeight()
                    .background(color = MaterialTheme.colorScheme.surface),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
            ) {

                Text(
                    text = dialogText,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium,
                )

                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp), // to match the height of the icons and continue the ux size
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    TextButton(
                        onClick = onDismiss
                    ) {
                        Text(
                            text = dismissButtonText,
                            style = MaterialTheme.typography.bodyMedium,
                            color = if(confirmButtonText != null) Color.Gray else MaterialTheme.colorScheme.primary
                        )
                    }
                    if (confirmButtonText != null) {
                        Button(
                            onClick = onConfirm,
                        ) {
                            Text(
                                text = confirmButtonText,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }

            }
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@ScreenPreview
@Composable
fun AlertDialogPreview() {
    PreviewWrapper {
        WietAlertDialog(
            dialogText = "Check your internet connection",
            onDismiss = {})
    }
}