package dev.skybit.pokedex.pokemonslist.presentation.ui

import androidx.lifecycle.SavedStateHandle
import dev.skybit.pokedex.main.core.domain.model.fakePokemonTypeFire
import dev.skybit.pokedex.main.core.domain.model.fakePokemonTypeGrass
import dev.skybit.pokedex.main.core.domain.usecases.FakeGetPokemonTypeBasicInfo
import dev.skybit.pokedex.main.pokemonslist.presentation.model.PokemonsListBasicInfoUI
import dev.skybit.pokedex.main.pokemonslist.presentation.navigation.PokemonsListScreenDestination
import dev.skybit.pokedex.main.pokemonslist.presentation.ui.PokemonsListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PokemonsListViewModelTest {
    private lateinit var getPokemonTypeBasicInfo: FakeGetPokemonTypeBasicInfo
    private lateinit var sut: PokemonsListViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun init() {
        Dispatchers.setMain(testDispatcher)
        getPokemonTypeBasicInfo = FakeGetPokemonTypeBasicInfo()
        initSut()
    }

    private fun initSut(savedStateHandle: SavedStateHandle = SavedStateHandle()) {
        sut = PokemonsListViewModel(
            savedStateHandle = savedStateHandle,
            getPokemonTypeBasicInfo = getPokemonTypeBasicInfo
        )
    }

    @Test
    fun should_set_pokemons_list_title_and_background_colour() = runBlocking {
        // define test data
        getPokemonTypeBasicInfo.fakePokemonType = fakePokemonTypeGrass

        // init sut
        val savedStateHandle = SavedStateHandle().apply {
            this[PokemonsListScreenDestination.POKEMON_TYPE_ID] = fakePokemonTypeFire.id.toString()
        }
        initSut(savedStateHandle)

        // check assertions
        val actual = sut.pokemonsListScreenState.first().pokemonTypeBasicInfo
        val expected = PokemonsListBasicInfoUI.fromDomain(fakePokemonTypeGrass)
        assertEquals(expected, actual)
    }
}
