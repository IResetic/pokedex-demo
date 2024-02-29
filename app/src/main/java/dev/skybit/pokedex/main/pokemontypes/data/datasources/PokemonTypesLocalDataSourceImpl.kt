package dev.skybit.pokedex.main.pokemontypes.data.datasources

import dev.skybit.pokedex.main.pokemontypes.data.local.dao.PokemonTypeDao
import dev.skybit.pokedex.main.pokemontypes.data.local.model.PokemonTypeEntity
import javax.inject.Inject

class PokemonTypesLocalDataSourceImpl @Inject constructor(
    private val pokemonTypeDao: PokemonTypeDao
) : PokemonTypesLocalDataSource {
    override suspend fun insertPokemonType(types: List<PokemonTypeEntity>) {
        pokemonTypeDao.insertPokemonTypes(types)
    }
}
