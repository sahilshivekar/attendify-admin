package com.attendify_admin.common.presentation.components

import android.app.DatePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.attendify_admin.R
import java.time.LocalDate
import java.util.Calendar


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AttendifyDatePicker(
    onDateSelected: (LocalDate) -> Unit,
    onCancelClicked: () -> Unit,
    selectedLocalDate: LocalDate?
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val year = selectedLocalDate?.year ?: calendar.get(Calendar.YEAR)
    val month = selectedLocalDate?.monthValue?.minus(1) ?: calendar.get(Calendar.MONTH) // Month is 0-based
    val day = selectedLocalDate?.dayOfMonth ?: calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(
        context,
        R.style.CustomDatePickerDialog,
        { _, y, m, d ->
            val localDate = LocalDate.of(y, m + 1, d)
            onDateSelected(localDate)
        },
        year,
        month,
        day
    ).apply {
        setOnCancelListener {
            onCancelClicked()
        }
    }.show()
}