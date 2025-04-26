package com.watsidev.retrofitapi.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.watsidev.retrofitapi.network.pokemonModel.PokemonResponse
import com.watsidev.retrofitapi.ui.viewmodel.PokedexViewModel

@Composable
fun HomeScreen(
    pokemon: PokemonResponse?,
    inputValue: String,
    onChangeValue: (String) -> Unit,
    onSearchPokemon: (String) -> Unit,
    onDetailNavigate: (String) -> Unit,
    onNavigateClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TextField(
            value = inputValue,
            onValueChange = { onChangeValue(it) },
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
            label = {
                    Text("Search Pokémon")
            },
            maxLines = 1,
            singleLine = true,
            modifier = Modifier
                .padding(top = 52.dp)
                .clip(RoundedCornerShape(100))
                .fillMaxWidth(),
        )
        AnimatedVisibility(pokemon != null) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                PokemonCard(
                    pokemon = pokemon,
                    onDetailNavigate = { onDetailNavigate(pokemon?.id.toString()) }
                )
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(top = 16.dp)
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .fillMaxWidth()
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(2) {
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(4) {
                Card(
                    modifier = Modifier
                        .clickable {
                            onNavigateClick()
                        }
                        .height(80.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    )
                ) {
                    Text("Pokédex", textAlign = TextAlign.Center)
                }
            }
        }
    }
}

