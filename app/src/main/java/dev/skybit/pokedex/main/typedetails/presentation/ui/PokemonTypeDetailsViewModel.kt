package dev.skybit.pokedex.main.typedetails.presentation.ui

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.skybit.pokedex.main.core.domain.usecases.GetPokemonTypeBasicInfo
import dev.skybit.pokedex.main.core.utils.onError
import dev.skybit.pokedex.main.core.utils.onSuccess
import dev.skybit.pokedex.main.typedetails.domain.usecases.GetPokemonsBasicInfoByTypeId
import dev.skybit.pokedex.main.typedetails.presentation.model.PokemonTypeBasicInfoUI
import dev.skybit.pokedex.main.typedetails.presentation.navigation.PokemonTypeDetailsScreenDestination.POKEMON_TYPE_ID
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonTypeDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getPokemonTypeBasicInfo: GetPokemonTypeBasicInfo,
    private val getPokemonsBasicInfoByTypeId: GetPokemonsBasicInfoByTypeId
) : ViewModel() {
    private val pokemonTypeId = savedStateHandle.get<String>(POKEMON_TYPE_ID)

    private var _pokemonsListScreenState = MutableStateFlow(PokemonTypeDetailsUiState())
    val pokemonsListScreenState: StateFlow<PokemonTypeDetailsUiState> = _pokemonsListScreenState.asStateFlow()

    init {
        getBasicPokemonTypeInfo()
        getPokemonsBasicInfo()
    }

    private fun getBasicPokemonTypeInfo() {
        viewModelScope.launch {
            pokemonTypeId?.let { pokemonTypeId ->
                val pokemonTypeBasicInfo = getPokemonTypeBasicInfo(pokemonTypeId.toInt())

                _pokemonsListScreenState.update {
                    it.copy(pokemonTypeBasicInfo = PokemonTypeBasicInfoUI.fromDomain(pokemonTypeBasicInfo))
                }
            }
        }
    }

    private fun getPokemonsBasicInfo() {
        viewModelScope.launch {
            pokemonTypeId?.let { pokemonTypeId ->
                val pokemonsBasicInfo = getPokemonsBasicInfoByTypeId(pokemonTypeId.toInt())

                pokemonsBasicInfo.onSuccess { pokemons ->
                    Log.d("TEST_POKEMONS", "Pokemons: $pokemons")
                    _pokemonsListScreenState.update {
                        it.copy(pokemonsBasicInfo = pokemons)
                    }
                }.onError { message, pokemons ->
                    _pokemonsListScreenState.update {
                        it.copy(
                            pokemonsBasicInfo = pokemons ?: emptyList(),
                            errorMessage = message ?: ""
                        )
                    }
                }
            }
        }
    }
}
