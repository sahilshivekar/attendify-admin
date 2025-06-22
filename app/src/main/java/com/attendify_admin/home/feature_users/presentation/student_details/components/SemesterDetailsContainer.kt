package com.attendify_admin.home.feature_users.presentation.student_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.attendify_admin.common.presentation.UiConstants
import com.attendify_admin.common.utils.DateTimeUtil
import com.attendify_admin.home.feature_users.presentation.student_details.StudentDetailsState

@Composable
fun SemesterDetailsContainer(modifier: Modifier = Modifier, state: StudentDetailsState) {

    state.semesters?.let {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .widthIn(max = UiConstants.MAX_WIDTH)
                .padding(vertical = 16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Past Semesters Details",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                )
            }
            state.semesters.forEach { semester ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Semester ${semester.semesterNumber}",
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
                    )
                    Text(
                        text = "${semester.academicStartYear}-${
                            semester.academicEndYear.toString().takeLast(2)
                        }",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = .8f)
                    )
                }
                Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .height(IntrinsicSize.Min)
                ) {
                    //semester line
                    Box(
                        modifier = Modifier
                            .width(5.dp)
                            .fillMaxHeight()
                            .padding(top = 4.dp, bottom = 20.dp)
                            .background(
                                color = Color.Blue,
                                shape = MaterialTheme.shapes.medium
                            )
                    )
                    //semester end date start date column
                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start,
                    ) {
                        Row(
                            verticalAlignment = Alignment.Top,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp)
                        ) {
                            Text(
                                text = "Start Date",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = ":",
                                modifier = Modifier.padding(horizontal = 4.dp),
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = DateTimeUtil.getDateInDDMMYYYYFromYYYYMMDD(semester.startDate),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }


                        // past divisions
                        var pastDivisions =
                            state.studentDivisions?.filter { studentDivision ->
                                studentDivision.division.semesterId == semester.id
                            }

                        pastDivisions = pastDivisions?.sortedBy { studentDivision ->
                            DateTimeUtil.getDateInDDMMYYYYFromYYYYMMDD(studentDivision.startDate)
                        }
                        pastDivisions?.forEach { studentDivision ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 4.dp, start = 32.dp, end = 16.dp),
                                verticalAlignment = Alignment.Top,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Division ${studentDivision.division.divisionCode}",
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        fontWeight = FontWeight.Medium
                                    )
                                )
                            }
                            Row(
                                verticalAlignment = Alignment.Top,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .padding(start = 32.dp)
                                    .height(IntrinsicSize.Min)
                            ) {
                                //division line
                                Box(
                                    modifier = Modifier
                                        .width(5.dp)
                                        .fillMaxHeight()
                                        .padding(vertical = 4.dp)
                                        .background(
                                            color = Color.Green,
                                            shape = MaterialTheme.shapes.medium
                                        )
                                )
                                //division from till column
                                Column(
                                    verticalArrangement = Arrangement.Top,
                                    horizontalAlignment = Alignment.Start,
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.Top,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 8.dp)
                                    ) {
                                        Text(
                                            text = "From",
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                        Text(
                                            text = "",
                                            modifier = Modifier.padding(horizontal = 4.dp),
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                        Text(
                                            text = DateTimeUtil.getDateInDDMMYYYYFromYYYYMMDD(
                                                studentDivision.startDate
                                            ),
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                    }


                                    var pastBatches =
                                        state.studentBatches?.filter { studentBatch ->
                                            studentBatch.batch.divisionId == studentDivision.division.id
                                        }
                                    pastBatches = pastBatches?.sortedBy { studentBatch ->
                                        DateTimeUtil.getDateInDDMMYYYYFromYYYYMMDD(studentBatch.startDate)
                                    }
                                    pastBatches?.forEach { studentBatch ->

                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(
                                                    top = 4.dp,
                                                    start = 32.dp,
                                                    end = 16.dp
                                                ),
                                            verticalAlignment = Alignment.Top,
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text(
                                                text = "Batch ${studentBatch.batch.batchCode}",
                                                style = MaterialTheme.typography.bodyLarge.copy(
                                                    fontWeight = FontWeight.Medium
                                                )
                                            )
                                        }
                                        Row(
                                            verticalAlignment = Alignment.Top,
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            modifier = Modifier
                                                .padding(start = 32.dp)
                                                .height(IntrinsicSize.Min)
                                        ) {
                                            //division line
                                            Box(
                                                modifier = Modifier
                                                    .width(5.dp)
                                                    .fillMaxHeight()
                                                    .padding(vertical = 4.dp)
                                                    .background(
                                                        color = Color.Yellow,
                                                        shape = MaterialTheme.shapes.medium
                                                    )
                                            )
                                            //division from till column
                                            Column(
                                                verticalArrangement = Arrangement.Top,
                                                horizontalAlignment = Alignment.Start,
                                            ) {
                                                Row(
                                                    verticalAlignment = Alignment.Top,
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(horizontal = 8.dp)
                                                ) {
                                                    Text(
                                                        text = "From",
                                                        style = MaterialTheme.typography.bodyMedium
                                                    )
                                                    Text(
                                                        text = "",
                                                        modifier = Modifier.padding(
                                                            horizontal = 4.dp
                                                        ),
                                                        style = MaterialTheme.typography.bodyMedium
                                                    )
                                                    Text(
                                                        text = DateTimeUtil.getDateInDDMMYYYYFromYYYYMMDD(studentBatch.startDate),
                                                        style = MaterialTheme.typography.bodyMedium
                                                    )
                                                }
                                                Row(
                                                    verticalAlignment = Alignment.Top,
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(horizontal = 8.dp)
                                                        .padding(top = 4.dp)
                                                ) {
                                                    Text(
                                                        text = "till",
                                                        style = MaterialTheme.typography.bodyMedium
                                                    )
                                                    Text(
                                                        text = "",
                                                        modifier = Modifier.padding(
                                                            horizontal = 4.dp
                                                        ),
                                                        style = MaterialTheme.typography.bodyMedium
                                                    )
                                                    Text(
                                                        text = if (studentBatch.endDate != null) DateTimeUtil.getDateInDDMMYYYYFromYYYYMMDD(
                                                            studentBatch.endDate
                                                        ) else
                                                            "Today",
                                                        style = MaterialTheme.typography.bodyMedium
                                                    )
                                                }

                                            }
                                        }
                                    }

                                    Row(
                                        verticalAlignment = Alignment.Top,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 8.dp)
                                            .padding(top = 4.dp)
                                    ) {
                                        Text(
                                            text = "till",
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                        Text(
                                            text = "",
                                            modifier = Modifier.padding(horizontal = 4.dp),
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                        Text(
                                            text = if (studentDivision.endDate != null) DateTimeUtil.getDateInDDMMYYYYFromYYYYMMDD(
                                                studentDivision.endDate
                                            ) else
                                                "Today",
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                    }
                                }


                            }
                        }

                        // semester end date
                        Row(
                            verticalAlignment = Alignment.Top,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp)
                                .padding(top = 4.dp, bottom = 16.dp)
                        ) {
                            Text(
                                text = "End Date",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = ":",
                                modifier = Modifier.padding(horizontal = 4.dp),
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = DateTimeUtil.getDateInDDMMYYYYFromYYYYMMDD(semester.endDate),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }


                    }

                }
            }

        }
    }
}


