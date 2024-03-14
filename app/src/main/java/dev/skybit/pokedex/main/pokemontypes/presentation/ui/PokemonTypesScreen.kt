package dev.skybit.pokedex.main.pokemontypes.presentation.ui

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_BUG
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_DARK
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_DRAGON
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_FIRE
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_GRASS
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_NORMAL
import dev.skybit.pokedex.main.pokemontypes.presentation.model.PokemonTypeUI
import dev.skybit.pokedex.main.pokemontypes.presentation.ui.PokemonTypeScreenEvent.ClearErrorMessage
import dev.skybit.pokedex.main.pokemontypes.presentation.ui.components.EmptyPokemonTypesList
import dev.skybit.pokedex.main.pokemontypes.presentation.ui.components.HeaderComponent
import dev.skybit.pokedex.main.pokemontypes.presentation.ui.components.PokemonTypeListItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

const val LANDSCAPE_MODE_NUMBER_OF_COLUMNS = 3
const val PORTRAIT_MODE_NUMBER_OF_COLUMNS = 2
const val DEFAULT_SIZE_OF_POKEMON_TYPES_LIST = 10

@Composable
internal fun PokemonTypesRoute(
    navigateToPokemonsList: (pokemonTypeId: Int) -> Unit
) {
    val viewModel = hiltViewModel<PokemonTypeScreenViewModel>()
    val pokemonTypesScreenState = viewModel.pokemonTypeScreenState.collectAsState()
    val pokemonTypes = pokemonTypesScreenState.value.pokemonTypes
    val context = LocalContext.current
    val errorMessage = stringResource(id = R.string.pokemon_types_toast_error)

    LaunchedEffect(key1 = pokemonTypesScreenState.value.error) {
        val error = pokemonTypesScreenState.value.error
        if (error.isNotEmpty() && pokemonTypes.isNotEmpty()) {
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            viewModel.onEvent(ClearErrorMessage)
        }
    }

    PokemonTypesScreen(
        pokemonTypes = pokemonTypes.toImmutableList(),
        isLoading = pokemonTypesScreenState.value.isLoading,
        errorMessage = pokemonTypesScreenState.value.error,
        retryLoading = { viewModel.onEvent(PokemonTypeScreenEvent.RetryLoadingOfPokemonTypes) },
        navigateToPokemonsList = navigateToPokemonsList
    )
}

@Composable
internal fun PokemonTypesScreen(
    pokemonTypes: ImmutableList<PokemonTypeUI>,
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

        when {
            pokemonTypes.isEmpty() && !isLoading && errorMessage.isEmpty() -> {
                EmptyPokemonTypesList(
                    message = stringResource(id = R.string.pokemon_types_empty_list_message),
                    onRetry = retryLoading
                )
            }

            pokemonTypes.isEmpty() && errorMessage.isNotEmpty() && !isLoading -> {
                EmptyPokemonTypesList(
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
                        .padding(paddingValues)
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
                    items(if (pokemonTypes.isEmpty()) DEFAULT_SIZE_OF_POKEMON_TYPES_LIST else pokemonTypes.size) {
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
        pokemonTypes = PokemonTypeUI.fromDomainList(pokemonTypes).toImmutableList(),
        isLoading = false,
        errorMessage = "",
        retryLoading = {},
        navigateToPokemonsList = {}
    )
}
