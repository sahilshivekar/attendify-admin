package com.presencify_admin.common.data.remote.dto.response

import com.presencify_admin.common.domain.model.StudentBatch
import com.google.gson.annotations.SerializedName

data class StudentBatchDto(
    val id: Int,
    val endDate: String?,
    val startDate: String,
    val batchId: Int,
    @SerializedName("Batch")
    val batch: BatchDto?,
    val studentId: Int,
    @SerializedName("Student")
    val student: StudentDto?,
    val createdAt: String,
    val updatedAt: String
)

fun StudentBatchDto.toStudentBatch(): StudentBatch {
    return StudentBatch(
        id = id,
        endDate = endDate,
        startDate = startDate,
        batchId = batchId,
        batch = batch?.toBatch(),
        studentId = studentId,
        student = student?.toStudent()
    )
}
