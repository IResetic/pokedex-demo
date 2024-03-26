package dev.skybit.pokedex.main.pokemontypes.presentation.ui

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import dev.skybit.pokedex.R
import dev.skybit.pokedex.main.core.domain.model.PokemonType
import dev.skybit.pokedex.main.core.presentation.style.defaultPadding
import dev.skybit.pokedex.main.core.presentation.style.largePadding
import dev.skybit.pokedex.main.core.utils.DEFAULT_SIZE_OF_GRID_LIST
import dev.skybit.pokedex.main.core.utils.LANDSCAPE_MODE_NUMBER_OF_COLUMNS
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_BUG
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_DARK
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_DRAGON
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_FIRE
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_GRASS
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_NORMAL
import dev.skybit.pokedex.main.core.utils.PORTRAIT_MODE_NUMBER_OF_COLUMNS
import dev.skybit.pokedex.main.pokemontypes.presentation.model.PokemonTypeUi
import dev.skybit.pokedex.main.pokemontypes.presentation.ui.PokemonTypeScreenEvent.ClearErrorMessage
import dev.skybit.pokedex.main.pokemontypes.presentation.ui.components.EmptyPokemonTypesListView
import dev.skybit.pokedex.main.pokemontypes.presentation.ui.components.HeaderComponent
import dev.skybit.pokedex.main.pokemontypes.presentation.ui.components.PokemonTypeListItem
import dev.skybit.pokedex.main.pokemontypes.presentation.ui.components.PokemonTypeRetryOnErrorView
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
internal fun PokemonTypesRoute(
    navigateToPokemonsList: (pokemonTypeId: Int) -> Unit
) {
    val viewModel = hiltViewModel<PokemonTypeScreenViewModel>()
    val pokemonTypesScreenState by viewModel.pokemonTypeScreenState.collectAsState()
    val pokemonTypes = pokemonTypesScreenState.pokemonTypes
    val context = LocalContext.current
    val errorMessage = stringResource(id = R.string.pokemon_types_toast_error)

    LaunchedEffect(key1 = pokemonTypesScreenState.errorMessage) {
        val error = pokemonTypesScreenState.errorMessage
        if (error.isNotEmpty() && pokemonTypes.isNotEmpty()) {
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            viewModel.onEvent(ClearErrorMessage)
        }
    }

    PokemonTypesScreen(
        pokemonTypes = pokemonTypes.toImmutableList(),
        isLoading = pokemonTypesScreenState.isLoading,
        errorMessage = pokemonTypesScreenState.errorMessage,
        retryLoading = { viewModel.onEvent(PokemonTypeScreenEvent.RetryLoadingOfPokemonTypes) },
        navigateToPokemonsList = navigateToPokemonsList
    )
}

@Composable
internal fun PokemonTypesScreen(
    pokemonTypes: ImmutableList<PokemonTypeUi>,
    isLoading: Boolean = false,
    errorMessage: String,
    retryLoading: () -> Unit,
    navigateToPokemonsList: (Int) -> Unit
) {
    Scaffold(
        modifier = Modifier.background(color = MaterialTheme.colorScheme.primary),
        topBar = {
            HeaderComponent()
        }
    ) { paddingValues ->
        val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
        Box(modifier = Modifier.padding(paddingValues)) {
            when {
                pokemonTypes.isEmpty() && !isLoading && errorMessage.isEmpty() -> {
                    EmptyPokemonTypesListView()
                }

                pokemonTypes.isEmpty() && errorMessage.isNotEmpty() && !isLoading -> {
                    PokemonTypeRetryOnErrorView(
                        message = stringResource(id = R.string.pokemon_types_error_message),
                        onRetry = retryLoading
                    )
                }

                else -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(
                            if (isLandscape) LANDSCAPE_MODE_NUMBER_OF_COLUMNS else PORTRAIT_MODE_NUMBER_OF_COLUMNS
                        ),
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = MaterialTheme.colorScheme.primary),
                        contentPadding = PaddingValues(
                            start = largePadding,
                            end = largePadding,
                            top = defaultPadding,
                            bottom = largePadding
                        ),
                        verticalArrangement = Arrangement.spacedBy(defaultPadding),
                        horizontalArrangement = Arrangement.spacedBy(defaultPadding)
                    ) {
                        items(if (pokemonTypes.isEmpty()) DEFAULT_SIZE_OF_GRID_LIST else pokemonTypes.size) {
                            val pokemonType = if (pokemonTypes.isNotEmpty()) pokemonTypes[it] else null
                            PokemonTypeListItem(
                                pokemonType = pokemonType,
                                onClick = navigateToPokemonsList
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun PokemonTypesScreenPreview() {
    val pokemonTypes = listOf(
        PokemonType(1, POKEMON_TYPE_GRASS),
        PokemonType(2, POKEMON_TYPE_FIRE),
        PokemonType(3, POKEMON_TYPE_NORMAL),
        PokemonType(4, POKEMON_TYPE_BUG),
        PokemonType(5, POKEMON_TYPE_DARK),
        PokemonType(6, POKEMON_TYPE_DRAGON)
    )

    PokemonTypesScreen(
        pokemonTypes = PokemonTypeUi.fromDomainList(pokemonTypes).toImmutableList(),
        isLoading = false,
        errorMessage = "",
        retryLoading = {},
        navigateToPokemonsList = {}
    )
}

@Preview(showBackground = false)
@Composable
fun PokemonTypesScreenEmptyListPreview() {
    val emptyList: ImmutableList<PokemonTypeUi> = emptyList<PokemonTypeUi>().toImmutableList()

    PokemonTypesScreen(
        pokemonTypes = emptyList,
        isLoading = false,
        errorMessage = "",
        retryLoading = {},
        navigateToPokemonsList = {}
    )
}

@Preview(showBackground = false)
@Composable
fun PokemonTypesScreenEmptyListWithErrorPreview() {
    val emptyList: ImmutableList<PokemonTypeUi> = emptyList<PokemonTypeUi>().toImmutableList()

    PokemonTypesScreen(
        pokemonTypes = emptyList,
        isLoading = false,
        errorMessage = "Error for preview",
        retryLoading = {},
        navigateToPokemonsList = {}
    )
}
