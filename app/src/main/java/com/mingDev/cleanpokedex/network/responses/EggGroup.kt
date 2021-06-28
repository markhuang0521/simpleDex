package com.mingDev.cleanpokedex.network.responses

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EggGroup(
    val name: String,
    val url: String
)