package dev.skybit.pokedex.pokemon.presentation.ui

import androidx.lifecycle.SavedStateHandle
import dev.skybit.pokedex.main.core.utils.Resource
import dev.skybit.pokedex.main.core.utils.parseTypeToColor
import dev.skybit.pokedex.main.pokemon.domain.usecases.FakeGetPokemonBasicInfo
import dev.skybit.pokedex.main.pokemon.domain.usecases.FakeGetPokemonDetailsById
import dev.skybit.pokedex.main.pokemon.domain.usecases.FakePopulatePokemonDetails
import dev.skybit.pokedex.main.pokemon.presentation.navigation.PokemonDetailsScreenDestination.POKEMON_ID
import dev.skybit.pokedex.main.pokemon.presentation.ui.PokemonDetailsScreenViewModel
import dev.skybit.pokedex.main.typedetails.domain.model.fakePokemonBasicInfoBulbasaur
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PokemonDetailsScreenViewModelTest {
    private lateinit var getPokemonBasicInfo: FakeGetPokemonBasicInfo
    private lateinit var getPokemonDetailsById: FakeGetPokemonDetailsById
    private lateinit var populatePokemonDetails: FakePopulatePokemonDetails
    private lateinit var sut: PokemonDetailsScreenViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun init() {
        Dispatchers.setMain(testDispatcher)
        getPokemonBasicInfo = FakeGetPokemonBasicInfo()
        getPokemonDetailsById = FakeGetPokemonDetailsById()
        populatePokemonDetails = FakePopulatePokemonDetails()
        initSut()
    }

    private fun initSut(savedStateHandle: SavedStateHandle = SavedStateHandle()) {
        sut = PokemonDetailsScreenViewModel(
            savedStateHandle = savedStateHandle,
            getPokemonBasicInfo = getPokemonBasicInfo,
            getPokemonDetailsById = getPokemonDetailsById,
            populatePokemonDetails = populatePokemonDetails
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
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
    fun should_successfully_populate_pokemon_details() = runBlocking {
        // define test data
        populatePokemonDetails.fakeResult = Resource.Success(Unit)
        val saveScreenViewModel = SavedStateHandle(mapOf(POKEMON_ID to fakePokemonBasicInfoBulbasaur.id))

        // init sut
        initSut(savedStateHandle = saveScreenViewModel)

        // trigger action
        val actual = sut.pokemonDetailsScreenUiState.first()

        // check assertions
        val expected = parseTypeToColor(fakePokemonBasicInfoBulbasaur.pokemonTypeName)
        assertEquals(expected, actual.backgroundColor)
    }
}
