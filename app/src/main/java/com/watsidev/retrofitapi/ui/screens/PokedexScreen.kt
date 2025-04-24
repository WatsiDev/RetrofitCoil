package com.watsidev.retrofitapi.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.watsidev.retrofitapi.network.pokemonModel.PokemonResponse

@Composable
fun PokedexScreen(
    listPokemon: List<PokemonResponse>,
    name: String,
    onDetailNavigate: (String) -> Unit,
    inputValue: String,
    onValueChange: (String) -> Unit,
    onSearchPokemon: (String) -> Unit,
    onLoadPokemonByRegion: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(name) {
        onLoadPokemonByRegion(name)
    }
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        TextField(
            value = inputValue,
            onValueChange = { onValueChange(it) },
            label = { Text("Name or id...") },
            trailingIcon = {
                IconButton(
                    onClick = { onSearchPokemon(inputValue) }
                ) {
                    Icon(
                        imageVector = androidx.compose.material.icons.Icons.Default.Search,
                        contentDescription = "Search"
                    )
                }
            },
            singleLine = true,
            modifier = Modifier
                .padding(top = 52.dp)
                .clip(RoundedCornerShape(100))
                .fillMaxWidth(),
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(listPokemon) { pokemon ->
                 Row(
                     modifier = Modifier
                         .fillMaxWidth()
                         .clickable{ onDetailNavigate(pokemon.id.toString()) }
                 ) {
                    Text(pokemon.id.toString())
                    Text(pokemon.name)
                 }
            }
        }
    }
}