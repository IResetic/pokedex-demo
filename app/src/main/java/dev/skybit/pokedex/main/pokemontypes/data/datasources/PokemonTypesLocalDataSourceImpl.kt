package dev.skybit.pokedex.main.pokemontypes.data.datasources

import dev.skybit.pokedex.main.pokemontypes.data.local.dao.PokemonTypeDao
import dev.skybit.pokedex.main.pokemontypes.data.local.model.PokemonTypeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonTypesLocalDataSourceImpl @Inject constructor(
    private val pokemonTypeDao: PokemonTypeDao
) : PokemonTypesLocalDataSource {
    override suspend fun insertOrUpdatePokemonType(types: List<PokemonTypeEntity>) {
        pokemonTypeDao.insertOrUpdatePokemonTypes(types)
    }

    override suspend fun getPokemonTypes(): List<PokemonTypeEntity> {
        return pokemonTypeDao.getAllPokemonTypes()
    }

    override suspend fun getPokemonTypesFlow(): Flow<List<PokemonTypeEntity>> {
        return pokemonTypeDao.getPokemonTypesFlow()
    }
}
