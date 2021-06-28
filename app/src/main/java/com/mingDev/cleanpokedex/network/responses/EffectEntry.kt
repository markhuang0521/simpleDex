package com.mingDev.cleanpokedex.network.responses

data class EffectEntry(
    val effect: String,
    val language: Language,
    val short_effect: String
)