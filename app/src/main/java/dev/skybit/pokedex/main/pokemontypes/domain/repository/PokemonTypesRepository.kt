package dev.skybit.pokedex.main.pokemontypes.domain.repository

import dev.skybit.pokedex.main.core.utils.Resource
import dev.skybit.pokedex.main.pokemontypes.domain.model.PokemonType

interface PokemonTypesRepository {
    suspend fun populatePokemonTypes()

    suspend fun getPokemonTypes(): Resource<List<PokemonType>>

    suspend fun getPokemonTypeDetails(typeName: String): PokemonType
}
