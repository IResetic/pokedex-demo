package dev.skybit.pokedex.pokemontype.domain.usecases

import dev.skybit.pokedex.main.core.domain.model.fakePokemonTypeFire
import dev.skybit.pokedex.main.core.domain.model.fakePokemonTypeGrass
import dev.skybit.pokedex.main.core.utils.Resource
import dev.skybit.pokedex.main.pokemontypes.domain.repository.FakePokemonTypesRepository
import dev.skybit.pokedex.main.pokemontypes.domain.usecases.GetPokemonTypesImpl
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetPokemonTypesTest {
    private lateinit var pokemonTypesRepository: FakePokemonTypesRepository
    private lateinit var sut: GetPokemonTypesImpl

    @Before
    fun init() {
        pokemonTypesRepository = FakePokemonTypesRepository()
        sut = GetPokemonTypesImpl(pokemonTypesRepository)
    }

    @Test
    fun should_successfully_fetch_pokemon_types_and_return_them() = runBlocking {
        // define test data
        val fakeRepository = pokemonTypesRepository.apply {
            fakePokemonTypes = listOf(fakePokemonTypeFire, fakePokemonTypeGrass)
            shouldFetchThrowException = false
        }

        // trigger action
        val result = sut()

        // check assertions
        assertTrue(result is Resource.Success)
        assertEquals(fakeRepository.fakePokemonTypes, result.data)
    }

    @Test
    fun should_fail_to_fetch_pokemon_types_and_return_current_pokemon_types_from_local_data_source() = runBlocking {
        // define test data
        val fakeRepository = pokemonTypesRepository.apply {
            fakePokemonTypes = listOf(fakePokemonTypeFire)
            shouldFetchThrowException = true
        }

        // trigger action
        val result = sut()

        // check assertions
        assertTrue(result is Resource.Error)
        assertEquals(fakeRepository.fakePokemonTypes, result.data)
    }
}
