package dev.skybit.pokedex.main.pokemontypes.domain.repository

import dev.skybit.pokedex.main.core.utils.Resource
import dev.skybit.pokedex.main.pokemontypes.domain.model.PokemonType
import dev.skybit.pokedex.main.pokemontypes.domain.model.fakePokemonTypeFire
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakePokemonTypesRepository : PokemonTypesRepository {
    var fakeResult: Resource<Unit> = Resource.Success(Unit)
    var fakePokemonTypes = listOf<PokemonType>()

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
}
