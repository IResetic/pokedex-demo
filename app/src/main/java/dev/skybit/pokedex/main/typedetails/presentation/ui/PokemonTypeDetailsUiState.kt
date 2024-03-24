package dev.skybit.pokedex.main.typedetails.presentation.ui

import dev.skybit.pokedex.main.typedetails.presentation.model.PokemonTypeBasicInfoUI

data class PokemonTypeDetailsUiState(
    val pokemonTypeBasicInfo: PokemonTypeBasicInfoUI? = null,
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val errorMessage: String = ""
)
