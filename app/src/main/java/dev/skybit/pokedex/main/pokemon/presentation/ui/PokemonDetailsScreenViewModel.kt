package dev.skybit.pokedex.main.pokemon.presentation.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.skybit.pokedex.main.core.utils.parseTypeToColor
import dev.skybit.pokedex.main.pokemon.domain.usecases.GetPokemonBasicInfo
import dev.skybit.pokedex.main.pokemon.presentation.navigation.PokemonDetailsScreenDestination.POKEMON_ID
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getPokemonBasicInfo: GetPokemonBasicInfo
) : ViewModel() {

    private val pokemonId = savedStateHandle.get<Int>(POKEMON_ID)

    private var _pokemonDetailsScreenUiState = MutableStateFlow(PokemonDetailsScreenUiState())
    val pokemonDetailsScreenUiState: StateFlow<PokemonDetailsScreenUiState> = _pokemonDetailsScreenUiState.asStateFlow()

    init {
        getPokemonBasicInfo()
    }

    private fun getPokemonBasicInfo() {
        viewModelScope.launch {
            pokemonId?.let { id ->
                val basicPokemonInfo = getPokemonBasicInfo(id)
                val backgroundColor = parseTypeToColor(basicPokemonInfo.pokemonTypeName)

                _pokemonDetailsScreenUiState.update {
                    it.copy(backgroundColor = backgroundColor)
                }
            }
        }
    }
}
