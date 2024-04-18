package dev.skybit.pokedex.pokemon.domain.usecases

import dev.skybit.pokedex.main.core.utils.Resource
import dev.skybit.pokedex.main.pokemon.domain.model.fakePokemonDetailsOne
import dev.skybit.pokedex.main.pokemon.domain.repository.FakePokemonDetailsRepository
import dev.skybit.pokedex.main.pokemon.domain.usecases.FakePopulatePokemonDetails
import dev.skybit.pokedex.main.pokemon.domain.usecases.GetPokemonDetails
import dev.skybit.pokedex.main.pokemon.domain.usecases.GetPokemonDetailsImpl
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetPokemonDetailsTest {
    private lateinit var pokemonDetailsRepository: FakePokemonDetailsRepository
    private lateinit var populatePokemonDetails: FakePopulatePokemonDetails
    private lateinit var sut: GetPokemonDetails

    @Before
    fun init() {
        pokemonDetailsRepository = FakePokemonDetailsRepository()
        populatePokemonDetails = FakePopulatePokemonDetails()
        sut = GetPokemonDetailsImpl(pokemonDetailsRepository, populatePokemonDetails)
    }

    @Test
    fun should_successfully_populate_pokemon_details() = runBlocking {
        // define test data
        populatePokemonDetails.fakeResult = Resource.Success(Unit)
        populatePokemonDetails.isPokemonDetailsPopulated = false

        // trigger action
        val actual = sut(fakePokemonDetailsOne.id)

        // check assertions
        assertTrue(populatePokemonDetails.isPokemonDetailsPopulated)
    }

    @Test
    fun should_successfully_get_pokemon_details() = runBlocking {
        // define test data
        populatePokemonDetails.fakeResult = Resource.Success(Unit)

        // trigger action
        val actual = sut(fakePokemonDetailsOne.id)

        // check assertions
        assertTrue(actual is Resource.Success)
    }

    @Test
    fun should_return_resources_error_when_populate_pokemon_details_fails() = runBlocking {
        // define test data
        populatePokemonDetails.fakeResult = Resource.Error("An error occurred")
        populatePokemonDetails.isPokemonDetailsPopulated = false

        // trigger action
        val actual = sut(fakePokemonDetailsOne.id)

        // check assertions
        assertTrue(actual is Resource.Error)
    }

    @Test
    fun should_return_resources_error_when_there_is_no_pokemon_details() = runBlocking {
        // define test data
        populatePokemonDetails.fakeResult = Resource.Success(Unit)
        pokemonDetailsRepository.fakePokemonDetailsMap = mutableMapOf()

        // trigger action
        val actual = sut(fakePokemonDetailsOne.id)

        // check assertions
        assertTrue(actual is Resource.Error)
    }
}
