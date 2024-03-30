package dev.skybit.pokedex.main.pokemon.presentation.ui

import androidx.compose.ui.graphics.Color
import dev.skybit.pokedex.main.pokemon.presentation.model.PokemonDetailsUi

data class PokemonDetailsScreenUiState(
    val backgroundColor: Color? = null,
    val pokemonDetails: PokemonDetailsUi? = null
)
