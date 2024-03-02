package dev.skybit.pokedex.main.pokemontypes.data.datasources

import dev.skybit.pokedex.main.core.data.remote.model.PagedResponse
import retrofit2.Response

interface PokemonTypesRemoteDataSource {
    suspend fun getPokemonTypes(offset: Int): Response<PagedResponse>
}
