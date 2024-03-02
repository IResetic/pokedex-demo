package dev.skybit.pokedex.main.pokemontypes.presentation.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.skybit.pokedex.main.core.domain.usecases.PopulatePokemonTypes
import dev.skybit.pokedex.main.core.utils.onError
import dev.skybit.pokedex.main.core.utils.onSuccess
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonTypeScreenViewModel @Inject constructor(
    private val populatePokemonTypes: PopulatePokemonTypes
) : ViewModel() {

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
        }
    }
}
