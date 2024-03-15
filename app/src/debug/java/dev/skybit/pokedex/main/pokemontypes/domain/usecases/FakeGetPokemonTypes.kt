package dev.skybit.pokedex.main.pokemontypes.domain.usecases

import dev.skybit.pokedex.main.core.domain.model.PokemonType
import dev.skybit.pokedex.main.core.utils.Resource

class FakeGetPokemonTypes : GetPokemonTypes {
    var fakeResult: Resource<List<PokemonType>> = Resource.Success(emptyList<PokemonType>())

    override suspend fun invoke(): Resource<List<PokemonType>> {
        return fakeResult
    }
}
