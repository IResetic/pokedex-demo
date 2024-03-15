package dev.skybit.pokedex.pokemontype.data.repository

import dev.skybit.pokedex.main.core.data.remote.model.fakePokemonTypesPagedResponse
import dev.skybit.pokedex.main.pokemontypes.data.datasources.FakePokemonTypesLocalDataSource
import dev.skybit.pokedex.main.pokemontypes.data.datasources.FakePokemonTypesRemoteDataSource
import dev.skybit.pokedex.main.pokemontypes.data.local.model.fakePokemonTypeEntityFire
import dev.skybit.pokedex.main.pokemontypes.data.local.model.fakePokemonTypeEntityGrass
import dev.skybit.pokedex.main.pokemontypes.data.local.model.mappers.ResultDtoToPokemonEntityTypeMapper
import dev.skybit.pokedex.main.pokemontypes.data.repository.PokemonTypesRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class PokemonTypesRepositoryTest {
    private lateinit var pokemonTypesRemoteDataSource: FakePokemonTypesRemoteDataSource
    private lateinit var pokemonTypesLocalDataSource: FakePokemonTypesLocalDataSource
    private lateinit var resultDtoToPokemonEntityTypeMapper: ResultDtoToPokemonEntityTypeMapper
    private lateinit var sut: PokemonTypesRepositoryImpl

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun init() {
        pokemonTypesRemoteDataSource = FakePokemonTypesRemoteDataSource()
        pokemonTypesLocalDataSource = FakePokemonTypesLocalDataSource()
        resultDtoToPokemonEntityTypeMapper = ResultDtoToPokemonEntityTypeMapper()
        sut = PokemonTypesRepositoryImpl(
            pokemonTypesRemoteDataSource,
            pokemonTypesLocalDataSource,
            resultDtoToPokemonEntityTypeMapper,
            testDispatcher
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun should_successfully_populate_pokemon_types() = runBlocking {
        // define test data
        pokemonTypesRemoteDataSource.responses = listOf(Response.success(fakePokemonTypesPagedResponse))

        // trigger action
        sut.populatePokemonTypes(0)

        // check assertions
        assertTrue(pokemonTypesLocalDataSource.isInserted)
        val expected = fakePokemonTypesPagedResponse.results?.map { resultDtoToPokemonEntityTypeMapper(it) }
        assertEquals(expected, pokemonTypesLocalDataSource.fakePokemonTypeEntities)
    }

    @Test
    fun should_return_pokemon_types() = runBlocking {
        // trigger action
        val actual = sut.getPokemonTypes()

        // check assertions
        val expected = pokemonTypesLocalDataSource.fakePokemonTypeEntities.map { it.toDomain() }
        assertEquals(expected, actual)
    }

    @Test(expected = Exception::class)
    fun populatePokemonTypes_handlesErrorFromRemoteDataSource() = runBlocking {
        // define test data
        pokemonTypesRemoteDataSource.responses = listOf(Response.error(400, "Bad Request".toResponseBody(null)))

        // trigger action
        sut.populatePokemonTypes(0)
    }

    @Test
    fun should_recursively_populate_pokemon_types() = runBlocking {
        // define test data
        pokemonTypesRemoteDataSource.responses = listOf(
            Response.success(fakePokemonTypesPagedResponse.copy(next = "http://example.com/page2")),
            Response.success(fakePokemonTypesPagedResponse.copy(next = null))
        )

        // trigger action
        sut.populatePokemonTypes(0)

        // check assertions
        assertTrue(pokemonTypesLocalDataSource.isInserted)
        assertEquals(4, pokemonTypesLocalDataSource.fakePokemonTypeEntities.size)
    }

    @Test
    fun should_return_pokemon_type_for_specific_id() = runBlocking {
        // define test data
        val pokemonTypes = listOf(fakePokemonTypeEntityFire, fakePokemonTypeEntityGrass)
        pokemonTypesLocalDataSource.populatePokemonTypesMap(pokemonTypes)

        // trigger action
        val actual = sut.getPokemonTypeBasicIInfoById(fakePokemonTypeEntityGrass.id)

        // check assertions
        val expected = fakePokemonTypeEntityGrass.toDomain()
        assertEquals(expected, actual)
    }
}
