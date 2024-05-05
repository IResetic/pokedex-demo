package dev.skybit.pokedex.main.main.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import dev.skybit.pokedex.main.pokemon.presentation.navigation.PokemonDetailsScreenDestination
import dev.skybit.pokedex.main.pokemon.presentation.navigation.pokemonDetailsGraph
import dev.skybit.pokedex.main.pokemontypes.presentation.navigation.PokemonTypesScreenDestination
import dev.skybit.pokedex.main.pokemontypes.presentation.navigation.pokemonTypesGraph
import dev.skybit.pokedex.main.typedetails.presentation.navigation.PokemonTypeDetailsScreenDestination
import dev.skybit.pokedex.main.typedetails.presentation.navigation.pokemonTypeDetailsGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PokedexContent(navController: NavHostController) {
    Scaffold(
        modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars)
    ) {
        NavHost(
            navController = navController,
            startDestination = PokemonTypesScreenDestination.route
        ) {
            pokemonTypesGraph(
                navigateToPokemonsList = { navController.navigate(PokemonTypeDetailsScreenDestination.route(it)) }
            )

            pokemonTypeDetailsGraph(
                navigateToPokemonDetails = { navController.navigate(PokemonDetailsScreenDestination.route(it)) },
                navigateBack = navController::popBackStack
            )

            pokemonDetailsGraph(
                navigateBack = navController::popBackStack
            )
        }
    }
}
