package dev.skybit.pokedex.main.pokemontypes.presentation.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.skybit.pokedex.main.core.utils.onError
import dev.skybit.pokedex.main.core.utils.onSuccess
import dev.skybit.pokedex.main.pokemontypes.domain.usecases.GetPokemonTypes
import dev.skybit.pokedex.main.pokemontypes.domain.usecases.PopulatePokemonTypes
import dev.skybit.pokedex.main.pokemontypes.presentation.model.PokemonTypeUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonTypeScreenViewModel @Inject constructor(
    private val populatePokemonTypes: PopulatePokemonTypes,
    private val getPokemonTypes: GetPokemonTypes
) : ViewModel() {

    private var _pokemonTypeScreenState = MutableStateFlow(PokemonTypScreenUIState())
    val pokemonTypeScreenState: StateFlow<PokemonTypScreenUIState> = _pokemonTypeScreenState.asStateFlow()

    init {
        onEvent(PokemonTypeScreenEvent.LoadPokemonTypes)
    }

    fun onEvent(event: PokemonTypeScreenEvent) {
        when (event) {
            is PokemonTypeScreenEvent.LoadPokemonTypes -> { loadPokemonTypes() }
        }
    }

    private fun loadPokemonTypes() {
        viewModelScope.launch {
            populatePokemonTypes().onSuccess {
                Log.d("PokemonTypeScreenViewModel", "Pokemon types loaded successfully")
            }.onError { message: String?, _ ->
                Log.d("PokemonTypeScreenViewModel", "Error loading pokemon types $message")
            }

            setPokemonTypes()
        }
    }

    private fun setPokemonTypes() {
        viewModelScope.launch {
            val pokemonTypes = getPokemonTypes()

            _pokemonTypeScreenState.update {
                it.copy(pokemonTypes = PokemonTypeUI.fromDomainList(pokemonTypes))
            }
        }
    }
}
