package dev.skybit.pokedex.main.pokemontypes.data.remote

import dev.skybit.pokedex.main.core.data.remote.model.PagedResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonTypeApi {

    @GET("type")
    suspend fun getPokemonTypes(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PagedResponse
}
