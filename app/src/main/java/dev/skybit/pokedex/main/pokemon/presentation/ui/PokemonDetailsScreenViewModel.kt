package dev.skybit.pokedex.main.pokemon.presentation.ui

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.skybit.pokedex.main.core.utils.parseTypeToColor
import dev.skybit.pokedex.main.pokemon.domain.usecases.GetPokemonBasicInfo
import dev.skybit.pokedex.main.pokemon.domain.usecases.GetPokemonDetailsById
import dev.skybit.pokedex.main.pokemon.domain.usecases.PopulatePokemonDetails
import dev.skybit.pokedex.main.pokemon.presentation.model.PokemonDetailsUi
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
    private val getPokemonBasicInfo: GetPokemonBasicInfo,
    private val getPokemonDetailsById: GetPokemonDetailsById,
    private val populatePokemonDetails: PopulatePokemonDetails
) : ViewModel() {

    private val pokemonId = savedStateHandle.get<Int>(POKEMON_ID)

    private var _pokemonDetailsScreenUiState = MutableStateFlow(PokemonDetailsScreenUiState())
    val pokemonDetailsScreenUiState: StateFlow<PokemonDetailsScreenUiState> = _pokemonDetailsScreenUiState.asStateFlow()

    init {
        getPokemonBasicInfo()
        populatePokemonDetailsById()
        observePokemonDetails()
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

    private fun populatePokemonDetailsById() {
        viewModelScope.launch {
            pokemonId?.let { id ->
                val result = populatePokemonDetails(id)
                Log.d("PokemonDetailsScreenViewModel", "PopulatePokemonDetails: $result")
            }
        }
    }

    private fun observePokemonDetails() {
        viewModelScope.launch {
            pokemonId?.let { id ->
                getPokemonDetailsById(id).collect { pokemonDetails ->
                    val pokemonDetailsUi = pokemonDetails?.let { PokemonDetailsUi.fromDomain(it) }
                    _pokemonDetailsScreenUiState.update { currentState ->
                        currentState.copy(pokemonDetails = pokemonDetailsUi)
                    }
                }
            }
        }
    }
}
