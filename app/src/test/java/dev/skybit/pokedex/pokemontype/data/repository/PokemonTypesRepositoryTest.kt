package dev.skybit.pokedex.pokemontype.data.repository

import dev.skybit.pokedex.main.core.data.remote.model.PagedResponse
import dev.skybit.pokedex.main.core.data.remote.model.fakePokemonTypesPagedResponse
import dev.skybit.pokedex.main.core.data.remote.model.fakeResultDtoElectric
import dev.skybit.pokedex.main.core.data.remote.model.fakeResultDtoWater
import dev.skybit.pokedex.main.core.utils.Resource
import dev.skybit.pokedex.main.pokemontypes.data.datasources.FakePokemonTypesLocalDataSource
import dev.skybit.pokedex.main.pokemontypes.data.datasources.FakePokemonTypesRemoteDataSource
import dev.skybit.pokedex.main.pokemontypes.data.local.model.fakePokemonTypeEntityFire
import dev.skybit.pokedex.main.pokemontypes.data.local.model.fakePokemonTypeEntityGrass
import dev.skybit.pokedex.main.pokemontypes.data.remote.mappers.ResultDtoToPokemonEntityTypeMapper
import dev.skybit.pokedex.main.pokemontypes.data.repository.PokemonTypesRepositoryImpl
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response

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

    @Test
    fun successfully_populate_pokemon_types_from_remote_source() = runBlocking {
        // define test data
        val pokemonTypes = listOf(fakeResultDtoWater, fakeResultDtoElectric)
        val response = Response.success(
            fakePokemonTypesPagedResponse.copy(
                results = pokemonTypes
            )
        )
        pokemonTypesRemoteDataSource.responses = listOf(response)

        // trigger action
        sut.populatePokemonTypes()

        // check assertions
        val result = pokemonTypesLocalDataSource.fakePokemonTypeEntities
        val expected = pokemonTypes.map { resultDtoToPokemonEntityTypeMapper(it) }
        assertEquals(expected, result)
    }

    @Test
    fun should_return_pokemon_types() = runBlocking {
        // trigger action
        val actual = sut.getPokemonTypes()

        // check assertions
        val expected = pokemonTypesLocalDataSource.fakePokemonTypeEntities.map { it.toDomain() }
        assertEquals(expected, actual)
    }

    @Test
    fun populate_pokemon_types_should_return_error_when_remote_source_fails() = runBlocking {
        // define test data
        val errorMessage = "Network error"
        val response = Response.error<PagedResponse>(
            500,
            "{\"error\": \"$errorMessage\"}"
                .toResponseBody("application/json".toMediaTypeOrNull())
        )
        pokemonTypesRemoteDataSource.responses = listOf(response)

        // trigger action
        val result = sut.populatePokemonTypes()

        // check assertions
        assertTrue(result is Resource.Error)
        assertTrue((result as Resource.Error).message!!.contains(errorMessage))
    }

    @Test
    fun should_fetch_and_store_multiple_pages_of_pokemon_types() = runBlocking {
        // define test data
        val firstPageResponse = Response.success(
            fakePokemonTypesPagedResponse.copy(next = "http://nextpage.com", results = listOf(fakeResultDtoWater))
        )
        val secondPageResponse = Response.success(
            fakePokemonTypesPagedResponse.copy(next = null, results = listOf(fakeResultDtoElectric))
        )
        pokemonTypesRemoteDataSource.responses = (listOf(firstPageResponse, secondPageResponse))

        // trigger action
        sut.populatePokemonTypes()

        // check assertions
        val storedTypes = pokemonTypesLocalDataSource.fakePokemonTypeEntities
        val expectedTypes = listOf(fakeResultDtoWater, fakeResultDtoElectric).map {
            resultDtoToPokemonEntityTypeMapper(it)
        }
        assertEquals(expectedTypes, storedTypes)
    }

    @Test
    fun should_successfully_return_a_flow_of_pokemon_types() = runBlocking {
        // define test data
        val fakeDataEntities = listOf(
            fakePokemonTypeEntityGrass,
            fakePokemonTypeEntityFire
        )
        pokemonTypesLocalDataSource.fakePokemonTypeEntities.addAll(fakeDataEntities)

        // trigger action
        val result = sut.getPokemonTypesFlow().first()

        // check assertions
        val expected = fakeDataEntities.map { it.toDomain() }
        assertEquals(expected, result)
    }
}
