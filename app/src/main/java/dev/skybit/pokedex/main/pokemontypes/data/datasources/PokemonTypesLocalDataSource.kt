package dev.skybit.pokedex.main.pokemontypes.data.datasources

import dev.skybit.pokedex.main.pokemontypes.data.local.model.PokemonTypeEntity
import kotlinx.coroutines.flow.Flow

interface PokemonTypesLocalDataSource {
    suspend fun insertOrUpdatePokemonType(types: List<PokemonTypeEntity>)

    suspend fun getPokemonTypes(): List<PokemonTypeEntity>

    suspend fun getPokemonTypesFlow(): Flow<List<PokemonTypeEntity>>
}
