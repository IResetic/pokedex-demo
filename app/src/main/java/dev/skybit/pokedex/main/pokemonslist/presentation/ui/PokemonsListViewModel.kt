package dev.skybit.pokedex.main.pokemonslist.presentation.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.skybit.pokedex.main.core.domain.usecases.GetPokemonTypeBasicInfo
import dev.skybit.pokedex.main.pokemonslist.presentation.model.PokemonsListBasicInfoUI
import dev.skybit.pokedex.main.pokemonslist.presentation.navigation.PokemonsListScreenDestination.POKEMON_TYPE_ID
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonsListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getPokemonTypeBasicInfo: GetPokemonTypeBasicInfo
) : ViewModel() {
    private val pokemonTypeId = savedStateHandle.get<String>(POKEMON_TYPE_ID)

    private var _pokemonsListScreenState = MutableStateFlow(PokemonsListUiState())
    val pokemonsListScreenState: StateFlow<PokemonsListUiState> = _pokemonsListScreenState.asStateFlow()

    init {
        getBasicPokemonTypeInfo()
    }

    private fun getBasicPokemonTypeInfo() {
        viewModelScope.launch {
            pokemonTypeId?.let { pokemonTypeId ->
                val pokemonTypeBasicInfo = getPokemonTypeBasicInfo(pokemonTypeId.toInt())

                _pokemonsListScreenState.update {
                    it.copy(pokemonTypeBasicInfo = PokemonsListBasicInfoUI.fromDomain(pokemonTypeBasicInfo))
                }
            }
        }
    }
}
