package dev.skybit.pokedex.main.pokemontypes.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import dev.skybit.pokedex.main.core.presentation.style.defaultPadding
import dev.skybit.pokedex.main.core.presentation.style.largePadding
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_BUG
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_DARK
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_DRAGON
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_FIRE
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_GRASS
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_NORMAL
import dev.skybit.pokedex.main.pokemontypes.domain.model.PokemonType
import dev.skybit.pokedex.main.pokemontypes.presentation.model.PokemonTypeUI
import dev.skybit.pokedex.main.pokemontypes.presentation.ui.components.HeaderComponent
import dev.skybit.pokedex.main.pokemontypes.presentation.ui.components.PokemonTypeListItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

const val NUMBER_OF_COLUMNS = 2

@Composable
internal fun PokemonTypesRoute() {
    val viewModel = hiltViewModel<PokemonTypeScreenViewModel>()
    val pokemonTypesScreenState = viewModel.pokemonTypeScreenState.collectAsState()
    val pokemonType = pokemonTypesScreenState.value.pokemonTypes

    PokemonTypesScreen(pokemonTypes = pokemonType.toImmutableList())
}

@Composable
internal fun PokemonTypesScreen(
    pokemonTypes: ImmutableList<PokemonTypeUI>
) {
    Scaffold(
        modifier = Modifier.background(color = MaterialTheme.colorScheme.primary)
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(NUMBER_OF_COLUMNS),
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.primary),
            contentPadding = PaddingValues(
                start = largePadding,
                end = largePadding,
                top = defaultPadding,
                bottom = defaultPadding
            ),
            verticalArrangement = Arrangement.spacedBy(defaultPadding),
            horizontalArrangement = Arrangement.spacedBy(defaultPadding)
        ) {
            item(span = { GridItemSpan(this.maxLineSpan) }, content = {
                HeaderComponent()
            })

            itemsIndexed(pokemonTypes) { _, pokemonTypeUI ->
                PokemonTypeListItem(pokemonType = pokemonTypeUI)
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
        pokemonTypes = PokemonTypeUI.fromDomainList(pokemonTypes).toImmutableList()
    )
}
