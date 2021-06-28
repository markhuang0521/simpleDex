package com.mingDev.cleanpokedex.network.responses

data class GameIndice(
    val game_index: Int,
    val version: com.mingDev.cleanpokedex.network.responses.Version
)