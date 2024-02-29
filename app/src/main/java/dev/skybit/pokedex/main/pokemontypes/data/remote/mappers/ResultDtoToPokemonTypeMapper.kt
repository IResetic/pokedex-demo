package dev.skybit.pokedex.main.pokemontypes.data.remote.mappers

import dev.skybit.pokedex.main.core.data.remote.model.ResultDto
import dev.skybit.pokedex.main.pokemontypes.domain.model.PokemonType
import javax.inject.Inject

class ResultDtoToPokemonTypeMapper @Inject constructor() {
    operator fun invoke(result: ResultDto): PokemonType {
        return PokemonType(
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
