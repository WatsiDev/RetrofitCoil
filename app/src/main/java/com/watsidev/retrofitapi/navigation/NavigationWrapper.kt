package com.watsidev.retrofitapi.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.watsidev.retrofitapi.data.RegionDataSource
import com.watsidev.retrofitapi.network.pokemonModel.PokemonResponse
import com.watsidev.retrofitapi.ui.screens.DetailScreen
import com.watsidev.retrofitapi.ui.screens.HomeScreen
import com.watsidev.retrofitapi.ui.screens.PokedexScreen
import com.watsidev.retrofitapi.ui.screens.RegionsScreen
import com.watsidev.retrofitapi.ui.viewmodel.PokedexViewModel

@Composable
fun navigationWrapper() {
    val navController = rememberNavController()
    Scaffold(
        topBar = { MyAppBar() }
    ) { contentPadding ->
        val viewModel: PokedexViewModel = viewModel()
        val uiState = viewModel.uiState.collectAsState()
        NavHost(
            modifier = Modifier.padding(),
            navController = navController,
            startDestination = (Home)
        ) {
            composable<Home> {
                HomeScreen(
                    pokemon = uiState.value.pokemonSearch,
                    inputValue = uiState.value.inputValue,
                    onChangeValue = {viewModel.onChangeValue(it)},
                    onSearchPokemon = { viewModel.searchPokemonByIdOrName(it) },
                    onNavigateClick = { navController.navigate(Regions) },
                    onDetailNavigate = { id -> navController.navigate(Detail(id = id)) },
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .padding(contentPadding)
                )
            }
            composable<Regions> {
                RegionsScreen(
                    listRegions = viewModel.regionList,
                    onNavigateClick = { name -> navController.navigate(Pokedex(name = name)) },
                    modifier = Modifier
                        .padding(contentPadding)
                )
            }
            composable<Pokedex> {
                val pokedex = it.toRoute<Pokedex>()
                PokedexScreen(
                    isLoading = uiState.value.isLoading,
                    listPokemon = uiState.value.pokemonList,
                    name = pokedex.name,
                    onDetailNavigate = { id -> navController.navigate(Detail(id = id)) },
                    inputValue = uiState.value.inputValue,
                    onValueChange = { viewModel.onChangeValue(it) },
                    onSearchPokemon = { viewModel.searchPokemonByIdOrName(it) },
                    onLoadPokemonByRegion = { viewModel.getPokemonByRegion(it) },
                    modifier = Modifier
                        .padding(contentPadding)
                )
            }
            composable<Detail> {
                val detail = it.toRoute<Detail>()
                DetailScreen(
                    pokemon = uiState.value.selectedPokemon,
                    idPokemon = detail.id,
                    onBackClick = { navController.popBackStack() },
                    onLoadPokemonById = { viewModel.getPokemonById(it) },
                    modifier = Modifier
                        .background(Color(0xFF65558F))
                        .padding(contentPadding)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppBar() {
    CenterAlignedTopAppBar(
        title = { Text("POKEDEX-MMO", fontSize = 24.sp) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = Color.White
        )
    )
}
