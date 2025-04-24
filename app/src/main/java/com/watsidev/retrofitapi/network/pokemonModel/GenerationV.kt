package com.watsidev.retrofitapi.network.pokemonModel

import kotlinx.serialization.SerialName

data class GenerationV(
    @SerialName("black-white")
    val blackWhite: BlackWhite
)