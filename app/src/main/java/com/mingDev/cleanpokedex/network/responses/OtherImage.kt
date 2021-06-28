package com.mingDev.cleanpokedex.network.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OtherImage(
    @Json(name = "dream_world") val dreamWorld: DreamWorld,
    @Json(name = "official-artwork") val officialArtwork: OfficialArtwork
)