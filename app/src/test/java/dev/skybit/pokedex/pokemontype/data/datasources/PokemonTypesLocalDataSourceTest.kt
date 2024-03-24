package dev.skybit.pokedex.pokemontype.data.datasources

import dev.skybit.pokedex.main.pokemontypes.data.datasources.PokemonTypesLocalDataSourceImpl
import dev.skybit.pokedex.main.pokemontypes.data.local.dao.FakePokemonTypeDao
import dev.skybit.pokedex.main.pokemontypes.data.local.model.PokemonTypeEntity
import dev.skybit.pokedex.main.pokemontypes.data.local.model.fakePokemonTypeEntityGrass
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class PokemonTypesLocalDataSourceTest {
    private lateinit var pokemonTypeDao: FakePokemonTypeDao
    private lateinit var sut: PokemonTypesLocalDataSourceImpl

    @Before
    fun init() {
        pokemonTypeDao = FakePokemonTypeDao()
        sut = PokemonTypesLocalDataSourceImpl(pokemonTypeDao)
    }

    @Test
    fun successfully_insert_pokemon_types_to_db() = runBlocking {
        // define test data
        val testPokemonTypes = mutableListOf(fakePokemonTypeEntityGrass, fakePokemonTypeEntityGrass)

        // trigger action
        sut.insertOrUpdatePokemonType(testPokemonTypes)

        // check assertions
        assertEquals(testPokemonTypes, pokemonTypeDao.getAllPokemonTypes())
        assertTrue(pokemonTypeDao.isInserted)
    }

    @Test
    fun successfully_get_pokemon_types_from_db() = runBlocking {
        // define test data
        val testPokemonTypes = mutableListOf(fakePokemonTypeEntityGrass, fakePokemonTypeEntityGrass)
        pokemonTypeDao.fakePokemonTypeEntities = testPokemonTypes

        // trigger action
        val actual = sut.getPokemonTypes()

        // check assertions
        assertEquals(testPokemonTypes, actual)
    }

    @Test
    fun should_return_empty_list_when_theres_no_pokemon_types_in_db() = runBlocking {
        // define test data
        pokemonTypeDao.fakePokemonTypeEntities = mutableListOf()

        // trigger action
        val actual = sut.getPokemonTypes()

        // check assertions
        assertEquals(emptyList<PokemonTypeEntity>(), actual)
    }
}
