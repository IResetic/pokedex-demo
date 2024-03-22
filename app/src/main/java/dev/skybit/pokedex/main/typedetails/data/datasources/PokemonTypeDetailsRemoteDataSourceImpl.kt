package dev.skybit.pokedex.main.typedetails.data.datasources

import dev.skybit.pokedex.main.typedetails.data.remote.api.PokemonTypeDetailsApi
import dev.skybit.pokedex.main.typedetails.data.remote.model.PokemonTypeDetailsResponse
import retrofit2.Response
import javax.inject.Inject

class PokemonTypeDetailsRemoteDataSourceImpl @Inject constructor(
    private val pokemonTypeDetailsApi: PokemonTypeDetailsApi
) : PokemonTypeDetailsRemoteDataSource {
    override suspend fun getPokemonTypeDetails(typeId: Int): Response<PokemonTypeDetailsResponse> {
        return pokemonTypeDetailsApi.getPokemonTypeDetails(typeId)
    }
}
