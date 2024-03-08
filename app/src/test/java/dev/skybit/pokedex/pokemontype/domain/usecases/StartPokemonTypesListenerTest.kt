package dev.skybit.pokedex.pokemontype.domain.usecases

import dev.skybit.pokedex.main.pokemontypes.domain.model.fakePokemonTypeFire
import dev.skybit.pokedex.main.pokemontypes.domain.model.fakePokemonTypeGrass
import dev.skybit.pokedex.main.pokemontypes.domain.repository.FakePokemonTypesRepository
import dev.skybit.pokedex.main.pokemontypes.domain.usecases.StartPokemonTypesListenerImpl
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class StartPokemonTypesListenerTest {
    private lateinit var fakePokemonTypesRepository: FakePokemonTypesRepository
    private lateinit var sut: StartPokemonTypesListenerImpl

    @Before
    fun init() {
        fakePokemonTypesRepository = FakePokemonTypesRepository()
        sut = StartPokemonTypesListenerImpl(fakePokemonTypesRepository)
    }

    @Test
    fun `invoke should return a flow of pokemon types`() = runBlocking {
        // define test data
        val expectedPokemonTypes = listOf(
            fakePokemonTypeFire,
            fakePokemonTypeGrass
        )
        fakePokemonTypesRepository.fakePokemonTypes = expectedPokemonTypes

        // trigger action
        val result = sut.invoke().first()

        // check assertions
        assertEquals(expectedPokemonTypes, result)
    }
}
