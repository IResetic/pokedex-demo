package dev.skybit.pokedex.main.pokemontypes.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonTypeResponse(
    @field:Json(name = "name") val typeName: String,
    @field:Json(name = "url") val typeUrl: String
)
