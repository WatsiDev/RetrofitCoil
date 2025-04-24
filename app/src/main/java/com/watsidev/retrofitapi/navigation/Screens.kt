package com.watsidev.retrofitapi.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object Regions

@Serializable
data class Pokedex(
    val name: String,
)

@Serializable
data class Detail(
    val id: String
)