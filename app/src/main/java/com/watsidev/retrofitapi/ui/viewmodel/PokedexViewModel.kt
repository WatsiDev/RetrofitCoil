package com.watsidev.retrofitapi.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.watsidev.retrofitapi.data.RegionDataSource
import com.watsidev.retrofitapi.network.pokedexService.PokedexApi
import com.watsidev.retrofitapi.network.pokemonModel.PokemonResponse
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokedexViewModel: ViewModel() {
    private val retrofit = PokedexApi.retrofitService
    private val _uiState = MutableStateFlow(PokedexUiState())
    val uiState: StateFlow<PokedexUiState> = _uiState.asStateFlow()

    /*
    * [regionList] -> Es la lista de regiones que se va a mostrar en la pantalla de regiones
    * [RegionDataSource.regionList] -> Esta lista esta definida en la clase RegionDataSource
     */
    val regionList = RegionDataSource.regionList

    /*
    * [value] -> Es el valor que se va a mostrar en el input
     */
    fun onChangeValue(value: String) {
        _uiState.value = _uiState.value.copy(
            inputValue = value
        )
    }

    /*
    * [idOrName] -> Es el id o el nombre del pokemon que se va a buscar en la API
    * [searchPokemonByIdOrName] -> Es la función que se encarga de buscar el pokemon en la API
    * se mostrara un resultado de solo un pokemon para los input de busqueda de las pantallas Home y Pokedex
    * */
    fun searchPokemonByIdOrName(idOrName: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                pokemonList = emptyList(),
                isLoading = true
            )

            try {
                val pokemon = retrofit.getPokemon(idOrName.lowercase()) // la API acepta id o nombre
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    pokemonSearch = pokemon,
                    inputValue = ""
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "No se encontró el Pokémon"
                )
            }
        }
    }

    /*
    * [name] -> Es el nombre de la región que se va a buscar
    */
    fun getPokemonByRegion(name: String){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                pokemonList = emptyList(),
                isLoading = true
            )

            try {
                val region = regionList.find { it.name.equals(name, ignoreCase = true) }
                    ?: throw IllegalArgumentException("Región no encontrada")

                val pokemons = mutableListOf<PokemonResponse>()

                for (id in region.idStart.toInt()..region.idEnd.toInt()) {
                    try {
                        val pokemon = retrofit.getPokemon(id.toString())
                        pokemons.add(pokemon)
                    } catch (e: Exception) {
                        // Puedes ignorar ciertos errores individuales si lo deseas
                        continue
                    }
                }

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    pokemonList = pokemons
                )

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Error cargando Pokémon"
                )
            }
        }
    }

    /*
    * [id] -> Es el id del pokemon que se va a buscar en la API y obtener toda su información
    * para la pantalla de detalle
     */
    fun getPokemonById(id: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                selectedPokemon = null,
                isLoading = true
            )

            try {
                val pokemon = retrofit.getPokemon(id)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    selectedPokemon = pokemon
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "No se encontró el Pokémon"
                )
            }
        }
    }
}