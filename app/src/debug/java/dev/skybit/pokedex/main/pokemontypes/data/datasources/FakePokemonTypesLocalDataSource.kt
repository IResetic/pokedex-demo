package dev.skybit.pokedex.main.pokemontypes.data.datasources

import dev.skybit.pokedex.main.pokemontypes.data.local.model.PokemonTypeEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakePokemonTypesLocalDataSource : PokemonTypesLocalDataSource {
    var fakePokemonTypeEntities = mutableListOf<PokemonTypeEntity>()
    var fakePokemonTypeMap = mutableMapOf<Int, PokemonTypeEntity>()
    var isInserted = false

    override suspend fun insertOrUpdatePokemonType(types: List<PokemonTypeEntity>) {
        fakePokemonTypeEntities.addAll(types)
        isInserted = true
    }

    override suspend fun getPokemonTypes(): List<PokemonTypeEntity> {
        return fakePokemonTypeEntities
    }

    override suspend fun getPokemonTypesFlow(): Flow<List<PokemonTypeEntity>> {
        return flow { emit(fakePokemonTypeEntities) }
    }

    override suspend fun getPokemonTypeById(id: Int): PokemonTypeEntity {
        return fakePokemonTypeMap[id] ?: throw NoSuchElementException("No pokemon type with id $id")
    }

    fun populatePokemonTypesMap(types: List<PokemonTypeEntity>) {
        types.forEach {
            fakePokemonTypeMap[it.id] = it
        }
    }
}
