package dev.skybit.pokedex.main.pokemon.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.skybit.pokedex.main.core.presentation.navigation.NavigationDestination
import dev.skybit.pokedex.main.pokemon.presentation.ui.PokemonDetailsRoute

object PokemonDetailsScreenDestination : NavigationDestination {
    const val POKEMON_ID = "pokemon_id"
    override val route: String = "pokemon-details-screen-route?$POKEMON_ID={$POKEMON_ID}"
    override val destination: String = "pokemon-details-screen-destination"

    fun route(pokemonId: Int): String {
        return route.replace("{$POKEMON_ID}", pokemonId.toString())
    }
}

fun NavGraphBuilder.pokemonDetailsGraph() {
    composable(
        route = PokemonDetailsScreenDestination.route,
        arguments = listOf(
            navArgument(PokemonDetailsScreenDestination.POKEMON_ID) { type = NavType.IntType }
        )
    ) {
        PokemonDetailsRoute()
    }
}
