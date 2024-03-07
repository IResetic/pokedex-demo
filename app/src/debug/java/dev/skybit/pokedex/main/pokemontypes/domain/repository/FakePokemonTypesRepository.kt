package dev.skybit.pokedex.main.pokemontypes.domain.repository

import dev.skybit.pokedex.main.core.utils.Resource
import dev.skybit.pokedex.main.pokemontypes.domain.model.PokemonType
import dev.skybit.pokedex.main.pokemontypes.domain.model.fakePokemonTypeFire
import dev.skybit.pokedex.main.pokemontypes.domain.model.fakePokemonTypeGrass

class FakePokemonTypesRepository : PokemonTypesRepository {
    var fakeResult: Resource<Unit> = Resource.Success(Unit)
    var fakePokemonTypes = listOf(fakePokemonTypeGrass, fakePokemonTypeFire)

    override suspend fun populatePokemonTypes(): Resource<Unit> {
        return fakeResult
    }

    override suspend fun getPokemonTypeDetails(typeName: String): PokemonType {
        return fakePokemonTypeFire
    }

    override suspend fun getPokemonTypes(): List<PokemonType> {
        return fakePokemonTypes
    }
}
