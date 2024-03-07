package dev.skybit.pokedex.pokemontype.domain.usecases

import dev.skybit.pokedex.main.core.utils.Resource
import dev.skybit.pokedex.main.core.utils.Resource.Error
import dev.skybit.pokedex.main.pokemontypes.domain.repository.FakePokemonTypesRepository
import dev.skybit.pokedex.main.pokemontypes.domain.usecases.PopulatePokemonTypesImpl
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class PopulatePokemonTypesTest {
    private lateinit var fakePokemonTypesRepository: FakePokemonTypesRepository
    private lateinit var sut: PopulatePokemonTypesImpl

    @Before
    fun setup() {
        fakePokemonTypesRepository = FakePokemonTypesRepository()
        sut = PopulatePokemonTypesImpl(fakePokemonTypesRepository)
    }

    @Test
    fun should_return_success_when_repository_populates_types_successfully() = runBlocking {
        // define test data
        fakePokemonTypesRepository.fakeResult = Resource.Success(Unit)

        // trigger action
        val result = sut()

        // check assertions
        assertTrue(result is Resource.Success)
    }

    @Test
    fun `invoke should return error when repository population fails`() = runBlocking {
        // define test data
        val errorMessage = "Error populating types"
        fakePokemonTypesRepository.fakeResult = Error(errorMessage)

        // trigger action
        val result = sut()

        // check assertions
        assertTrue(result is Error)
        assertEquals(errorMessage, (result as Error).message)
    }
}
