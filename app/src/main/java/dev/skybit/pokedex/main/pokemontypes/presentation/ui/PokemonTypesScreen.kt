package dev.skybit.pokedex.main.pokemontypes.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dev.skybit.pokedex.main.pokemontypes.domain.model.PokemonType

@Composable
internal fun PokemonTypesRoute() {
    val viewModel = hiltViewModel<PokemonTypeScreenViewModel>()
    val pokemonTypesScreenState = viewModel.pokemonTypeScreenState.collectAsState()
    val pokemonType = pokemonTypesScreenState.value.pokemonTypes

    PokemonTypesScreen(pokemonTypes = pokemonType)
}

@Composable
internal fun PokemonTypesScreen(
    pokemonTypes: List<PokemonType>
) {
    Scaffold { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            LazyColumn {
                items(pokemonTypes.size) { pokemonType ->
                    Text(text = pokemonTypes[pokemonType].name)
                }
            }
        }
    }
}
