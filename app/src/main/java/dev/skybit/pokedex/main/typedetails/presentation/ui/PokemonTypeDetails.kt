package dev.skybit.pokedex.main.typedetails.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dev.skybit.pokedex.main.typedetails.presentation.model.PokemonTypeBasicInfoUI

@Composable
fun PokemonTypeDetailsRoute() {
    val viewModel = hiltViewModel<PokemonTypeDetailsViewModel>()
    val pokemonsListScreenState = viewModel.pokemonsListScreenState.collectAsState()
    val pokemonTypeBasicInfo = pokemonsListScreenState.value.pokemonTypeBasicInfo

    PokemonTypesDetailsScreen(
        pokemonTypeBasicInfo = pokemonTypeBasicInfo
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PokemonTypesDetailsScreen(
    pokemonTypeBasicInfo: PokemonTypeBasicInfoUI? = null
) {
    Scaffold {
        Box(modifier = Modifier.fillMaxSize().background(pokemonTypeBasicInfo?.backgroundColor ?: MaterialTheme.colorScheme.primary)) {
            Text("PokemonsListScreen ${pokemonTypeBasicInfo?.title}")
        }
    }
}
