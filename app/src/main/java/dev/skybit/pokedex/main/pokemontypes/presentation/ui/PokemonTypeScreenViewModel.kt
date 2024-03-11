package dev.skybit.pokedex.main.pokemontypes.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.skybit.pokedex.main.core.utils.onError
import dev.skybit.pokedex.main.core.utils.onSuccess
import dev.skybit.pokedex.main.pokemontypes.domain.usecases.PopulatePokemonTypes
import dev.skybit.pokedex.main.pokemontypes.domain.usecases.StartPokemonTypesListener
import dev.skybit.pokedex.main.pokemontypes.presentation.model.PokemonTypeUI
import dev.skybit.pokedex.main.pokemontypes.presentation.ui.PokemonTypeScreenEvent.ClearErrorMessage
import dev.skybit.pokedex.main.pokemontypes.presentation.ui.PokemonTypeScreenEvent.LoadPokemonTypes
import dev.skybit.pokedex.main.pokemontypes.presentation.ui.PokemonTypeScreenEvent.RetryLoadingOfPokemonTypes
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonTypeScreenViewModel @Inject constructor(
    private val populatePokemonTypes: PopulatePokemonTypes,
    private val startPokemonTypesListener: StartPokemonTypesListener
) : ViewModel() {

    private var _pokemonTypeScreenState = MutableStateFlow(PokemonTypScreenUIState())
    val pokemonTypeScreenState: StateFlow<PokemonTypScreenUIState> = _pokemonTypeScreenState.asStateFlow()

    init {
        startListener()
        onEvent(LoadPokemonTypes)
    }

    fun onEvent(event: PokemonTypeScreenEvent) {
        when (event) {
            is LoadPokemonTypes -> { loadPokemonTypes() }
            is ClearErrorMessage -> { clearErrorMessage() }
            is RetryLoadingOfPokemonTypes -> { loadPokemonTypes() }
        }
    }

    private fun loadPokemonTypes() {
        viewModelScope.launch {
            _pokemonTypeScreenState.update {
                it.copy(isLoading = true, error = "")
            }

            delay(5000)
            populatePokemonTypes().onSuccess {
                _pokemonTypeScreenState.update {
                    it.copy(isLoading = false, error = "")
                }
            }.onError { message: String?, _ ->
                _pokemonTypeScreenState.update {
                    it.copy(isLoading = false, error = "Error loading pokemon types $message")
                }
            }
        }
    }

    private fun startListener() {
        viewModelScope.launch {
            val pokemonTypesFlow = startPokemonTypesListener()

            pokemonTypesFlow.collect { pokemonTypes ->
                _pokemonTypeScreenState.update {
                    it.copy(pokemonTypes = PokemonTypeUI.fromDomainList(pokemonTypes))
                }
            }
        }
    }

    private fun clearErrorMessage() {
        _pokemonTypeScreenState.update {
            it.copy(error = "")
        }
    }
}
