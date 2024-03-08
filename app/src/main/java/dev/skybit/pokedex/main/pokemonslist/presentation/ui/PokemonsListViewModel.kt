package dev.skybit.pokedex.main.pokemonslist.presentation.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.skybit.pokedex.main.pokemonslist.presentation.navigation.PokemonsListScreenDestination.POKEMON_TYPE_ID
import javax.inject.Inject

@HiltViewModel
class PokemonsListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val pokemonTypeId = savedStateHandle.get<String>(POKEMON_TYPE_ID)
}
