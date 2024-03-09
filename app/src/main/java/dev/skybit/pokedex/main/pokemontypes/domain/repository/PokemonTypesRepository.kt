package dev.skybit.pokedex.main.pokemontypes.domain.repository

import dev.skybit.pokedex.main.core.domain.model.PokemonType
import dev.skybit.pokedex.main.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface PokemonTypesRepository {
    suspend fun populatePokemonTypes(): Resource<Unit>

    suspend fun getPokemonTypeDetails(typeName: String): PokemonType

    suspend fun getPokemonTypes(): List<PokemonType>

    suspend fun getPokemonTypesFlow(): Flow<List<PokemonType>>

    suspend fun getPokemonTypeBasicIInfoById(pokemonTypeId: Int): PokemonType
}
