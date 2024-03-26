@file:OptIn(ExperimentalCoroutinesApi::class)

package dev.skybit.pokedex.pokemontype.presentation.ui

import dev.skybit.pokedex.main.core.domain.model.fakePokemonTypeFire
import dev.skybit.pokedex.main.core.domain.model.fakePokemonTypeGrass
import dev.skybit.pokedex.main.core.utils.Resource
import dev.skybit.pokedex.main.core.utils.Resource.Error
import dev.skybit.pokedex.main.pokemontypes.domain.usecases.FakeGetPokemonTypes
import dev.skybit.pokedex.main.pokemontypes.presentation.model.PokemonTypeUi
import dev.skybit.pokedex.main.pokemontypes.presentation.ui.PokemonTypeScreenEvent.ClearErrorMessage
import dev.skybit.pokedex.main.pokemontypes.presentation.ui.PokemonTypeScreenEvent.RetryLoadingOfPokemonTypes
import dev.skybit.pokedex.main.pokemontypes.presentation.ui.PokemonTypeScreenViewModel
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

class PokemonTypeScreenViewModelTest {
    private lateinit var getPokemonTypes: FakeGetPokemonTypes
    private lateinit var sut: PokemonTypeScreenViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun init() {
        Dispatchers.setMain(testDispatcher)
        getPokemonTypes = FakeGetPokemonTypes()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun initSut() {
        sut = PokemonTypeScreenViewModel(getPokemonTypes)
    }

    @Test
    fun should_handle_error_when_trying_to_fetch_pokemon_types() = runBlocking {
        // define test data
        val errorMessage = "Network Error"
        getPokemonTypes.fakeResult = Error(errorMessage)

        // init sut
        initSut()
        testDispatcher.scheduler.advanceUntilIdle()

        // trigger action
        val actual = sut.pokemonTypeScreenState.first().errorMessage

        // check assertions
        assertTrue(actual.contains(errorMessage))
    }

    @Test
    fun should_update_state_with_pokemon_types() = runBlocking {
        // define test data
        val pokemonTypes = listOf(fakePokemonTypeGrass, fakePokemonTypeFire)
        getPokemonTypes.fakeResult = Resource.Success(pokemonTypes)

        // init state
        initSut()

        // trigger action
        val actual = sut.pokemonTypeScreenState.first().pokemonTypes

        // check assertions
        val expected = PokemonTypeUi.fromDomainList(pokemonTypes)
        assertEquals(expected, actual)
    }

    @Test
    fun should_clear_error_message_after_is_consumed() = runBlocking {
        // define test data
        getPokemonTypes.fakeResult = Error("Network Error", emptyList())

        // init sut
        initSut()

        // trigger action
        sut.onEvent(ClearErrorMessage)

        // check assertions
        val actual = sut.pokemonTypeScreenState.first().errorMessage
        assertTrue(actual.isEmpty())
    }

    @Test
    fun should_fetch_cashed_pokemon_types_in_case_of_an_error() = runBlocking {
        // define test data
        val pokemonTypes = listOf(fakePokemonTypeGrass, fakePokemonTypeFire)
        getPokemonTypes.fakeResult = Error("Network Error", pokemonTypes)

        // init sut
        initSut()

        // check assertions
        val actual = sut.pokemonTypeScreenState.first()
        assertTrue(actual.errorMessage.contains("Network Error"))
        assertEquals(PokemonTypeUi.fromDomainList(pokemonTypes), actual.pokemonTypes)
    }

    @Test
    fun should_retry_loading_pokemon_types_in_case_of_the_retry_event() = runBlocking {
        // define test data
        val pokemonTypes = listOf(fakePokemonTypeGrass, fakePokemonTypeFire)
        getPokemonTypes.fakeResult = Error("Network Error", emptyList())

        // init sut
        initSut()

        // check assertions
        var actual = sut.pokemonTypeScreenState.first()
        assertTrue(actual.errorMessage.contains("Network Error"))
        assertTrue(actual.pokemonTypes.isEmpty())

        // trigger action
        getPokemonTypes.fakeResult = Resource.Success(pokemonTypes)
        sut.onEvent(RetryLoadingOfPokemonTypes)
        testDispatcher.scheduler.advanceUntilIdle()

        // check assertions
        actual = sut.pokemonTypeScreenState.first()
        assertTrue(actual.errorMessage.isEmpty())
        assertEquals(PokemonTypeUi.fromDomainList(pokemonTypes), actual.pokemonTypes)
    }
}
