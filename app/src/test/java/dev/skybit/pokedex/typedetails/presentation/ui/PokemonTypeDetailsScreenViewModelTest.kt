package dev.skybit.pokedex.typedetails.presentation.ui

import androidx.lifecycle.SavedStateHandle
import androidx.paging.testing.asSnapshot
import dev.skybit.pokedex.main.core.domain.model.fakePokemonTypeGrass
import dev.skybit.pokedex.main.core.utils.Resource
import dev.skybit.pokedex.main.typedetails.domain.model.fakePokemonBasicInfoBulbasaur
import dev.skybit.pokedex.main.typedetails.domain.model.fakePokemonBasicInfoIvysaur
import dev.skybit.pokedex.main.typedetails.domain.usecases.FakeGetPokemonTypeBasicInfo
import dev.skybit.pokedex.main.typedetails.domain.usecases.FakeGetPokemonsBasicInfoByTypeIdPaged
import dev.skybit.pokedex.main.typedetails.domain.usecases.FakePopulatePokemonTypeDetails
import dev.skybit.pokedex.main.typedetails.presentation.model.PokemonBasicInfoUi
import dev.skybit.pokedex.main.typedetails.presentation.model.PokemonTypeBasicInfoUI
import dev.skybit.pokedex.main.typedetails.presentation.navigation.PokemonTypeDetailsScreenDestination.POKEMON_TYPE_ID
import dev.skybit.pokedex.main.typedetails.presentation.ui.PokemonTypeDetailsScreenEvent.ClearErrorMessage
import dev.skybit.pokedex.main.typedetails.presentation.ui.PokemonTypeDetailsScreenEvent.RetryLoadingPokemonTypeDetails
import dev.skybit.pokedex.main.typedetails.presentation.ui.PokemonTypeDetailsScreenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

@OptIn(ExperimentalCoroutinesApi::class)
class PokemonTypeDetailsScreenViewModelTest {
    private lateinit var getPokemonTypeBasicInfo: FakeGetPokemonTypeBasicInfo
    private lateinit var getPokemonsBasicInfoByTypeIdPaged: FakeGetPokemonsBasicInfoByTypeIdPaged
    private lateinit var populatePokemonTypeDetails: FakePopulatePokemonTypeDetails

    private lateinit var sut: PokemonTypeDetailsScreenViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun init() {
        Dispatchers.setMain(testDispatcher)
        getPokemonTypeBasicInfo = FakeGetPokemonTypeBasicInfo()
        getPokemonsBasicInfoByTypeIdPaged = FakeGetPokemonsBasicInfoByTypeIdPaged()
        populatePokemonTypeDetails = FakePopulatePokemonTypeDetails()

        initSut()
    }

    private fun initSut(savedStateHandle: SavedStateHandle = SavedStateHandle()) {
        sut = PokemonTypeDetailsScreenViewModel(
            savedStateHandle = savedStateHandle,
            getPokemonTypeBasicInfo = getPokemonTypeBasicInfo,
            getPokemonsBasicInfoByTypeIdPaged = getPokemonsBasicInfoByTypeIdPaged,
            populatePokemonTypeDetails = populatePokemonTypeDetails
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
    fun should_successfully_populate_pokemon_type_details() = runBlocking {
        // define test data
        val savedStateHandle = SavedStateHandle(mapOf(POKEMON_TYPE_ID to "1"))
        populatePokemonTypeDetails.fakePokemonBasicInfoList = Resource.Success(Unit)

        // init sut
        initSut(savedStateHandle)

        // check assertions
        val actual = sut.pokemonsListScreenState.first()
        assertTrue(actual.errorMessage.isEmpty())
    }

    @Test
    fun should_handle_error_return_after_trying_to_populate_pokemon_type_details() = runBlocking {
        // define test data
        val networkError = "Network error"
        val savedStateHandle = SavedStateHandle(mapOf(POKEMON_TYPE_ID to "1"))
        populatePokemonTypeDetails.fakePokemonBasicInfoList = Resource.Error(networkError)

        // init sut
        initSut(savedStateHandle)

        // check assertions
        val actual = sut.pokemonsListScreenState.first().errorMessage
        assertTrue(actual.contains(networkError))
    }

    @Test
    fun successfully_fetch_pokemon_type_details_after_retry() = runBlocking {
        // define test data
        val networkError = "Network error"
        val savedStateHandle = SavedStateHandle(mapOf(POKEMON_TYPE_ID to "1"))
        populatePokemonTypeDetails.fakePokemonBasicInfoList = Resource.Error(networkError)

        // init sut
        initSut(savedStateHandle)

        // check assertions
        val currentError = sut.pokemonsListScreenState.first().errorMessage
        assertTrue(currentError.contains(networkError))

        // trigger action
        populatePokemonTypeDetails.fakePokemonBasicInfoList = Resource.Success(Unit)
        sut.onEvent(RetryLoadingPokemonTypeDetails)

        testDispatcher.scheduler.advanceUntilIdle()

        // check assertions
        val actual = sut.pokemonsListScreenState.first()
        assertTrue(actual.errorMessage.isEmpty())
    }

    @Test
    fun should_successfully_clear_error_message() = runBlocking {
        // define test data
        val networkError = "Network error"
        val savedStateHandle = SavedStateHandle(mapOf(POKEMON_TYPE_ID to "1"))
        populatePokemonTypeDetails.fakePokemonBasicInfoList = Resource.Error(networkError)

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

    @Test
    fun should_successfully_emit_pokemons_basic_info_paging_source() = runBlocking {
        // define test data
        val savedStateHandle = SavedStateHandle(mapOf(POKEMON_TYPE_ID to "1"))
        val pokemons = listOf(fakePokemonBasicInfoBulbasaur, fakePokemonBasicInfoIvysaur)
        getPokemonsBasicInfoByTypeIdPaged.fakePokemonBasicInfoData = pokemons
        val expected = pokemons.map { PokemonBasicInfoUi.fromDomain(it) }

        // init sut
        initSut(savedStateHandle)

        // check assertions
        val actual = sut.pokemonsBasicInfoPagingSource.asSnapshot()
        assertEquals(expected, actual)
    }
}
