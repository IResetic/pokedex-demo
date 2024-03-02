package dev.skybit.pokedex.main.pokemontypes.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun PokemonTypesRoute() {
    val viewModel = hiltViewModel<PokemonTypeScreenViewModel>()

    PokemonTypesScreen()
}

@Composable
internal fun PokemonTypesScreen() {
    Scaffold { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            Text("Pokemon Types Screen")
        }
    }
}
