package dev.skybit.pokedex.main.pokemontypes.presentation.ui

import dev.skybit.pokedex.main.pokemontypes.domain.model.PokemonType

data class PokemonTypScreenUIState(
    val isLoading: Boolean = false,
    // TODO Replace with the UI pokemon type that will contain color and image
    val pokemonTypes: List<PokemonType> = emptyList(),
    val error: String = ""
)
