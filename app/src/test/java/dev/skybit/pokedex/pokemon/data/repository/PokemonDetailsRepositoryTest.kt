package dev.skybit.pokedex.pokemon.data.repository

import dev.skybit.pokedex.main.pokemon.data.datasources.FakePokemonDetailsLocalDataSource
import dev.skybit.pokedex.main.pokemon.data.datasources.FakePokemonDetailsRemoteDataSource
import dev.skybit.pokedex.main.pokemon.data.local.model.fakePokemonDetailsEntityOne
import dev.skybit.pokedex.main.pokemon.data.local.model.fakePokemonDetailsEntityTwo
import dev.skybit.pokedex.main.pokemon.data.mappers.PokemonDetailsResponseToPokemonDetailsEntityMapper
import dev.skybit.pokedex.main.pokemon.data.remote.model.fakePokemonDetailsResponseOne
import dev.skybit.pokedex.main.pokemon.data.remote.model.fakePokemonDetailsResponseTwo
import dev.skybit.pokedex.main.pokemon.data.repository.PokemonDetailsRepositoryImpl
import dev.skybit.pokedex.main.pokemon.domain.repository.PokemonDetailsRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class PokemonDetailsRepositoryTest {
    private lateinit var pokemonDetailsRemoteDataSource: FakePokemonDetailsRemoteDataSource
    private lateinit var pokemonDetailsLocalDataSource: FakePokemonDetailsLocalDataSource
    private lateinit var pokemonDetailsResponseToPokemonDetailsEntityMapper: PokemonDetailsResponseToPokemonDetailsEntityMapper
    private lateinit var sut: PokemonDetailsRepository

    @Before
    fun init() {
        pokemonDetailsRemoteDataSource = FakePokemonDetailsRemoteDataSource()
        pokemonDetailsLocalDataSource = FakePokemonDetailsLocalDataSource()
        pokemonDetailsResponseToPokemonDetailsEntityMapper = PokemonDetailsResponseToPokemonDetailsEntityMapper()
        sut = PokemonDetailsRepositoryImpl(
            pokemonDetailsRemoteDataSource = pokemonDetailsRemoteDataSource,
            pokemonDetailsLocalDataSource = pokemonDetailsLocalDataSource,
            pokemonDetailsResponseToPokemonDetailsEntityMapper = pokemonDetailsResponseToPokemonDetailsEntityMapper
        )
    }

    @Test
    fun should_successfully_save_data_to_local_data_source() = runBlocking {
        // define test data
        val fakePokemonId = fakePokemonDetailsResponseOne.id
        pokemonDetailsRemoteDataSource.fakePokemonDetailsResponseMap[fakePokemonId] = fakePokemonDetailsResponseOne
        pokemonDetailsRemoteDataSource.fakePokemonDetailsResponseMap[fakePokemonDetailsResponseTwo.id] =
            fakePokemonDetailsResponseTwo
        pokemonDetailsRemoteDataSource.isSuccess = true

        // trigger action
        sut.populatePokemonDetails(fakePokemonId)

        // check assertions
        val expected = pokemonDetailsResponseToPokemonDetailsEntityMapper(fakePokemonDetailsResponseOne)
        val actual = pokemonDetailsLocalDataSource.getPokemonDetailsById(fakePokemonId)
        assertEquals(expected, actual)
    }

    @Test(expected = Exception::class)
    fun should_throw_exception_when_fetching_from_remote_data_source_is_not_successful() = runBlocking {
        // define test data
        val errorMessage = "Network error"
        val fakePokemonId = fakePokemonDetailsResponseOne.id
        pokemonDetailsRemoteDataSource.fakePokemonDetailsResponseMap[fakePokemonId] = fakePokemonDetailsResponseOne
        pokemonDetailsRemoteDataSource.isSuccess = false

        // trigger action
        sut.populatePokemonDetails(fakePokemonId)
    }

    @Test
    fun should_successfully_get_pokemon_details_data_for_specific_pokemon_id() = runBlocking {
        // define test data
        val fakePokemonIdOne = fakePokemonDetailsEntityOne.id
        val fakePokemonIdTwo = fakePokemonDetailsEntityTwo.id
        pokemonDetailsLocalDataSource.fakePokemonDetailsEntityMap[fakePokemonIdOne] = fakePokemonDetailsEntityOne
        pokemonDetailsLocalDataSource.fakePokemonDetailsEntityMap[fakePokemonIdTwo] = fakePokemonDetailsEntityTwo

        // trigger action
        val actual = sut.getPokemonDetails(fakePokemonIdOne)
        val expected = fakePokemonDetailsEntityOne.toDomain()

        // check assertions
        assertEquals(expected, actual)
    }

    @Test
    fun should_return_null_when_pokemon_details_data_is_not_found() = runBlocking {
        // define test data
        val fakePokemonIdOne = fakePokemonDetailsEntityOne.id
        val fakePokemonIdTwo = fakePokemonDetailsEntityTwo.id
        pokemonDetailsLocalDataSource.fakePokemonDetailsEntityMap[fakePokemonIdOne] = fakePokemonDetailsEntityOne

        // trigger action
        val actual = sut.getPokemonDetails(fakePokemonIdTwo)

        // check assertions
        assertNull(actual)
    }
}
