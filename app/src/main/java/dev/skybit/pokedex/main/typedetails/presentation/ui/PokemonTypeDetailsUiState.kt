package dev.skybit.pokedex.main.typedetails.presentation.ui

import dev.skybit.pokedex.main.typedetails.domain.model.PokemonBasicInfo
import dev.skybit.pokedex.main.typedetails.presentation.model.PokemonTypeBasicInfoUI

data class PokemonTypeDetailsUiState(
    val pokemonTypeBasicInfo: PokemonTypeBasicInfoUI? = null,
    val pokemonsBasicInfo: List<PokemonBasicInfo> = emptyList(),
    val errorMessage: String = ""
)
