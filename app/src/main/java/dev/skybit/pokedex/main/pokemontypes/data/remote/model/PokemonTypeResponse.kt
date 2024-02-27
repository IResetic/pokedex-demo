package dev.skybit.pokedex.main.pokemontypes.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dev.skybit.pokedex.main.core.data.remote.model.ResultDto

@JsonClass(generateAdapter = true)
data class PokemonTypeResponse(
    @field:Json(name = "count") val count: Int,
    @field:Json(name = "next") val next: String?,
    @field:Json(name = "previous") val previous: String?,
    @field:Json(name = "results") val results: List<ResultDto>?
)
