package dev.skybit.pokedex.main.pokemon.presentation.ui

sealed interface PokemonDetailsScreenEvent {
    data object ClearPokemonErrorMessage : PokemonDetailsScreenEvent
    data object RetryLoadingPokemonDetails : PokemonDetailsScreenEvent
}
