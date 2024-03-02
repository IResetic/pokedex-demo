package dev.skybit.pokedex.main.pokemontypes.presentation.ui

data class PokemonTypScreenUIState(
    val isLoading: Boolean = false,
    val pokemonTypes: List<String> = emptyList(),
    val error: String = ""
)
