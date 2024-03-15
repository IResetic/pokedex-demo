package dev.skybit.pokedex.main.pokemontypes.domain.repository

import dev.skybit.pokedex.main.core.domain.model.PokemonType
import dev.skybit.pokedex.main.core.utils.Resource

class FakePokemonTypesRepository : PokemonTypesRepository {
    var fakeResult: Resource<Unit> = Resource.Success(Unit)
    var fakePokemonTypes = listOf<PokemonType>()
    var fakePokemonTypeMap = mutableMapOf<Int, PokemonType>()
    var shouldFetchThrowException: Boolean = false

    override suspend fun getPokemonTypes(): List<PokemonType> {
        return fakePokemonTypes
    }

    override suspend fun getPokemonTypeBasicIInfoById(pokemonTypeId: Int): PokemonType {
        return fakePokemonTypeMap[pokemonTypeId] ?: throw NoSuchElementException(
            "No pokemon type with id $pokemonTypeId"
        )
    }

    override suspend fun populatePokemonTypes(offset: Int) {
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
