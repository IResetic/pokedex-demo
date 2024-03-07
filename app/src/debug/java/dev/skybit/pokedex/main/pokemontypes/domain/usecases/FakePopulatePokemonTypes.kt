package dev.skybit.pokedex.main.pokemontypes.domain.usecases

import dev.skybit.pokedex.main.core.utils.Resource

class FakePopulatePokemonTypes : PopulatePokemonTypes {
    var fakeResult: Resource<Unit> = Resource.Success(Unit)

    override suspend fun invoke(): Resource<Unit> {
        return fakeResult
    }
}
