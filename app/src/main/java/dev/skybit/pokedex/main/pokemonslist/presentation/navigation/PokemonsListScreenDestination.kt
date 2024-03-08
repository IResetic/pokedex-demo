package dev.skybit.pokedex.main.pokemonslist.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.skybit.pokedex.main.core.presentation.navigation.NavigationDestination
import dev.skybit.pokedex.main.pokemonslist.presentation.ui.PokemonsListRoute

object PokemonsListScreenDestination : NavigationDestination {
    const val POKEMON_TYPE_ID = "pokemon_type_id"
    override val route: String = "pokemons-list-screen-route?$POKEMON_TYPE_ID={$POKEMON_TYPE_ID}"
    override val destination: String = "pokemons-list-screen-destination"

    fun route(pokemonTypeId: Int): String {
        return route.replace("{$POKEMON_TYPE_ID}", pokemonTypeId.toString())
    }
}

fun NavGraphBuilder.pokemonsListGraph() {
    composable(
        route = PokemonsListScreenDestination.route,
        arguments = listOf(
            navArgument(PokemonsListScreenDestination.POKEMON_TYPE_ID) { type = NavType.StringType }
        )
    ) {
        PokemonsListRoute()
    }
}
