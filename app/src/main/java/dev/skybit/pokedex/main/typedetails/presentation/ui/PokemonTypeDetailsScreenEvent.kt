package dev.skybit.pokedex.main.typedetails.presentation.ui

sealed interface PokemonTypeDetailsScreenEvent {
    data object RefreshPokemonTypeDetails : PokemonTypeDetailsScreenEvent
    data object ClearErrorMessage : PokemonTypeDetailsScreenEvent
}
