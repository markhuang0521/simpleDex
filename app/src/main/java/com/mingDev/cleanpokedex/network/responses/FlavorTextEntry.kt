package com.mingDev.cleanpokedex.network.responses

data class FlavorTextEntry(
    val flavor_text: String,
    val language: Language,
    val version_group: VersionGroup
)