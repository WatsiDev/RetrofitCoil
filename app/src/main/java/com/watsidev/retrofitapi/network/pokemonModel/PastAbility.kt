package com.watsidev.retrofitapi.network.pokemonModel

data class PastAbility(
    val abilities: List<Ability>,
    val generation: Generation
)