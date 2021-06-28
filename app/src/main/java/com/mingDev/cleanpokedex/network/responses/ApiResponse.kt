package com.mingDev.cleanpokedex.network.responses

import com.squareup.moshi.Json

data class ApiResponse(
    @field:Json(name = "count") val count: Int,
    @field:Json(name = "next") val next: String?,
    @field:Json(name = "previous") val previous: String?,
    @field:Json(name = "results") val results: List<ResultUrl>
)