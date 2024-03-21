package dev.skybit.pokedex.main.typedetails.presentation.ui

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import dev.skybit.pokedex.R
import dev.skybit.pokedex.main.core.presentation.style.defaultPadding
import dev.skybit.pokedex.main.core.presentation.style.largePadding
import dev.skybit.pokedex.main.core.presentation.ui.components.ShimmerGridListItem
import dev.skybit.pokedex.main.core.utils.DEFAULT_SIZE_OF_GRID_LIST
import dev.skybit.pokedex.main.core.utils.LANDSCAPE_MODE_NUMBER_OF_COLUMNS
import dev.skybit.pokedex.main.core.utils.PORTRAIT_MODE_NUMBER_OF_COLUMNS
import dev.skybit.pokedex.main.typedetails.presentation.model.PokemonBasicInfoUi
import dev.skybit.pokedex.main.typedetails.presentation.model.PokemonTypeBasicInfoUI
import dev.skybit.pokedex.main.typedetails.presentation.ui.PokemonTypeDetailsScreenEvent.ClearErrorMessage
import dev.skybit.pokedex.main.typedetails.presentation.ui.PokemonTypeDetailsScreenEvent.RetryLoadingPokemonTypeDetails
import dev.skybit.pokedex.main.typedetails.presentation.ui.components.BasicPokemonListItem
import dev.skybit.pokedex.main.typedetails.presentation.ui.components.EmptyPokemonListView
import dev.skybit.pokedex.main.typedetails.presentation.ui.components.PokemonTypeDetailsHeaderComponent
import dev.skybit.pokedex.main.typedetails.presentation.ui.components.PokemonsRetryOnErrorView

@Composable
fun PokemonTypeDetailsRoute(
    navigateBack: () -> Unit
) {
    val viewModel = hiltViewModel<PokemonTypeDetailsViewModel>()
    val pokemonsListScreenState = viewModel.pokemonsListScreenState.collectAsState()
    val pokemonTypeBasicInfo = pokemonsListScreenState.value.pokemonTypeBasicInfo
    val pokemons = pokemonsListScreenState.value.pokemons
    val isLoading = pokemonsListScreenState.value.isLoading
    val errorMessage = stringResource(id = R.string.unable_to_fetch_pokemon_type_details_error_message)
    val context = LocalContext.current

    LaunchedEffect(key1 = pokemonsListScreenState.value.errorMessage) {
        val error = pokemonsListScreenState.value.errorMessage
        if (error.isNotEmpty() && pokemons.isNotEmpty()) {
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            viewModel.onEvent(ClearErrorMessage)
        }
    }

    PokemonTypesDetailsScreen(
        pokemonTypeBasicInfo = pokemonTypeBasicInfo,
        pokemons = pokemons,
        isLoading = isLoading,
        errorMessage = errorMessage,
        refreshPokemonTypeDetails = { viewModel.onEvent(RetryLoadingPokemonTypeDetails) },
        navigateBack = navigateBack
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PokemonTypesDetailsScreen(
    pokemonTypeBasicInfo: PokemonTypeBasicInfoUI? = null,
    pokemons: List<PokemonBasicInfoUi> = emptyList(),
    isLoading: Boolean,
    errorMessage: String,
    refreshPokemonTypeDetails: () -> Unit,
    navigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            PokemonTypeDetailsHeaderComponent(
                backgroundColor = pokemonTypeBasicInfo?.backgroundColor,
                title = pokemonTypeBasicInfo?.title,
                navigateBack = navigateBack
            )
        }
    ) { paddingValues ->
        val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
        val gridState = rememberLazyGridState()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                pokemons.isEmpty() && !isLoading && errorMessage.isEmpty() -> {
                    EmptyPokemonListView(pokemonTypeBasicInfo?.backgroundColor)
                }

                pokemons.isEmpty() && !isLoading && errorMessage.isNotEmpty() -> {
                    PokemonsRetryOnErrorView(
                        backgroundColor = pokemonTypeBasicInfo?.backgroundColor,
                        onRetry = refreshPokemonTypeDetails
                    )
                }

                else -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(
                            if (isLandscape) LANDSCAPE_MODE_NUMBER_OF_COLUMNS else PORTRAIT_MODE_NUMBER_OF_COLUMNS
                        ),
                        state = gridState,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                color = pokemonTypeBasicInfo?.backgroundColor ?: MaterialTheme.colorScheme.primary
                            ),
                        contentPadding = PaddingValues(
                            start = largePadding,
                            end = largePadding,
                            top = defaultPadding,
                            bottom = largePadding
                        ),
                        verticalArrangement = Arrangement.spacedBy(defaultPadding),
                        horizontalArrangement = Arrangement.spacedBy(defaultPadding)
                    ) {
                        if (isLoading) {
                            items(DEFAULT_SIZE_OF_GRID_LIST) {
                                ShimmerGridListItem()
                            }
                        } else {
                            items(pokemons.size) { index ->
                                BasicPokemonListItem(pokemons[index])
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun PokemonTypesDetailsScreenPreview() {
    PokemonTypesDetailsScreen(
        pokemonTypeBasicInfo = PokemonTypeBasicInfoUI(
            title = "fire",
            backgroundColor = MaterialTheme.colorScheme.primary
        ),
        pokemons = listOf(
            PokemonBasicInfoUi(
                id = 1,
                name = "bulbasaur",
                imageUrl = ""
            ),
            PokemonBasicInfoUi(
                id = 2,
                name = "ivysaur",
                imageUrl = ""
            ),
            PokemonBasicInfoUi(
                id = 3,
                name = "venusaur",
                imageUrl = ""
            ),
            PokemonBasicInfoUi(
                id = 4,
                name = "charmander",
                imageUrl = ""
            ),
            PokemonBasicInfoUi(
                id = 5,
                name = "charmeleon",
                imageUrl = ""
            ),
            PokemonBasicInfoUi(
                id = 6,
                name = "charizard",
                imageUrl = ""
            )
        ),
        isLoading = false,
        errorMessage = "",
        refreshPokemonTypeDetails = {},
        navigateBack = {}
    )
}
