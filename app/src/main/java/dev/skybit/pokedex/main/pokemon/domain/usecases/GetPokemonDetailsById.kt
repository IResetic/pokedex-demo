package dev.skybit.pokedex.main.pokemon.domain.usecases

import dev.skybit.pokedex.main.pokemon.domain.model.PokemonDetails
import kotlinx.coroutines.flow.Flow

interface GetPokemonDetailsById {
    operator fun invoke(pokemonId: Int): Flow<PokemonDetails?>
}
