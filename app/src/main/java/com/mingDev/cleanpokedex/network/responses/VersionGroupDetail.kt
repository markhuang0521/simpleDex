package com.mingDev.cleanpokedex.network.responses

data class VersionGroupDetail(
    val level_learned_at: Int,
    val move_learn_method: com.mingDev.cleanpokedex.network.responses.MoveLearnMethod,
    val version_group: com.mingDev.cleanpokedex.network.responses.VersionGroup
)