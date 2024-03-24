package dev.skybit.pokedex.main.typedetails.data.datasources

import dev.skybit.pokedex.main.typedetails.data.remote.model.PokemonTypeDetailsResponse
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

class FakePokemonTypeDetailsRemoteDataSource : PokemonTypeDetailsRemoteDataSource {
    var response: Response<PokemonTypeDetailsResponse>? = null
    var shouldReturnError: Boolean = false
    var errorCode = 404

    override suspend fun getPokemonTypeDetails(typeId: Int): Response<PokemonTypeDetailsResponse> {
        if (shouldReturnError) {
            return Response.error(errorCode, "Not Found".toResponseBody(null))
        }
        return response ?: throw IllegalStateException("Response not set for FakePokemonTypeDetailsRemoteDataSource")
    }
}
