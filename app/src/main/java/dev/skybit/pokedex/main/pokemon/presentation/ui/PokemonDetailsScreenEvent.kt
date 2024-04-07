package dev.skybit.pokedex.main.pokemon.presentation.ui

sealed interface PokemonDetailsScreenEvent {
    data object ClearErrorMessage : PokemonDetailsScreenEvent
    data object RetryLoading : PokemonDetailsScreenEvent
}
