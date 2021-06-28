package com.mingDev.cleanpokedex.network.responses

data class HeldItem(
    val item: com.mingDev.cleanpokedex.network.responses.Item,
    val version_details: List<com.mingDev.cleanpokedex.network.responses.VersionDetail>
)