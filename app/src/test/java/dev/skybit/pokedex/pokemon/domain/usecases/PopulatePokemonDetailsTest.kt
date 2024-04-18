package dev.skybit.pokedex.pokemon.domain.usecases

import dev.skybit.pokedex.main.core.utils.Resource
import dev.skybit.pokedex.main.pokemon.domain.model.fakePokemonDetailsOne
import dev.skybit.pokedex.main.pokemon.domain.repository.FakePokemonDetailsRepository
import dev.skybit.pokedex.main.pokemon.domain.usecases.PopulatePokemonDetails
import dev.skybit.pokedex.main.pokemon.domain.usecases.PopulatePokemonDetailsImpl
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class PopulatePokemonDetailsTest {
    private lateinit var populatePokemonDetails: FakePokemonDetailsRepository
    private lateinit var sut: PopulatePokemonDetails

    @Before
    fun init() {
        populatePokemonDetails = FakePokemonDetailsRepository()
        sut = PopulatePokemonDetailsImpl(populatePokemonDetails)
    }

    @Test
    fun should_successfully_populate_pokemon_details() = runBlocking {
        // define test data
        populatePokemonDetails.shouldThrowException = false

        // trigger action
        val actual = sut(fakePokemonDetailsOne.id)

        // check assertions
        assertTrue(actual is Resource.Success)
    }

    @Test
    fun should_return_resources_error_when_populate_pokemon_details_fails() = runBlocking {
        // define test data
        populatePokemonDetails.shouldThrowException = true

        // trigger action
        val actual = sut(fakePokemonDetailsOne.id)

        // check assertions
        assertTrue(actual is Resource.Error)
    }
}
