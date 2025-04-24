package com.watsidev.retrofitapi.ui.viewmodel

import android.graphics.Region
import com.watsidev.retrofitapi.data.RegionDataSource
import com.watsidev.retrofitapi.network.pokemonModel.PokemonResponse

data class PokedexUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val inputValue: String = "",
    val userValue: String = "",
    val pokemonList: List<PokemonResponse> = emptyList(),
    val pokemonSearch: PokemonResponse? = null,
    val pokemonSearchList: PokemonResponse? = null,
    val selectedPokemon: PokemonResponse? = null,
)