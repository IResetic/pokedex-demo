package dev.skybit.pokedex.pokemon.presentation.ui

import androidx.lifecycle.SavedStateHandle
import dev.skybit.pokedex.main.core.utils.Resource
import dev.skybit.pokedex.main.core.utils.parseTypeToColor
import dev.skybit.pokedex.main.pokemon.domain.model.fakePokemonDetailsOne
import dev.skybit.pokedex.main.pokemon.domain.usecases.FakeGetPokemonBasicInfo
import dev.skybit.pokedex.main.pokemon.domain.usecases.FakeGetPokemonDetails
import dev.skybit.pokedex.main.pokemon.presentation.model.PokemonDetailsUi
import dev.skybit.pokedex.main.pokemon.presentation.navigation.PokemonDetailsScreenDestination.POKEMON_ID
import dev.skybit.pokedex.main.pokemon.presentation.ui.PokemonDetailsScreenEvent.ClearPokemonErrorMessage
import dev.skybit.pokedex.main.pokemon.presentation.ui.PokemonDetailsScreenViewModel
import dev.skybit.pokedex.main.pokemon.presentation.ui.model.PokemonDetailsDataState
import dev.skybit.pokedex.main.typedetails.domain.model.fakePokemonBasicInfoBulbasaur
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class PokemonDetailsScreenViewModelTest {
    private lateinit var getPokemonBasicInfo: FakeGetPokemonBasicInfo
    private lateinit var getPokemonDetails: FakeGetPokemonDetails
    private lateinit var sut: PokemonDetailsScreenViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun init() {
        Dispatchers.setMain(testDispatcher)
        getPokemonBasicInfo = FakeGetPokemonBasicInfo()
        getPokemonDetails = FakeGetPokemonDetails()
        initSut()
    }

    private fun initSut(savedStateHandle: SavedStateHandle = SavedStateHandle()) {
        sut = PokemonDetailsScreenViewModel(
            savedStateHandle = savedStateHandle,
            getPokemonBasicInfo = getPokemonBasicInfo,
            getPokemonDetails = getPokemonDetails
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cancel()
    }

    @Test
    fun should_successfully_get_pokemon_basic_info() = runBlocking {
        // define test data
        val saveScreenViewModel = SavedStateHandle(mapOf(POKEMON_ID to fakePokemonBasicInfoBulbasaur.id))

        // init sut
        initSut(savedStateHandle = saveScreenViewModel)

        // trigger action
        val actual = sut.pokemonDetailsScreenUiState.first()

        // check assertions
        val expected = parseTypeToColor(fakePokemonBasicInfoBulbasaur.pokemonTypeName)
        assertEquals(expected, actual.backgroundColor)
    }

    @Test
    fun should_successfully_load_pokemon_details_and_update_ui_state() = runBlocking {
        // define test data
        val savedStateHandle = SavedStateHandle(mapOf(POKEMON_ID to fakePokemonBasicInfoBulbasaur.id))
        getPokemonDetails.fakePokemonDetails = Resource.Success(fakePokemonDetailsOne)

        // init sut
        initSut(savedStateHandle)

        // trigger action
        val uiState = sut.pokemonDetailsScreenUiState.first()

        // check assertions
        assertEquals(PokemonDetailsUi.fromDomain(fakePokemonDetailsOne), uiState.pokemonDetails)
        assertEquals("", uiState.errorMessage)
    }

    @Test
    fun should_clear_pokemon_error_message_after_is_consumed() = runBlocking {
        // define test data
        val savedStateHandle = SavedStateHandle(mapOf(POKEMON_ID to fakePokemonBasicInfoBulbasaur.id))
        getPokemonDetails.fakePokemonDetails = Resource.Error("Network Error", fakePokemonDetailsOne)

        // init sut
        initSut(savedStateHandle)
        var errorMessage = sut.pokemonDetailsScreenUiState.first().errorMessage

        // check test data setup
        assertTrue(errorMessage.isNotEmpty())

        // trigger action
        sut.onEvent(ClearPokemonErrorMessage)

        // check assertions
        val actual = sut.pokemonDetailsScreenUiState.first().errorMessage
        assertTrue(actual.isEmpty())
    }

    @Test
    fun should_set_data_state_to_success() = runBlocking {
        // define test data
        val savedStateHandle = SavedStateHandle(mapOf(POKEMON_ID to fakePokemonBasicInfoBulbasaur.id))

        // init sut
        initSut(savedStateHandle)

        // check assertions
        val result = sut.pokemonDetailsScreenUiState.first()
        assertEquals(PokemonDetailsDataState.SUCCESS, result.pokemonDetailsDataState)
    }

    @Test
    fun should_set_data_state_to_error_cached_data_is_not_empty() = runBlocking {
        // define test data
        val savedStateHandle = SavedStateHandle(mapOf(POKEMON_ID to fakePokemonBasicInfoBulbasaur.id))
        getPokemonDetails.fakePokemonDetails = Resource.Error("Network error", fakePokemonDetailsOne)

        // init sut
        initSut(savedStateHandle)

        // check assertions
        val result = sut.pokemonDetailsScreenUiState.first()
        assertEquals(PokemonDetailsDataState.ERROR_CACHED_DATA_IS_NOT_EMPTY, result.pokemonDetailsDataState)
    }

    @Test
    fun should_set_data_state_to_error_cached_data_is_empty() = runBlocking {
        // define test data
        val savedStateHandle = SavedStateHandle(mapOf(POKEMON_ID to fakePokemonBasicInfoBulbasaur.id))
        getPokemonDetails.fakePokemonDetails = Resource.Error("Network error", null)

        // init sut
        initSut(savedStateHandle)

        // check assertions
        val result = sut.pokemonDetailsScreenUiState.first()
        assertEquals(PokemonDetailsDataState.ERROR_CACHED_DATA_IS_EMPTY, result.pokemonDetailsDataState)
    }
}
