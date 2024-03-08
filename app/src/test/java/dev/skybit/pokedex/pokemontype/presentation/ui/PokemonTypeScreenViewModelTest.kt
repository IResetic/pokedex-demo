package dev.skybit.pokedex.pokemontype.presentation.ui

import dev.skybit.pokedex.main.core.utils.Resource.Error
import dev.skybit.pokedex.main.pokemontypes.domain.model.PokemonType
import dev.skybit.pokedex.main.pokemontypes.domain.model.fakePokemonTypeFire
import dev.skybit.pokedex.main.pokemontypes.domain.model.fakePokemonTypeGrass
import dev.skybit.pokedex.main.pokemontypes.domain.usecases.FakePopulatePokemonTypes
import dev.skybit.pokedex.main.pokemontypes.domain.usecases.FakeStartPokemonTypesListener
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
    private lateinit var startPokemonTypesListener: FakeStartPokemonTypesListener
    private lateinit var sut: PokemonTypeScreenViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun init() {
        Dispatchers.setMain(testDispatcher)
        fakePopulatePokemonTypes = FakePopulatePokemonTypes()
        startPokemonTypesListener = FakeStartPokemonTypesListener()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun initSut() {
        sut = PokemonTypeScreenViewModel(fakePopulatePokemonTypes, startPokemonTypesListener)
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
        val pokemonTypes = listOf(fakePokemonTypeGrass, fakePokemonTypeFire)
        startPokemonTypesListener.emitPokemonTypes(pokemonTypes)

        // trigger action
        val actual = sut.pokemonTypeScreenState.first().pokemonTypes

        // check assertions
        val expected = PokemonTypeUI.fromDomainList(pokemonTypes)
        assertEquals(expected, actual)
    }

    @Test
    fun should_not_update_state_if_pokemon_types_list_is_empty() = runBlocking {
        // init sut
        initSut()

        // check assertions
        startPokemonTypesListener.emitPokemonTypes(emptyList())
        var actual = sut.pokemonTypeScreenState.first().pokemonTypes
        assertEquals(emptyList<PokemonType>(), actual)

        val pokemonTypes = listOf(fakePokemonTypeGrass, fakePokemonTypeFire)
        startPokemonTypesListener.emitPokemonTypes(pokemonTypes)
        actual = sut.pokemonTypeScreenState.first().pokemonTypes

        val expected = PokemonTypeUI.fromDomainList(pokemonTypes)
        assertEquals(expected, actual)
    }
}
