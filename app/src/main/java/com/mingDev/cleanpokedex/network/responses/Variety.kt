package com.mingDev.cleanpokedex.network.responses

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Variety(
    val is_default: Boolean,
    val pokemon: ResultUrl
)