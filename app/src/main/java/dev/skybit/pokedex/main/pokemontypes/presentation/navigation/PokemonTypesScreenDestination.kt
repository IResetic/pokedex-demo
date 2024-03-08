package dev.skybit.pokedex.main.pokemontypes.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.skybit.pokedex.main.core.presentation.navigation.NavigationDestination
import dev.skybit.pokedex.main.pokemontypes.presentation.ui.PokemonTypesRoute

object PokemonTypesScreenDestination : NavigationDestination {
    override val route: String = "pokemon-types-screen-route"
    override val destination: String = "pokemon-types-screen-destination"
}

fun NavGraphBuilder.pokemonTypesGraph(
    navigateToPokemonsList: (Int) -> Unit
) {
    composable(route = PokemonTypesScreenDestination.route) {
        PokemonTypesRoute(navigateToPokemonsList = navigateToPokemonsList)
    }
}
