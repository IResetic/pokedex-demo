package dev.skybit.pokedex.main.pokemon.presentation.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.skybit.pokedex.main.core.utils.RELOADING_DEBOUNCE_TIME
import dev.skybit.pokedex.main.core.utils.onError
import dev.skybit.pokedex.main.core.utils.onSuccess
import dev.skybit.pokedex.main.core.utils.parseTypeToColor
import dev.skybit.pokedex.main.pokemon.domain.usecases.GetPokemonBasicInfo
import dev.skybit.pokedex.main.pokemon.domain.usecases.GetPokemonDetails
import dev.skybit.pokedex.main.pokemon.presentation.model.PokemonDetailsUi
import dev.skybit.pokedex.main.pokemon.presentation.navigation.PokemonDetailsScreenDestination.POKEMON_ID
import dev.skybit.pokedex.main.pokemon.presentation.ui.PokemonDetailsScreenEvent.ClearPokemonErrorMessage
import dev.skybit.pokedex.main.pokemon.presentation.ui.PokemonDetailsScreenEvent.RetryLoadingPokemonDetails
import dev.skybit.pokedex.main.pokemon.presentation.ui.model.PokemonDetailsDataState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
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
    private val getPokemonDetails: GetPokemonDetails
) : ViewModel() {

    private val pokemonId = savedStateHandle.get<Int>(POKEMON_ID)
    private var fetchPokemonDetailsJob: Job? = null

    private var _pokemonDetailsScreenUiState = MutableStateFlow(PokemonDetailsScreenUiState())
    val pokemonDetailsScreenUiState: StateFlow<PokemonDetailsScreenUiState> = _pokemonDetailsScreenUiState.asStateFlow()

    init {
        getPokemonBasicInfo()
        loadPokemonDetails()
    }

    fun onEvent(event: PokemonDetailsScreenEvent) {
        when (event) {
            ClearPokemonErrorMessage -> clearErrorMessage()
            RetryLoadingPokemonDetails -> retryLoadingPokemonDetails()
        }
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

    private fun loadPokemonDetails() {
        _pokemonDetailsScreenUiState.update { currentState ->
            currentState.copy(
                pokemonDetailsDataState = pokemonLoadingDataState()
            )
        }

        fetchPokemonDetailsJob = viewModelScope.launch {
            fetchPokemonDetails()
        }
    }

    private fun retryLoadingPokemonDetails() {
        _pokemonDetailsScreenUiState.update { currentState ->
            currentState.copy(
                pokemonDetailsDataState = pokemonLoadingDataState()
            )
        }

        fetchPokemonDetailsJob?.cancel()

        fetchPokemonDetailsJob = viewModelScope.launch {
            delay(RELOADING_DEBOUNCE_TIME)
            fetchPokemonDetails()
        }
    }

    private suspend fun fetchPokemonDetails() {
        pokemonId?.let { id ->
            getPokemonDetails(id).onSuccess { pokemonDetails ->
                _pokemonDetailsScreenUiState.update { currentState ->
                    currentState.copy(
                        errorMessage = "",
                        pokemonDetails = PokemonDetailsUi.fromDomain(pokemonDetails)
                    )
                }
            }.onError { message, pokemonDetails ->
                val pokemonDetailsUi = if (pokemonDetails != null) PokemonDetailsUi.fromDomain(pokemonDetails) else null

                _pokemonDetailsScreenUiState.update { currentState ->
                    currentState.copy(
                        errorMessage = message ?: "",
                        pokemonDetails = pokemonDetailsUi
                    )
                }
            }

            setPokemonDetailsDataStateAfterFetching()
        }
    }

    private fun pokemonLoadingDataState(): PokemonDetailsDataState {
        return if (_pokemonDetailsScreenUiState.value.pokemonDetails != null) {
            PokemonDetailsDataState.LOADING_CASHED_DATA_IS_NOT_EMPTY
        } else {
            PokemonDetailsDataState.LOADING_CASHED_DATA_IS_EMPTY
        }
    }

    private fun setPokemonDetailsDataStateAfterFetching() {
        val currentState = _pokemonDetailsScreenUiState.value
        val newDataState = when {
            currentState.pokemonDetails != null && currentState.errorMessage.isNotEmpty() -> {
                PokemonDetailsDataState.ERROR_CACHED_DATA_IS_NOT_EMPTY
            }
            currentState.pokemonDetails == null && currentState.errorMessage.isNotEmpty() -> {
                PokemonDetailsDataState.ERROR_CACHED_DATA_IS_EMPTY
            }
            currentState.pokemonDetails == null && currentState.errorMessage.isEmpty() -> {
                PokemonDetailsDataState.NO_DATA_TO_SHOW
            }
            else -> {
                PokemonDetailsDataState.SUCCESS
            }
        }

        _pokemonDetailsScreenUiState.update { currentState ->
            currentState.copy(pokemonDetailsDataState = newDataState)
        }
    }

    private fun clearErrorMessage() {
        _pokemonDetailsScreenUiState.update {
            it.copy(errorMessage = "")
        }
    }
}
