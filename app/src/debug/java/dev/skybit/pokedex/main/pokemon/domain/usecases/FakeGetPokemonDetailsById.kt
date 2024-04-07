package dev.skybit.pokedex.main.pokemon.domain.usecases

import dev.skybit.pokedex.main.pokemon.domain.model.PokemonDetails
import dev.skybit.pokedex.main.pokemon.domain.model.fakePokemonDetailsOne
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeGetPokemonDetailsById : GetPokemonDetailsById {

    var fakePokemonDetails = fakePokemonDetailsOne

    override fun invoke(pokemonId: Int): Flow<PokemonDetails?> {
        return flow { emit(fakePokemonDetails) }
    }
}
