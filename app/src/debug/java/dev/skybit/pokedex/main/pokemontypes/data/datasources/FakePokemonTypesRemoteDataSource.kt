package dev.skybit.pokedex.main.pokemontypes.data.datasources

import dev.skybit.pokedex.main.core.data.remote.model.fakePokemonTypesPagedResponse
import dev.skybit.pokedex.main.core.data.remote.model.PagedResponse
import retrofit2.Response

class FakePokemonTypesRemoteDataSource : PokemonTypesRemoteDataSource {
    var fakePokemonTypes = Response.success(fakePokemonTypesPagedResponse)

    override suspend fun getPokemonTypes(offset: Int): Response<PagedResponse> {
        return fakePokemonTypes
    }
}
