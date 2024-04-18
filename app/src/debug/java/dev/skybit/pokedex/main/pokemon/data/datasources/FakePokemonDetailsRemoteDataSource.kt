package dev.skybit.pokedex.main.pokemon.data.datasources

import dev.skybit.pokedex.main.pokemon.data.remote.model.PokemonDetailsResponse
import retrofit2.Response

class FakePokemonDetailsRemoteDataSource : PokemonDetailsRemoteDataSource {
    val fakePokemonDetailsResponseMap = mutableMapOf<Int, PokemonDetailsResponse>()
    private lateinit var response: Response<PokemonDetailsResponse>
    var isSuccess = true

    override suspend fun getPokemonDetails(pokemonId: Int): Response<PokemonDetailsResponse> {
        serResponse(pokemonId)
        return response
    }

    private fun serResponse(pokemonId: Int) {
        if (isSuccess) {
            response = Response.success(fakePokemonDetailsResponseMap[pokemonId]!!)
        } else {
            response = Response.error(404, null)
        }
    }
}
