package dev.skybit.pokedex.main.pokemon.domain.usecases

import dev.skybit.pokedex.main.core.utils.Resource
import dev.skybit.pokedex.main.pokemon.domain.model.PokemonDetails

interface GetPokemonDetails {
    suspend operator fun invoke(pokemonId: Int): Resource<PokemonDetails>
}
