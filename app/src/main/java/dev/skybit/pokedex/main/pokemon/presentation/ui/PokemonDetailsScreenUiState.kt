package dev.skybit.pokedex.main.pokemon.presentation.ui

import androidx.compose.ui.graphics.Color
import dev.skybit.pokedex.main.pokemon.presentation.model.PokemonDetailsUi
import dev.skybit.pokedex.main.pokemon.presentation.ui.model.PokemonDetailsDataState

data class PokemonDetailsScreenUiState(
    val backgroundColor: Color? = null,
    val pokemonDetails: PokemonDetailsUi? = null,
    val errorMessage: String = "",
    val pokemonDetailsDataState: PokemonDetailsDataState? = null
)
