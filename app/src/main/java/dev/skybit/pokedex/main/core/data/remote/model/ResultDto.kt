package dev.skybit.pokedex.main.core.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResultDto(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "url") val url: String
)
