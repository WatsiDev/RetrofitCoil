package com.watsidev.retrofitapi.network.pokemonModel

import kotlinx.serialization.SerialName

data class GenerationIii(
    val emerald: Emerald,
    @SerialName("firered-leafgreen")
    val fireredLeafgreen: FireredLeafgreen,
    @SerialName("ruby-sapphire")
    val rubySapphire: RubySapphire,
)