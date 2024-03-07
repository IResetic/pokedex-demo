package dev.skybit.pokedex.pokemontype.presentation.ui

import dev.skybit.pokedex.main.core.utils.Resource
import dev.skybit.pokedex.main.core.utils.Resource.Error
import dev.skybit.pokedex.main.pokemontypes.domain.usecases.FakeGetPokemonTypes
import dev.skybit.pokedex.main.pokemontypes.domain.usecases.FakePopulatePokemonTypes
import dev.skybit.pokedex.main.pokemontypes.presentation.model.PokemonTypeUI
import dev.skybit.pokedex.main.pokemontypes.presentation.ui.PokemonTypeScreenViewModel
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

class PokemonTypeScreenViewModelTest {
    private lateinit var fakePopulatePokemonTypes: FakePopulatePokemonTypes
    private lateinit var fakeGetPokemonTypes: FakeGetPokemonTypes
    private lateinit var sut: PokemonTypeScreenViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun init() {
        Dispatchers.setMain(testDispatcher)
        fakePopulatePokemonTypes = FakePopulatePokemonTypes()
        fakeGetPokemonTypes = FakeGetPokemonTypes()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun initSut() {
        sut = PokemonTypeScreenViewModel(fakePopulatePokemonTypes, fakeGetPokemonTypes)
    }

    @Test
    fun `should_get_pokemon_types_on_initialization`() = runBlocking {
        // trigger action
        initSut()
        testDispatcher.scheduler.advanceUntilIdle()

        // check assertions
        assertTrue(fakePopulatePokemonTypes.fakeResult is Resource.Success)
        val expected = PokemonTypeUI.fromDomainList(fakeGetPokemonTypes.fakePokemonTypes)
        val actual = sut.pokemonTypeScreenState.first().pokemonTypes
        assertEquals(expected, actual)
    }

    @Test
    fun should_handle_error_when_trying_to_fetch_pokemon_types() = runBlocking {
        // define test data
        val errorMessage = "Network Error"
        fakePopulatePokemonTypes.fakeResult = Error(errorMessage)

        // init sut
        initSut()
        testDispatcher.scheduler.advanceUntilIdle()

        // trigger action
        val actual = sut.pokemonTypeScreenState.first().error

        // check assertions
        assertTrue(actual.contains(errorMessage))
    }

    @Test
    fun should_update_state_with_pokemon_types() = runBlocking {
        // init state
        initSut()
        val expected = PokemonTypeUI.fromDomainList(fakeGetPokemonTypes.fakePokemonTypes)
        testDispatcher.scheduler.advanceUntilIdle()

        // trigger action
        val actual = sut.pokemonTypeScreenState.first().pokemonTypes

        // check assertions
        assertEquals(expected, actual)
    }
}
