package dev.skybit.pokedex.pokemontype.domain.usecases

import dev.skybit.pokedex.main.pokemontypes.domain.model.fakePokemonTypeFire
import dev.skybit.pokedex.main.pokemontypes.domain.model.fakePokemonTypeGrass
import dev.skybit.pokedex.main.pokemontypes.domain.repository.FakePokemonTypesRepository
import dev.skybit.pokedex.main.pokemontypes.domain.usecases.GetPokemonTypesImpl
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetPokemonTypesTest {
    private lateinit var pokemonTypesRepository: FakePokemonTypesRepository
    private lateinit var sut: GetPokemonTypesImpl

    @Before
    fun setup() {
        pokemonTypesRepository = FakePokemonTypesRepository()
        sut = GetPokemonTypesImpl(pokemonTypesRepository)
    }

    @Test
    fun should_return_list_of_pokemon_types() = runBlocking {
        // define test data
        val expectedPokemonTypes = listOf(fakePokemonTypeGrass, fakePokemonTypeFire)
        pokemonTypesRepository.fakePokemonTypes = expectedPokemonTypes

        // trigger action
        val result = sut()

        // check assertions
        assertEquals(expectedPokemonTypes, result)
    }
}
