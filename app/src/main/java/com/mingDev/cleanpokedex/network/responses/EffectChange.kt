package com.mingDev.cleanpokedex.network.responses

data class EffectChange(
    val effect_entries: List<EffectEntry>,
    val version_group: VersionGroup
)