package dev.skybit.pokedex.main.pokemontypes.presentation.ui

sealed class PokemonTypeScreenEvent {
    data object LoadPokemonTypes : PokemonTypeScreenEvent()
    data object ClearErrorMessage : PokemonTypeScreenEvent()
}
