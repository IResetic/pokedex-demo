package dev.skybit.pokedex.typedetails.presentation.ui

import androidx.lifecycle.SavedStateHandle
import dev.skybit.pokedex.main.core.domain.model.fakePokemonTypeGrass
import dev.skybit.pokedex.main.core.domain.usecases.FakeGetPokemonTypeBasicInfo
import dev.skybit.pokedex.main.core.utils.Resource
import dev.skybit.pokedex.main.pokemontypes.domain.usecases.FakeGetPokemonsBasicInfoByTypeId
import dev.skybit.pokedex.main.typedetails.domain.model.fakePokemonBasicInfoBulbasaur
import dev.skybit.pokedex.main.typedetails.domain.model.fakePokemonBasicInfoIvysaur
import dev.skybit.pokedex.main.typedetails.presentation.model.PokemonBasicInfoUi
import dev.skybit.pokedex.main.typedetails.presentation.model.PokemonTypeBasicInfoUI
import dev.skybit.pokedex.main.typedetails.presentation.navigation.PokemonTypeDetailsScreenDestination.POKEMON_TYPE_ID
import dev.skybit.pokedex.main.typedetails.presentation.ui.PokemonTypeDetailsScreenEvent.ClearErrorMessage
import dev.skybit.pokedex.main.typedetails.presentation.ui.PokemonTypeDetailsScreenEvent.RetryLoadingPokemonTypeDetails
import dev.skybit.pokedex.main.typedetails.presentation.ui.PokemonTypeDetailsViewModel
import kotlinx.coroutines.Dispatchers
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

class PokemonsListViewModelTest {
    private lateinit var getPokemonTypeBasicInfo: FakeGetPokemonTypeBasicInfo
    private lateinit var getPokemonsBasicInfoByTypeId: FakeGetPokemonsBasicInfoByTypeId
    private lateinit var sut: PokemonTypeDetailsViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun init() {
        Dispatchers.setMain(testDispatcher)
        getPokemonTypeBasicInfo = FakeGetPokemonTypeBasicInfo()
        getPokemonsBasicInfoByTypeId = FakeGetPokemonsBasicInfoByTypeId()
        initSut()
    }

    private fun initSut(savedStateHandle: SavedStateHandle = SavedStateHandle()) {
        sut = PokemonTypeDetailsViewModel(
            savedStateHandle = savedStateHandle,
            getPokemonTypeBasicInfo = getPokemonTypeBasicInfo,
            getPokemonsBasicInfoByTypeId = getPokemonsBasicInfoByTypeId
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun should_fetch_basic_pokemon_type_info_and_pokemon_details() = runBlocking {
        // define test data
        getPokemonTypeBasicInfo.fakePokemonType = fakePokemonTypeGrass
        val savedStateHandle = SavedStateHandle(mapOf(POKEMON_TYPE_ID to fakePokemonTypeGrass.id.toString()))

        // init sut
        initSut(savedStateHandle)

        // check assertions
        val actual = sut.pokemonsListScreenState.first().pokemonTypeBasicInfo
        val expected = PokemonTypeBasicInfoUI.fromDomain(fakePokemonTypeGrass)
        assertEquals(expected, actual)
    }

    @Test
    fun should_successfully_fetch_pokemon_type_details() = runBlocking {
        // define test data
        val savedStateHandle = SavedStateHandle(mapOf(POKEMON_TYPE_ID to "1"))
        val pokemons = listOf(fakePokemonBasicInfoBulbasaur, fakePokemonBasicInfoIvysaur)
        getPokemonsBasicInfoByTypeId.fakePokemonBasicInfoList = Resource.Success(pokemons)

        // init sut
        initSut(savedStateHandle)

        // check assertions
        val actual = sut.pokemonsListScreenState.first().pokemons
        val expected = pokemons.map { PokemonBasicInfoUi.fromDomain(it) }
        assertEquals(expected, actual)
    }

    @Test
    fun should_handle_error_during_pokemon_type_details_fetching() = runBlocking {
        // define test data
        val networkError = "Network error"
        val savedStateHandle = SavedStateHandle(mapOf(POKEMON_TYPE_ID to "1"))
        getPokemonsBasicInfoByTypeId.fakePokemonBasicInfoList = Resource.Error(networkError)

        // init sut
        initSut(savedStateHandle)

        // check assertions
        val actual = sut.pokemonsListScreenState.first().errorMessage
        assertTrue(actual.contains(networkError))
    }

    @Test
    fun successfully_fetch_pokemon_type_details_after_retry() = runBlocking {
        // define test data
        val pokemons = listOf(fakePokemonBasicInfoBulbasaur)
        val networkError = "Network error"
        val savedStateHandle = SavedStateHandle(mapOf(POKEMON_TYPE_ID to "1"))
        getPokemonsBasicInfoByTypeId.fakePokemonBasicInfoList = Resource.Error(networkError)

        // init sut
        initSut(savedStateHandle)

        // check assertions
        val currentError = sut.pokemonsListScreenState.first().errorMessage
        assertTrue(currentError.contains(networkError))

        // trigger action
        getPokemonsBasicInfoByTypeId.fakePokemonBasicInfoList = Resource.Success(pokemons)
        sut.onEvent(RetryLoadingPokemonTypeDetails)

        testDispatcher.scheduler.advanceUntilIdle()

        // check assertions
        val actual = sut.pokemonsListScreenState.first()
        val expected = pokemons.map { PokemonBasicInfoUi.fromDomain(it) }
        assertEquals(expected, actual.pokemons)
        assertTrue(actual.errorMessage.isEmpty())
    }

    @Test
    fun should_successfully_clear_error_message() = runBlocking {
        // define test data
        val networkError = "Network error"
        val savedStateHandle = SavedStateHandle(mapOf(POKEMON_TYPE_ID to "1"))
        getPokemonsBasicInfoByTypeId.fakePokemonBasicInfoList = Resource.Error(networkError)

        // init sut
        initSut(savedStateHandle)

        // check assertions
        val currentError = sut.pokemonsListScreenState.first().errorMessage
        assertTrue(currentError.contains(networkError))

        // trigger action
        sut.onEvent(ClearErrorMessage)
        testDispatcher.scheduler.advanceUntilIdle()

        // check assertions
        val actual = sut.pokemonsListScreenState.first().errorMessage
        assertTrue(actual.isEmpty())
    }
}
