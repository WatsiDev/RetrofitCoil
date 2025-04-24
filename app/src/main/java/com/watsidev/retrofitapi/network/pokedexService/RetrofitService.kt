package com.watsidev.retrofitapi.network.pokedexService

import com.watsidev.retrofitapi.network.pokemonModel.PokemonResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://pokeapi.co/api/v2/"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface PokedexApiService{
    @GET("pokemon/{name}")
    suspend fun getPokemon(
        @Path("name") name: String
    ): PokemonResponse
}

object PokedexApi{
    val retrofitService: PokedexApiService by lazy {
        retrofit.create(PokedexApiService::class.java)
    }
}