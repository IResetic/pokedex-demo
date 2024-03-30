package dev.skybit.pokedex.main.pokemon.data.datasources

import dev.skybit.pokedex.main.pokemon.data.remote.model.PokemonDetailsResponse
import retrofit2.Response

interface PokemonDetailsRemoteDataSource {
    suspend fun getPokemonDetails(pokemonId: Int): Response<PokemonDetailsResponse>
}
