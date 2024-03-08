package dev.skybit.pokedex.main.pokemontypes.data.local.dao

import dev.skybit.pokedex.main.pokemontypes.data.local.model.PokemonTypeEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakePokemonTypeDao : PokemonTypeDao {
    var fakePokemonTypeEntities = mutableListOf<PokemonTypeEntity>()
    var isInserted = false

    override suspend fun insertOrUpdatePokemonTypes(pokemonTypes: List<PokemonTypeEntity>) {
        fakePokemonTypeEntities = pokemonTypes.toMutableList()
        isInserted = true
    }

    override fun getAllPokemonTypes(): List<PokemonTypeEntity> {
        return fakePokemonTypeEntities
    }

    override fun getPokemonTypesFlow(): Flow<List<PokemonTypeEntity>> {
        return flow { emit(fakePokemonTypeEntities) }
    }
}
