package dev.skybit.pokedex.main.pokemontypes.data.datasources

import dev.skybit.pokedex.main.core.data.PAGE_SIZE
import dev.skybit.pokedex.main.core.data.remote.model.PagedResponse
import dev.skybit.pokedex.main.pokemontypes.data.remote.PokemonTypeApi
import retrofit2.Response
import javax.inject.Inject

class PokemonTypesRemoteDataSourceImpl @Inject constructor(
    private val pokemonTypesApi: PokemonTypeApi
) : PokemonTypesRemoteDataSource {

    override suspend fun getPokemonTypes(offset: Int): Response<PagedResponse> {
        return pokemonTypesApi.getPokemonTypes(PAGE_SIZE, offset)
    }
}
