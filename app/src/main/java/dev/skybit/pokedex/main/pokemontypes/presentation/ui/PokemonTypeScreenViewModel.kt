package dev.skybit.pokedex.main.pokemontypes.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.skybit.pokedex.main.core.utils.onError
import dev.skybit.pokedex.main.core.utils.onSuccess
import dev.skybit.pokedex.main.pokemontypes.domain.usecases.GetPokemonTypes
import dev.skybit.pokedex.main.pokemontypes.presentation.model.PokemonTypeUI
import dev.skybit.pokedex.main.pokemontypes.presentation.ui.PokemonTypeScreenEvent.ClearErrorMessage
import dev.skybit.pokedex.main.pokemontypes.presentation.ui.PokemonTypeScreenEvent.LoadPokemonTypes
import dev.skybit.pokedex.main.pokemontypes.presentation.ui.PokemonTypeScreenEvent.RetryLoadingOfPokemonTypes
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

    private var _pokemonTypeScreenState = MutableStateFlow(PokemonTypScreenUIState())
    val pokemonTypeScreenState: StateFlow<PokemonTypScreenUIState> = _pokemonTypeScreenState.asStateFlow()

    init {
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
                it.copy(isLoading = true, errorMessage = "")
            }

            getPokemonTypes().onSuccess { pokemonTypes ->
                _pokemonTypeScreenState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "",
                        pokemonTypes = PokemonTypeUI.fromDomainList(pokemonTypes)
                    )
                }
            }.onError { message, pokemonTypes ->
                _pokemonTypeScreenState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "Error loading pokemon types $message",
                        pokemonTypes = PokemonTypeUI.fromDomainList(pokemonTypes ?: emptyList())
                    )
                }
            }
        }
    }

    private fun clearErrorMessage() {
        _pokemonTypeScreenState.update {
            it.copy(errorMessage = "")
        }
    }
}
