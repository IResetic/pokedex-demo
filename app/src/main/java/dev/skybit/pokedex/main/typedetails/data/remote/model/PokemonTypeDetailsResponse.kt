package dev.skybit.pokedex.main.typedetails.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dev.skybit.pokedex.main.core.data.remote.model.ResultDto

@JsonClass(generateAdapter = true)
data class PokemonTypeDetailsResponse(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String?,
    @field:Json(name = "pokemon") val pokemons: List<ResultDto>?
)
