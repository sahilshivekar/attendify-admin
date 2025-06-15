package com.attendify_admin.common.data.remote.dto.response

import com.attendify_admin.common.domain.model.Batch
import com.google.gson.annotations.SerializedName

data class BatchDto(
    val id: Int,
    val batchCode: String,
    val divisionId: Int,
    @SerializedName("Division")
    val division: DivisionDto?,
    val createdAt: String,
    val updatedAt: String,
)

fun BatchDto.toBatch(): Batch {
    return Batch(
        id = id,
        batchCode = batchCode,
        divisionId = divisionId,
        division = division?.toDivision()
    )
}
