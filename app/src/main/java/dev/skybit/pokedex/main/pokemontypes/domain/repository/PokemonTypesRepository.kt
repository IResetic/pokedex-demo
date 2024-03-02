package dev.skybit.pokedex.main.pokemontypes.domain.repository

import dev.skybit.pokedex.main.core.utils.Resource
import dev.skybit.pokedex.main.pokemontypes.domain.model.PokemonType

interface PokemonTypesRepository {
    suspend fun populatePokemonTypes(): Resource<Unit>

    suspend fun getPokemonTypeDetails(typeName: String): PokemonType
}
