package dev.skybit.pokedex.main.pokemontypes.presentation.ui

import dev.skybit.pokedex.main.pokemontypes.presentation.model.PokemonTypeUi

data class PokemonTypScreenUIState(
    val isLoading: Boolean = false,
    val pokemonTypes: List<PokemonTypeUi> = emptyList(),
    val errorMessage: String = ""
)
