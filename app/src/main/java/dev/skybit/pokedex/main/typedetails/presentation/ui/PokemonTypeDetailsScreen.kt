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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.LoadState.NotLoading
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import dev.skybit.pokedex.R
import dev.skybit.pokedex.main.core.presentation.ui.components.ShimmerGridListItem
import dev.skybit.pokedex.main.core.presentation.utlis.defaultPadding
import dev.skybit.pokedex.main.core.presentation.utlis.largePadding
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
import kotlinx.coroutines.flow.flowOf

@Composable
fun PokemonTypeDetailsRoute(
    navigateToPokemonDetails: (pokemonId: Int) -> Unit,
    navigateBack: () -> Unit
) {
    val viewModel = hiltViewModel<PokemonTypeDetailsScreenViewModel>()
    val context = LocalContext.current
    val pokemonsListScreenState by viewModel.pokemonsListScreenState.collectAsState()
    val pokemonsItems = viewModel.pokemonsBasicInfoPagingSource.collectAsLazyPagingItems()
    val toastErrorMessage = stringResource(id = R.string.unable_to_fetch_pokemon_type_details_error_message)

    LaunchedEffect(key1 = pokemonsListScreenState.errorMessage) {
        val error = pokemonsListScreenState.errorMessage
        if (error.isNotEmpty() && pokemonsItems.itemCount > 0) {
            Toast.makeText(context, toastErrorMessage, Toast.LENGTH_SHORT).show()
            viewModel.onEvent(ClearErrorMessage)
        }
    }

    PokemonTypesDetailsScreen(
        pokemonTypeBasicInfo = pokemonsListScreenState.pokemonTypeBasicInfo,
        pokemonsItems = pokemonsItems,
        isLoading = pokemonsListScreenState.isLoading,
        errorMessage = pokemonsListScreenState.errorMessage,
        refreshPokemonTypeDetails = { viewModel.onEvent(RetryLoadingPokemonTypeDetails) },
        navigateToPokemonDetails = navigateToPokemonDetails,
        navigateBack = navigateBack
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PokemonTypesDetailsScreen(
    pokemonTypeBasicInfo: PokemonTypeBasicInfoUI? = null,
    pokemonsItems: LazyPagingItems<PokemonBasicInfoUi>,
    isLoading: Boolean,
    errorMessage: String,
    refreshPokemonTypeDetails: () -> Unit,
    navigateToPokemonDetails: (pokemonId: Int) -> Unit,
    navigateBack: () -> Unit
) {
    val isEmptyState by remember(pokemonsItems.loadState.refresh, pokemonsItems.itemCount, isLoading, errorMessage) {
        derivedStateOf {
            pokemonsItems.loadState.refresh is NotLoading &&
                pokemonsItems.itemCount == 0 &&
                !isLoading &&
                errorMessage.isEmpty()
        }
    }

    val isErrorState by remember(pokemonsItems.loadState.refresh, pokemonsItems.itemCount, isLoading, errorMessage) {
        derivedStateOf {
            pokemonsItems.loadState.refresh is NotLoading &&
                !isLoading &&
                pokemonsItems.itemCount == 0 &&
                errorMessage.isNotEmpty()
        }
    }

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
                isEmptyState -> {
                    EmptyPokemonListView(pokemonTypeBasicInfo?.backgroundColor)
                }

                isErrorState -> {
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
                        if (isLoading || pokemonsItems.loadState.refresh is LoadState.Loading) {
                            items(DEFAULT_SIZE_OF_GRID_LIST) {
                                ShimmerGridListItem()
                            }
                        } else {
                            items(pokemonsItems.itemCount) { index ->
                                val pokemonInfo = pokemonsItems[index]
                                if (pokemonInfo != null) {
                                    BasicPokemonListItem(
                                        pokemonBasicInfo = pokemonInfo,
                                        navigateToPokemonDetails = navigateToPokemonDetails
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonTypesDetailsScreenPreview() {
    val pokemonTypeBasicInfo = PokemonTypeBasicInfoUI(backgroundColor = Color.Blue, title = "Water")
    val pokemonsItems = flowOf(
        PagingData.from(
            listOf(
                PokemonBasicInfoUi(
                    id = 1,
                    name = "Bulbasaur",
                    imageUrl = ""

                ),
                PokemonBasicInfoUi(
                    id = 2,
                    name = "Ivysaur",
                    imageUrl = ""
                ),
                PokemonBasicInfoUi(
                    id = 3,
                    name = "Venusaur",
                    imageUrl = ""
                ),
                PokemonBasicInfoUi(
                    id = 4,
                    name = "Charmander",
                    imageUrl = ""

                ),
                PokemonBasicInfoUi(
                    id = 5,
                    name = "Charmeleon",
                    imageUrl = ""

                ),
                PokemonBasicInfoUi(
                    id = 6,
                    name = "Charizard",
                    imageUrl = ""
                )
            )
        )
    ).collectAsLazyPagingItems()
    val isLoading = false
    val errorMessage = ""

    PokemonTypesDetailsScreen(
        pokemonTypeBasicInfo = pokemonTypeBasicInfo,
        pokemonsItems = pokemonsItems,
        isLoading = isLoading,
        errorMessage = errorMessage,
        refreshPokemonTypeDetails = { },
        navigateToPokemonDetails = { },
        navigateBack = { }
    )
}
