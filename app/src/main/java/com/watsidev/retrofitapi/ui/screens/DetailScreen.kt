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
import coil.compose.AsyncImage
import com.watsidev.retrofitapi.network.pokemonModel.PokemonResponse
import com.watsidev.retrofitapi.ui.common.LottieLoadAnimation

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
        if (pokemon == null) {
            LottieLoadAnimation()
        } else {
            AsyncImage(
                model = pokemon.sprites.other.home.front_default,
                contentDescription = pokemon.name
            )
            Text(pokemon.name)
            Text("Altura: ${pokemon.height}")
            Text("Peso: ${pokemon.weight}")
        }
        Text("Regresar", modifier.clickable { onBackClick() })
    }
}