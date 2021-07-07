package com.mingDev.cleanpokedex.network.responses

data class Chain(
    val evolution_details: List<EvolutionDetail?>,
    val evolves_to: List<EvolvesTo?>,
    val is_baby: Boolean,
    val species: Species
)