package com.watsidev.retrofitapi.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.watsidev.retrofitapi.network.pokemonModel.PokemonResponse

@Composable
fun DetailScreen(
    pokemon: PokemonResponse?,
    idPokemon: String,
    onBackClick: () -> Unit,
    onLoadPokemonById: (String) -> Unit,
    modifier: Modifier = Modifier
){
    LaunchedEffect(idPokemon) {
        onLoadPokemonById(idPokemon)
    }
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Text("Detalle del pokemon: $idPokemon")
        Text(pokemon?.name ?: "No encontrado")
        Text("Altura: ${pokemon?.height ?: "No disponible"}")
        Text("Peso: ${pokemon?.weight ?: "No disponible"}")
        Text("Regresar", modifier.clickable { onBackClick() })
    }
}