package dev.skybit.pokedex.typedetails.domain.usecases

import dev.skybit.pokedex.main.core.utils.Resource
import dev.skybit.pokedex.main.typedetails.domain.model.fakePokemonBasicInfoBulbasaur
import dev.skybit.pokedex.main.typedetails.domain.repository.FakePokemonTypeDetailsRepository
import dev.skybit.pokedex.main.typedetails.domain.usecases.GetPokemonsBasicInfoByTypeIdImpl
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetPokemonsBasicInfoByTypeIdTest {
    private lateinit var pokemonTypeDetailsRepository: FakePokemonTypeDetailsRepository
    private lateinit var sut: GetPokemonsBasicInfoByTypeIdImpl

    @Before
    fun init() {
        pokemonTypeDetailsRepository = FakePokemonTypeDetailsRepository()
        sut = GetPokemonsBasicInfoByTypeIdImpl(pokemonTypeDetailsRepository)
    }

    @Test
    fun should_successfully_fetch_and_get_pokemon_type_details() = runBlocking {
        // define test data
        val pokemons = listOf(fakePokemonBasicInfoBulbasaur)
        pokemonTypeDetailsRepository.fakePokemonBasicInfoList.addAll(pokemons)

        // trigger action
        val result = sut(1)

        // check assertions
        assertTrue(result is Resource.Success)
        assertEquals(pokemons, result.data)
    }

    @Test
    fun should_return_error_when_fetching_of_pokemon_type_details_failed() = runBlocking {
        // define test data
        pokemonTypeDetailsRepository.shouldThrowException = true
        val expectedMessage = "Custom Error Message"
        pokemonTypeDetailsRepository.exceptionMessage = expectedMessage

        // trigger action
        val result = sut(1)

        // check assertions
        assertTrue(result is Resource.Error)
        assertEquals(expectedMessage, result.message)
        assertTrue(result.data!!.isEmpty())
    }
}
