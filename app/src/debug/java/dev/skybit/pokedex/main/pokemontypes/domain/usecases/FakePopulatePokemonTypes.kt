package dev.skybit.pokedex.main.pokemontypes.domain.usecases

import dev.skybit.pokedex.main.core.utils.Resource
import dev.skybit.pokedex.main.pokemontypes.domain.usecases.PopulatePokemonTypes

class FakePopulatePokemonTypes : PopulatePokemonTypes {
    var fakeResult = Resource.Success(Unit)

    override suspend fun invoke(): Resource<Unit> {
        return fakeResult
    }
}
