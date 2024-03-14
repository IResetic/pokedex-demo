package dev.skybit.pokedex.main.pokemontypes.data.datasources

import dev.skybit.pokedex.main.core.data.remote.model.PagedResponse
import dev.skybit.pokedex.main.core.data.remote.model.fakePokemonTypesPagedResponse
import retrofit2.Response

class FakePokemonTypesRemoteDataSource : PokemonTypesRemoteDataSource {
    var responses: List<Response<PagedResponse>> = listOf(Response.success(fakePokemonTypesPagedResponse))
    var currentResponseIndex = 0

    override suspend fun getPokemonTypes(offset: Int): Response<PagedResponse> {
        return if (currentResponseIndex < responses.size) {
            responses[currentResponseIndex++]
        } else {
            Response.success(PagedResponse(count = 0, next = null, previous = null, results = listOf()))
        }
    }
}
