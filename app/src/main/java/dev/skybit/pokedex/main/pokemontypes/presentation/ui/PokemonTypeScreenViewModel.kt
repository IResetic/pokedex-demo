package dev.skybit.pokedex.main.pokemontypes.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.skybit.pokedex.main.core.domain.model.PokemonType
import dev.skybit.pokedex.main.core.utils.RELOADING_DEBOUNCE_TIME
import dev.skybit.pokedex.main.core.utils.onError
import dev.skybit.pokedex.main.core.utils.onSuccess
import dev.skybit.pokedex.main.pokemontypes.domain.usecases.GetPokemonTypes
import dev.skybit.pokedex.main.pokemontypes.presentation.model.PokemonTypeUi
import dev.skybit.pokedex.main.pokemontypes.presentation.ui.PokemonTypeScreenEvent.ClearErrorMessage
import dev.skybit.pokedex.main.pokemontypes.presentation.ui.PokemonTypeScreenEvent.LoadPokemonTypes
import dev.skybit.pokedex.main.pokemontypes.presentation.ui.PokemonTypeScreenEvent.RetryLoadingOfPokemonTypes
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonTypeScreenViewModel @Inject constructor(
    private val getPokemonTypes: GetPokemonTypes
) : ViewModel() {
    private var fetchPokemonTypesJob: Job? = null

    private var _pokemonTypeScreenState = MutableStateFlow(PokemonTypScreenUIState())
    val pokemonTypeScreenState: StateFlow<PokemonTypScreenUIState> = _pokemonTypeScreenState.asStateFlow()

    init {
        onEvent(LoadPokemonTypes)
    }

    fun onEvent(event: PokemonTypeScreenEvent) {
        when (event) {
            is LoadPokemonTypes -> { loadPokemonTypes() }
            is ClearErrorMessage -> { clearErrorMessage() }
            is RetryLoadingOfPokemonTypes -> { retryLoadingPokemonTypes() }
        }
    }

    private fun loadPokemonTypes() {
        _pokemonTypeScreenState.update {
            it.copy(isLoading = true, errorMessage = "")
        }

        viewModelScope.launch {
            fetchPokemonTypes()
        }
    }

    private fun retryLoadingPokemonTypes() {
        _pokemonTypeScreenState.update {
            it.copy(isLoading = true, errorMessage = "")
        }

        fetchPokemonTypesJob?.cancel()

        fetchPokemonTypesJob = viewModelScope.launch {
            delay(RELOADING_DEBOUNCE_TIME)
            fetchPokemonTypes()
        }
    }

    private suspend fun fetchPokemonTypes() {
        getPokemonTypes().onSuccess { pokemonTypes ->
            updatePokemonTypesScreenStateAfterFetching(pokemonTypes = pokemonTypes)
        }.onError { message, pokemonTypes ->
            updatePokemonTypesScreenStateAfterFetching(
                pokemonTypes = pokemonTypes ?: emptyList(),
                errorMessage = "Error loading pokemon types: $message"
            )
        }
    }

    private fun updatePokemonTypesScreenStateAfterFetching(
        pokemonTypes: List<PokemonType> = emptyList(),
        errorMessage: String = ""
    ) {
        _pokemonTypeScreenState.update {
            it.copy(
                isLoading = false,
                errorMessage = errorMessage,
                pokemonTypes = PokemonTypeUi.fromDomainList(pokemonTypes)
            )
        }
    }

    private fun clearErrorMessage() {
        _pokemonTypeScreenState.update {
            it.copy(errorMessage = "")
        }
    }
}
