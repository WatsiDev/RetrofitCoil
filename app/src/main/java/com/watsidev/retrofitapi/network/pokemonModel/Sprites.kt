package com.watsidev.retrofitapi.network.pokemonModel

data class Sprites(
    val back_default: Any,
    val back_female: Any,
    val back_shiny: Any,
    val back_shiny_female: Any,
    val front_default: String,
    val front_female: Any,
    val front_shiny: String,
    val front_shiny_female: Any,
    val other: Other,
    val versions: Versions
)