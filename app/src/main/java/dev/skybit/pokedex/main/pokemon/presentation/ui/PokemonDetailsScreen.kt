package dev.skybit.pokedex.main.pokemon.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import dev.skybit.pokedex.main.pokemon.presentation.model.PokemonDetailsUi
import dev.skybit.pokedex.main.pokemon.presentation.ui.components.PokemonDetailsBody
import dev.skybit.pokedex.main.pokemon.presentation.ui.components.PokemonDetailsHeader
import dev.skybit.pokedex.main.pokemon.presentation.ui.components.PokemonImageView

@Composable
fun PokemonDetailsRoute() {
    val viewModel = hiltViewModel<PokemonDetailsScreenViewModel>()
    val pokemonDetailsScreenUiState by viewModel.pokemonDetailsScreenUiState.collectAsState()

    PokemonDetailsScreen(
        backgroundColor = pokemonDetailsScreenUiState.backgroundColor,
        pokemonDetailsUi = pokemonDetailsScreenUiState.pokemonDetails
    )
}

@Composable
fun PokemonDetailsScreen(
    backgroundColor: Color?,
    pokemonDetailsUi: PokemonDetailsUi?
) {
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(backgroundColor ?: MaterialTheme.colorScheme.primary)
        ) {
            PokemonDetailsHeader()
            pokemonDetailsUi?.let {
                PokemonDetailsBody(pokemonDetailsUi = it)

                PokemonImageView(pokemonDetailsUi = it)
            }
        }
    }
}
