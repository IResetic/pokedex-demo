package dev.skybit.pokedex.main.pokemonslist.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun PokemonsListRoute() {
    val viewModel = hiltViewModel<PokemonsListViewModel>()

    PokemonsListScreen()
}

@Composable
fun PokemonsListScreen() {
    Box() {
        Text("PokemonsListScreen")
    }
}
