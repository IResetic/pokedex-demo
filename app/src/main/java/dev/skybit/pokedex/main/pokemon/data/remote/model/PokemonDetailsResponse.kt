package dev.skybit.pokedex.main.pokemon.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dev.skybit.pokedex.main.core.data.remote.model.ResultDto

@JsonClass(generateAdapter = true)
data class PokemonDetailsResponse(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String?,
    @field:Json(name = "weight") val weight: Int?,
    @field:Json(name = "height") val height: Int?,
    @field:Json(name = "stats") val baseStats: List<PokemonBasicStatsDto>?,
    @field:Json(name = "types") val types: List<PokemonTypeDto>?
)

@JsonClass(generateAdapter = true)
data class PokemonBasicStatsDto(
    @field:Json(name = "base_stat") val baseStat: Int,
    @field:Json(name = "effort") val effort: Int,
    @field:Json(name = "stat") val stat: ResultDto
)

@JsonClass(generateAdapter = true)
data class PokemonTypeDto(
    @field:Json(name = "slot") val slot: Int,
    @field:Json(name = "type") val type: ResultDto
)
