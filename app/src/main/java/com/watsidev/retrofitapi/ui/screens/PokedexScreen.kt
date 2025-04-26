package com.watsidev.retrofitapi.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.watsidev.retrofitapi.R
import com.watsidev.retrofitapi.network.pokemonModel.PokemonResponse
import com.watsidev.retrofitapi.ui.common.LottieLoadAnimation

@Composable
fun PokedexScreen(
    isLoading: Boolean,
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
                    onClick = {
                        if (inputValue.isNotEmpty()) {
                            onSearchPokemon(inputValue)
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                }
            },
            singleLine = true,
            maxLines = 1,
            modifier = Modifier
                .padding(top = 52.dp)
                .clip(RoundedCornerShape(100))
                .fillMaxWidth(),
        )
        if (isLoading) {
            LottieLoadAnimation()
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(listPokemon) { pokemon ->

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        PokemonCard(
                            pokemon = pokemon,
                            onDetailNavigate = { onDetailNavigate(pokemon.id.toString()) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PokemonCard(
    pokemon: PokemonResponse?,
    onDetailNavigate: (String) -> Unit
) {
    Box(
        modifier = Modifier
//            .fillMaxWidth()
            .height(125.dp),
        contentAlignment = Alignment.BottomStart
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .fillMaxWidth()
                .background(Color.LightGray)
                .height(100.dp)
                .clickable { onDetailNavigate(pokemon?.id.toString()) },
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 125.dp)
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    pokemon!!.id.toString()
                )
                Text(
                    pokemon.name
                )
            }
            LazyColumn (
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                pokemon?.let { items(it.types)  { type ->
                    Text(type.type.name)
                } }
            }
        }
        AsyncImage(
//            painterResource(R.drawable._94gengarsprite),
            model = pokemon!!.sprites.other.home.front_default,
            contentDescription = pokemon.name,
            modifier = Modifier
                .size(175.dp)
                .offset(x = (-25).dp, y = (-7).dp)
        )
    }
}