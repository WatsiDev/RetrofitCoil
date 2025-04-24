package com.watsidev.retrofitapi.network.pokemonModel

data class Ability(
    val ability: AbilityX,
    val is_hidden: Boolean,
    val slot: Int
)