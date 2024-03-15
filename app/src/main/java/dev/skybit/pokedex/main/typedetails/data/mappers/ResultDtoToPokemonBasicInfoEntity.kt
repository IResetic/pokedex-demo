package dev.skybit.pokedex.main.typedetails.data.mappers

import dev.skybit.pokedex.main.core.data.remote.model.ResultDto
import dev.skybit.pokedex.main.typedetails.data.local.model.PokemonBasicInfoEntity
import javax.inject.Inject

class ResultDtoToPokemonBasicInfoEntity @Inject constructor() {
    operator fun invoke(resultDto: ResultDto, pokemonTypeId: Int, pokemonTypeName: String): PokemonBasicInfoEntity {
        return PokemonBasicInfoEntity(
            id = getPokemonId(resultDto.url),
            name = resultDto.name,
            pokemonTypeId = pokemonTypeId,
            pokemonTypeName = pokemonTypeName
        )
    }

    private fun getPokemonId(url: String): Int {
        val segments = url.trimEnd('/').split("/")
        val id = segments.lastOrNull() ?: "-1"
        return id.toInt()
    }
}
