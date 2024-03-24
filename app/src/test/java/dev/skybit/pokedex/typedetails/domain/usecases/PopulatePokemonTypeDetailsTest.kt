package dev.skybit.pokedex.typedetails.domain.usecases

import dev.skybit.pokedex.main.core.utils.Resource
import dev.skybit.pokedex.main.typedetails.domain.repository.FakePokemonTypeDetailsRepository
import dev.skybit.pokedex.main.typedetails.domain.usecases.PopulatePokemonTypeDetails
import dev.skybit.pokedex.main.typedetails.domain.usecases.PopulatePokemonTypeDetailsImpl
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class PopulatePokemonTypeDetailsTest {
    private lateinit var pokemonTypeDetailsRepository: FakePokemonTypeDetailsRepository
    private lateinit var sut: PopulatePokemonTypeDetails

    @Before
    fun init() {
        pokemonTypeDetailsRepository = FakePokemonTypeDetailsRepository()
        sut = PopulatePokemonTypeDetailsImpl(pokemonTypeDetailsRepository)
    }

    @Test
    fun should_successfully_populate_pokemon_type_details() = runBlocking {
        // define test data
        val typeId = 1

        // trigger action
        val result = sut(typeId)

        // check assertions
        assertTrue(result is Resource.Success)
    }

    @Test
    fun should_return_error_when_populating_of_pokemon_type_details_failed() = runBlocking {
        // define test data
        pokemonTypeDetailsRepository.shouldThrowException = true
        val expectedMessage = "Custom Error Message"
        pokemonTypeDetailsRepository.exceptionMessage = expectedMessage

        // trigger action
        val result = sut(1)

        // check assertions
        assertTrue(result is Resource.Error)
    }
}
