package dev.skybit.pokedex.main.typedetails.data.remote

import dev.skybit.pokedex.main.typedetails.data.remote.model.PokemonTypeDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonTypeDetailsApi {

    @GET("type/{typeId}")
    suspend fun getPokemonTypeDetails(@Path("typeId") typeId: Int): Response<PokemonTypeDetailsResponse>
}
