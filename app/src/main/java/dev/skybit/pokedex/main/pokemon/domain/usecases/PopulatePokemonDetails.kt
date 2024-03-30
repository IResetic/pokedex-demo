package dev.skybit.pokedex.main.pokemon.domain.usecases

import dev.skybit.pokedex.main.core.utils.Resource

interface PopulatePokemonDetails {
    suspend operator fun invoke(pokemonId: Int): Resource<Unit>
}
