package dev.skybit.pokedex.main.main.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import dev.skybit.pokedex.main.pokemonslist.presentation.navigation.PokemonsListScreenDestination
import dev.skybit.pokedex.main.pokemonslist.presentation.navigation.pokemonsListGraph
import dev.skybit.pokedex.main.pokemontypes.presentation.navigation.PokemonTypesScreenDestination
import dev.skybit.pokedex.main.pokemontypes.presentation.navigation.pokemonTypesGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PokedexContent(navController: NavHostController) {
    Scaffold {
        NavHost(
            navController = navController,
            startDestination = PokemonTypesScreenDestination.route
        ) {
            pokemonTypesGraph(
                navigateToPokemonsList = { navController.navigate(PokemonsListScreenDestination.route(it)) }
            )

            pokemonsListGraph()
        }
    }
}
