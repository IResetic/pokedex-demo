package dev.skybit.pokedex.main.pokemon.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import dev.skybit.pokedex.R
import dev.skybit.pokedex.main.pokemon.presentation.model.PokemonDetailsUi
import dev.skybit.pokedex.main.pokemon.presentation.ui.PokemonDetailsScreenEvent.ClearErrorMessage
import dev.skybit.pokedex.main.pokemon.presentation.ui.PokemonDetailsScreenEvent.RetryLoading
import dev.skybit.pokedex.main.pokemon.presentation.ui.components.EmptyPokemonDataView
import dev.skybit.pokedex.main.pokemon.presentation.ui.components.PokemonDetailsBody
import dev.skybit.pokedex.main.pokemon.presentation.ui.components.PokemonDetailsHeader
import dev.skybit.pokedex.main.pokemon.presentation.ui.components.PokemonDetailsRetryOnErrorView
import dev.skybit.pokedex.main.pokemon.presentation.ui.components.PokemonDetailsShimmerView
import dev.skybit.pokedex.main.pokemon.presentation.ui.components.PokemonImageView
import dev.skybit.pokedex.main.pokemon.presentation.ui.model.PokemonDetailsDataState
import dev.skybit.pokedex.main.pokemon.presentation.ui.model.PokemonDetailsDataState.ERROR_CACHED_DATA_IS_EMPTY
import dev.skybit.pokedex.main.pokemon.presentation.ui.model.PokemonDetailsDataState.LOADING_CASHED_DATA_IS_EMPTY
import dev.skybit.pokedex.main.pokemon.presentation.ui.model.PokemonDetailsDataState.NO_DATA_TO_SHOW

@Composable
fun PokemonDetailsRoute(
    navigateBack: () -> Unit
) {
    val viewModel = hiltViewModel<PokemonDetailsScreenViewModel>()
    val context = LocalContext.current
    val pokemonDetailsScreenUiState by viewModel.pokemonDetailsScreenUiState.collectAsState()
    val toastErrorMessage = stringResource(id = R.string.unable_to_fetch_pokemon_details_error_message)

    LaunchedEffect(key1 = pokemonDetailsScreenUiState.errorMessage) {
        val error = pokemonDetailsScreenUiState.errorMessage
        if (error.isNotEmpty() && pokemonDetailsScreenUiState.pokemonDetails != null) {
            Toast.makeText(context, toastErrorMessage, Toast.LENGTH_SHORT).show()
            viewModel.onEvent(ClearErrorMessage)
        }
    }

    PokemonDetailsScreen(
        backgroundColor = pokemonDetailsScreenUiState.backgroundColor,
        pokemonDetailsUi = pokemonDetailsScreenUiState.pokemonDetails,
        pokemonDetailsDataState = pokemonDetailsScreenUiState.pokemonDetailsDataState,
        retryLoading = { viewModel.onEvent(RetryLoading) },
        navigateBack = navigateBack
    )
}

@Composable
fun PokemonDetailsScreen(
    backgroundColor: Color?,
    pokemonDetailsUi: PokemonDetailsUi?,
    pokemonDetailsDataState: PokemonDetailsDataState?,
    retryLoading: () -> Unit,
    navigateBack: () -> Unit
) {
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(backgroundColor ?: MaterialTheme.colorScheme.primary)
        ) {
            pokemonDetailsDataState?.let { dataState ->
                PokemonDetailsHeader(navigateBack = navigateBack)

                when (dataState) {
                    LOADING_CASHED_DATA_IS_EMPTY -> {
                        PokemonDetailsShimmerView()
                    }

                    ERROR_CACHED_DATA_IS_EMPTY -> {
                        PokemonDetailsRetryOnErrorView(
                            backgroundColor = backgroundColor ?: MaterialTheme.colorScheme.primary,
                            message = stringResource(id = R.string.pokemon_details_error_message),
                            onRetry = { retryLoading() }
                        )
                    }

                    NO_DATA_TO_SHOW -> {
                        EmptyPokemonDataView(backgroundColor = backgroundColor)
                    }

                    else -> {
                        pokemonDetailsUi?.let {
                            PokemonDetailsBody(pokemonDetailsUi = it)

                            PokemonImageView(pokemonDetailsUi = it)
                        }
                    }
                }
            }
        }
    }
}
