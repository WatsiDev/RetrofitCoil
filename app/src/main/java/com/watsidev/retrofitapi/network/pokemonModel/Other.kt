package com.watsidev.retrofitapi.network.pokemonModel

import kotlinx.serialization.SerialName

data class Other(
    val dream_world: DreamWorld,
    val home: Home,
    @SerialName("official-artwork")
    val officialArtwork: OfficialArtwork,
    val showdown: Showdown
)