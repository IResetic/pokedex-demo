package dev.skybit.pokedex.main.typedetails.data.datasources

import dev.skybit.pokedex.main.typedetails.data.remote.model.PokemonTypeDetailsResponse
import retrofit2.Response

interface PokemonTypeDetailsRemoteDataSource {
    suspend fun getPokemonTypeDetails(typeId: Int): Response<PokemonTypeDetailsResponse>
}
