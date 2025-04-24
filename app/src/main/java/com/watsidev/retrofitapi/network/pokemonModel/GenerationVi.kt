package com.watsidev.retrofitapi.network.pokemonModel

import kotlinx.serialization.SerialName

data class GenerationVi(
    @SerialName("omegaruby-alphasapphire")
    val omegarubyAlphasapphire: OmegarubyAlphasapphire,
    @SerialName("x-y")
    val xy: XY
)