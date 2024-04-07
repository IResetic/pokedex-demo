package dev.skybit.pokedex.main.pokemon.presentation.ui.model

enum class PokemonDetailsDataState {
    LOADING_CASHED_DATA_IS_EMPTY,
    LOADING_CASHED_DATA_IS_NOT_EMPTY,
    SUCCESS,
    ERROR_CACHED_DATA_IS_EMPTY,
    ERROR_CACHED_DATA_IS_NOT_EMPTY,
    NO_DATA_TO_SHOW
}
