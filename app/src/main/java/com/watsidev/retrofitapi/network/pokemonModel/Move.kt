package com.watsidev.retrofitapi.network.pokemonModel

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)