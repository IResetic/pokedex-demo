package dev.skybit.pokedex.main.typedetails.presentation.ui

import dev.skybit.pokedex.main.typedetails.presentation.model.PokemonBasicInfoUi
import dev.skybit.pokedex.main.typedetails.presentation.model.PokemonTypeBasicInfoUI

data class PokemonTypeDetailsUiState(
    val pokemonTypeBasicInfo: PokemonTypeBasicInfoUI? = null,
    val pokemons: List<PokemonBasicInfoUi> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String = ""
)
