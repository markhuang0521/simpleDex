package com.mingDev.cleanpokedex.network.responses

data class Normal(
    val use_after: List<UseAfter>,
    val use_before: Any
)