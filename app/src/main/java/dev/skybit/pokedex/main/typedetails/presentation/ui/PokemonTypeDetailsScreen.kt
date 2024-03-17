package dev.skybit.pokedex.main.typedetails.presentation.ui

import android.annotation.SuppressLint
import android.content.res.Configuration
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.hilt.navigation.compose.hiltViewModel
import dev.skybit.pokedex.main.core.presentation.style.defaultPadding
import dev.skybit.pokedex.main.core.presentation.style.largePadding
import dev.skybit.pokedex.main.core.utils.LANDSCAPE_MODE_NUMBER_OF_COLUMNS
import dev.skybit.pokedex.main.core.utils.PORTRAIT_MODE_NUMBER_OF_COLUMNS
import dev.skybit.pokedex.main.typedetails.presentation.model.PokemonBasicInfoUi
import dev.skybit.pokedex.main.typedetails.presentation.model.PokemonTypeBasicInfoUI
import dev.skybit.pokedex.main.typedetails.presentation.ui.components.BasicPokemonListItem
import dev.skybit.pokedex.main.typedetails.presentation.ui.components.PokemonTypeDetailsHeaderComponent

@Composable
fun PokemonTypeDetailsRoute() {
    val viewModel = hiltViewModel<PokemonTypeDetailsViewModel>()
    val pokemonsListScreenState = viewModel.pokemonsListScreenState.collectAsState()
    val pokemonTypeBasicInfo = pokemonsListScreenState.value.pokemonTypeBasicInfo
    val pokemons = pokemonsListScreenState.value.pokemons

    PokemonTypesDetailsScreen(
        pokemonTypeBasicInfo = pokemonTypeBasicInfo,
        pokemons = pokemons
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PokemonTypesDetailsScreen(
    pokemonTypeBasicInfo: PokemonTypeBasicInfoUI? = null,
    pokemons: List<PokemonBasicInfoUi> = emptyList()
) {
    Scaffold(
        topBar = {
            PokemonTypeDetailsHeaderComponent(
                backgroundColor = pokemonTypeBasicInfo?.backgroundColor,
                title = pokemonTypeBasicInfo?.title
            )
        }
    ) { paddingValues ->
        val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

        LazyVerticalGrid(
            columns = GridCells.Fixed(
                if (isLandscape) LANDSCAPE_MODE_NUMBER_OF_COLUMNS else PORTRAIT_MODE_NUMBER_OF_COLUMNS
            ),
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(color = pokemonTypeBasicInfo?.backgroundColor ?: MaterialTheme.colorScheme.primary),
            contentPadding = PaddingValues(
                start = largePadding,
                end = largePadding,
                top = defaultPadding,
                bottom = largePadding
            ),
            verticalArrangement = Arrangement.spacedBy(defaultPadding),
            horizontalArrangement = Arrangement.spacedBy(defaultPadding)
        ) {
            items(pokemons.size) { index ->
                BasicPokemonListItem(pokemons[index])
            }
        }
    }
}
