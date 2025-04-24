package com.watsidev.retrofitapi.network.pokemonModel

import kotlinx.serialization.SerialName

data class GenerationVii(
    val icons: Icons,
    @SerialName("ultra-sun-ultra-moon")
    val ultraSunUltraMoon: UltraSunUltraMoon
)