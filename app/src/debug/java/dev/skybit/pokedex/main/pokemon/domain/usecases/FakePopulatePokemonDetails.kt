package dev.skybit.pokedex.main.pokemon.domain.usecases

import dev.skybit.pokedex.main.core.utils.Resource

class FakePopulatePokemonDetails : PopulatePokemonDetails {
    var fakeResult: Resource<Unit> = Resource.Error("Network error", Unit)

    override suspend fun invoke(pokemonId: Int): Resource<Unit> {
        return fakeResult
    }
}
