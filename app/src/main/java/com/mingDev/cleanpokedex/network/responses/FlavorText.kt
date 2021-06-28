package com.mingDev.cleanpokedex.network.responses

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class FlavorText(
    val flavor_text: String,
    val language: Language,
    val version: Version
)