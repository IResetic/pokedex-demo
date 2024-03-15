package dev.skybit.pokedex.main.pokemontypes.presentation.ui

import dev.skybit.pokedex.main.pokemontypes.presentation.model.PokemonTypeUI

data class PokemonTypScreenUIState(
    val isLoading: Boolean = false,
    val pokemonTypes: List<PokemonTypeUI> = emptyList(),
    val errorMessage: String = ""
)
