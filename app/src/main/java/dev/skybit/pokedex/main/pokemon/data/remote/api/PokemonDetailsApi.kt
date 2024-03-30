package dev.skybit.pokedex.main.pokemon.data.remote.api

import dev.skybit.pokedex.main.pokemon.data.remote.model.PokemonDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonDetailsApi {

    @GET("pokemon/{pokemonId}")
    suspend fun getPokemonDetails(@Path("pokemonId") pokemonId: Int): Response<PokemonDetailsResponse>
}
