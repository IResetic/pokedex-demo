package dev.skybit.pokedex.main.typedetails.presentation.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.skybit.pokedex.main.core.utils.RELOADING_DEBOUNCE_TIME
import dev.skybit.pokedex.main.core.utils.onError
import dev.skybit.pokedex.main.core.utils.onSuccess
import dev.skybit.pokedex.main.typedetails.domain.usecases.GetPokemonTypeBasicInfo
import dev.skybit.pokedex.main.typedetails.domain.usecases.GetPokemonsBasicInfoByTypeIdPaged
import dev.skybit.pokedex.main.typedetails.domain.usecases.PopulatePokemonTypeDetails
import dev.skybit.pokedex.main.typedetails.presentation.model.PokemonBasicInfoUi
import dev.skybit.pokedex.main.typedetails.presentation.model.PokemonTypeBasicInfoUI
import dev.skybit.pokedex.main.typedetails.presentation.navigation.PokemonTypeDetailsScreenDestination.POKEMON_TYPE_ID
import dev.skybit.pokedex.main.typedetails.presentation.ui.PokemonTypeDetailsScreenEvent.ClearErrorMessage
import dev.skybit.pokedex.main.typedetails.presentation.ui.PokemonTypeDetailsScreenEvent.RetryLoadingPokemonTypeDetails
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonTypeDetailsScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getPokemonTypeBasicInfo: GetPokemonTypeBasicInfo,
    private val getPokemonsBasicInfoByTypeIdPaged: GetPokemonsBasicInfoByTypeIdPaged,
    private val populatePokemonTypeDetails: PopulatePokemonTypeDetails
) : ViewModel() {

    private val pokemonTypeId = savedStateHandle.get<String>(POKEMON_TYPE_ID)
    private var fetchPokemonsJob: Job? = null

    val pokemonsBasicInfoPagingSource: Flow<PagingData<PokemonBasicInfoUi>> = pokemonTypeId?.let { typeId ->
        getPokemonsBasicInfoByTypeIdPaged(typeId.toInt()).map { pagingData ->
            pagingData.map { pokemonBasicInfo ->
                PokemonBasicInfoUi.fromDomain(pokemonBasicInfo)
            }
        }.cachedIn(viewModelScope)
    } ?: flowOf(PagingData.empty())

    private var _pokemonsListScreenState = MutableStateFlow(PokemonTypeDetailsUiState())
    val pokemonsListScreenState: StateFlow<PokemonTypeDetailsUiState> = _pokemonsListScreenState.asStateFlow()

    init {
        getBasicPokemonTypeInfo()
        getPokemonTypeDetails()
    }

    fun onEvent(event: PokemonTypeDetailsScreenEvent) {
        when (event) {
            is RetryLoadingPokemonTypeDetails -> {
                tryReloadingPokemonTypeDetails()
            }
            is ClearErrorMessage -> {
                _pokemonsListScreenState.update {
                    it.copy(errorMessage = "")
                }
            }
        }
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

    private fun getPokemonTypeDetails() {
        _pokemonsListScreenState.update {
            it.copy(isLoading = true)
        }

        viewModelScope.launch {
            fetchPokemonsBasicInfo()
        }
    }

    private fun tryReloadingPokemonTypeDetails() {
        _pokemonsListScreenState.update {
            it.copy(isLoading = true)
        }

        fetchPokemonsJob?.cancel()

        fetchPokemonsJob = viewModelScope.launch {
            delay(RELOADING_DEBOUNCE_TIME)

            fetchPokemonsBasicInfo()
        }
    }

    private suspend fun fetchPokemonsBasicInfo() {
        pokemonTypeId?.let { pokemonTypeId ->
            val pokemonsBasicInfo = populatePokemonTypeDetails(pokemonTypeId.toInt())

            pokemonsBasicInfo.onSuccess {
                updatePokemonScreenStateAfterFetching()
            }.onError { message, _ ->
                updatePokemonScreenStateAfterFetching(
                    errorMessage = "Error while fetching pokemon data: $message"
                )
            }
        }
    }

    private fun updatePokemonScreenStateAfterFetching(
        errorMessage: String = ""
    ) {
        _pokemonsListScreenState.update {
            it.copy(
                errorMessage = errorMessage,
                isLoading = false,
                isRefreshing = false
            )
        }
    }
}
