package dev.skybit.pokedex.main.pokemon.data.datasources

import dev.skybit.pokedex.main.pokemon.data.local.dao.PokemonDetailsDao
import dev.skybit.pokedex.main.pokemon.data.local.model.PokemonDetailsEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonDetailsLocalDataSourceImpl @Inject constructor(
    private val pokemonDetailsDao: PokemonDetailsDao
) : PokemonDetailsLocalDataSource {

    override suspend fun insertOrUpdatePokemonDetails(pokemonDetails: PokemonDetailsEntity) {
        return pokemonDetailsDao.insertOrUpdatePokemonDetails(pokemonDetails)
    }

    override fun getPokemonDetails(pokemonId: Int): Flow<PokemonDetailsEntity?> {
        return pokemonDetailsDao.getPokemonDetails(pokemonId)
    }
}
