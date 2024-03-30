package dev.skybit.pokedex.main.pokemon.data.datasources

import dev.skybit.pokedex.main.pokemon.data.remote.api.PokemonDetailsApi
import dev.skybit.pokedex.main.pokemon.data.remote.model.PokemonDetailsResponse
import retrofit2.Response
import javax.inject.Inject

class PokemonDetailsRemoteDataSourceImpl @Inject constructor(
    private val pokemonDetailsApi: PokemonDetailsApi
) : PokemonDetailsRemoteDataSource {
    override suspend fun getPokemonDetails(pokemonId: Int): Response<PokemonDetailsResponse> {
        return pokemonDetailsApi.getPokemonDetails(pokemonId)
    }
}
