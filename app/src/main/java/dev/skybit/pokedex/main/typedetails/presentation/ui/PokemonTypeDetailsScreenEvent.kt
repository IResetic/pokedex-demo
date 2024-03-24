package dev.skybit.pokedex.main.typedetails.presentation.ui

sealed interface PokemonTypeDetailsScreenEvent {
    data object RetryLoadingPokemonTypeDetails : PokemonTypeDetailsScreenEvent
    data object ClearErrorMessage : PokemonTypeDetailsScreenEvent
}
