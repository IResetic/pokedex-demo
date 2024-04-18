package dev.skybit.pokedex.pokemon.data.datasources

import dev.skybit.pokedex.main.pokemon.data.datasources.PokemonDetailsLocalDataSource
import dev.skybit.pokedex.main.pokemon.data.datasources.PokemonDetailsLocalDataSourceImpl
import dev.skybit.pokedex.main.pokemon.data.local.dao.FakePokemonDetailsDao
import dev.skybit.pokedex.main.pokemon.data.local.model.fakePokemonDetailsEntityOne
import dev.skybit.pokedex.main.pokemon.data.local.model.fakePokemonDetailsEntityTwo
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PokemonDetailsLocalDataSourceTest {
    private lateinit var pokemonDetailsDao: FakePokemonDetailsDao
    private lateinit var sut: PokemonDetailsLocalDataSource

    @Before
    fun init() {
        pokemonDetailsDao = FakePokemonDetailsDao()
        sut = PokemonDetailsLocalDataSourceImpl(pokemonDetailsDao)
    }

    @Test
    fun should_successfully_insert_pokemon_details_to_local_data_source() = runBlocking {
        // trigger action
        sut.insertOrUpdatePokemonDetails(fakePokemonDetailsEntityOne)

        // check assertions
        val actual = pokemonDetailsDao.pokemonDetailsStorage[fakePokemonDetailsEntityOne.id]
        assertEquals(fakePokemonDetailsEntityOne, actual)
    }

    @Test
    fun should_successfully_get_pokemon_details_from_local_data_source() = runBlocking {
        // define test data
        pokemonDetailsDao.pokemonDetailsStorage[fakePokemonDetailsEntityOne.id] = fakePokemonDetailsEntityOne
        pokemonDetailsDao.pokemonDetailsStorage[fakePokemonDetailsEntityTwo.id] = fakePokemonDetailsEntityTwo

        // trigger action
        val actual = sut.getPokemonDetailsById(fakePokemonDetailsEntityTwo.id)

        // check assertions
        assertEquals(fakePokemonDetailsEntityTwo, actual)
    }
}
