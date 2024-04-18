package dev.skybit.pokedex.main.pokemon.domain.usecases

import dev.skybit.pokedex.main.core.utils.Resource
import dev.skybit.pokedex.main.pokemon.domain.model.PokemonDetails
import dev.skybit.pokedex.main.pokemon.domain.model.fakePokemonDetailsOne

class FakeGetPokemonDetails : GetPokemonDetails {
    var fakePokemonDetails: Resource<PokemonDetails> = Resource.Success(fakePokemonDetailsOne)

    override suspend fun invoke(pokemonId: Int): Resource<PokemonDetails> {
        return fakePokemonDetails
    }
}
