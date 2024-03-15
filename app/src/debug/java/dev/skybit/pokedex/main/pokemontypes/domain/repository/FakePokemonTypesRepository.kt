package dev.skybit.pokedex.main.pokemontypes.domain.repository

import dev.skybit.pokedex.main.core.domain.model.PokemonType
import dev.skybit.pokedex.main.core.domain.model.fakePokemonTypeFire
import dev.skybit.pokedex.main.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakePokemonTypesRepository : PokemonTypesRepository {
    var fakeResult: Resource<Unit> = Resource.Success(Unit)
    var fakePokemonTypes = listOf<PokemonType>()
    var fakePokemonTypeMap = mutableMapOf<Int, PokemonType>()
    var shouldFetchThrowException: Boolean = false

    override suspend fun populatePokemonTypes(): Resource<Unit> {
        return fakeResult
    }

    override suspend fun getPokemonTypeDetails(typeName: String): PokemonType {
        return fakePokemonTypeFire
    }

    override suspend fun getPokemonTypes(): List<PokemonType> {
        return fakePokemonTypes
    }

    override suspend fun getPokemonTypesFlow(): Flow<List<PokemonType>> {
        return flow { emit(fakePokemonTypes) }
    }

    override suspend fun getPokemonTypeBasicIInfoById(pokemonTypeId: Int): PokemonType {
        return fakePokemonTypeMap[pokemonTypeId] ?: throw NoSuchElementException(
            "No pokemon type with id $pokemonTypeId"
        )
    }

    override suspend fun fetchNewPokemonTypes(offset: Int) {
        if (shouldFetchThrowException) {
            throw Exception("Fake exception")
        }
    }

    fun populatePokemonTypesMap(types: List<PokemonType>) {
        types.forEach {
            fakePokemonTypeMap[it.id] = it
        }
    }
}
