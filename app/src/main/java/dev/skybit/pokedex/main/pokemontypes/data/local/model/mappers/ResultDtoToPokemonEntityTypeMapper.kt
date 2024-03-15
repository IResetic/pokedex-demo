package dev.skybit.pokedex.main.pokemontypes.data.local.model.mappers

import dev.skybit.pokedex.main.core.data.remote.model.ResultDto
import dev.skybit.pokedex.main.pokemontypes.data.local.model.PokemonTypeEntity
import javax.inject.Inject

class ResultDtoToPokemonEntityTypeMapper @Inject constructor() {
    operator fun invoke(result: ResultDto): PokemonTypeEntity {
        return PokemonTypeEntity(
            id = getPokemonTypeId(result.url),
            name = result.name
        )
    }

    private fun getPokemonTypeId(url: String): Int {
        val segments = url.trimEnd('/').split("/")
        val id = segments.lastOrNull() ?: "-1"
        return id.toInt()
    }
}
