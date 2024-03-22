package dev.skybit.pokedex.typedetails.data.repository

import dev.skybit.pokedex.main.typedetails.data.datasources.FakePokemonTypeDetailsLocalDataSource
import dev.skybit.pokedex.main.typedetails.data.datasources.FakePokemonTypeDetailsRemoteDataSource
import dev.skybit.pokedex.main.typedetails.data.local.model.fakeGrassPokemonTypeId
import dev.skybit.pokedex.main.typedetails.data.local.model.fakePokemonBasicInfEntityFireOne
import dev.skybit.pokedex.main.typedetails.data.local.model.fakePokemonBasicInfoEntityGrassOne
import dev.skybit.pokedex.main.typedetails.data.local.model.fakePokemonBasicInfoEntityGrassTwp
import dev.skybit.pokedex.main.typedetails.data.mappers.ResultDtoToPokemonBasicInfoEntityMapper
import dev.skybit.pokedex.main.typedetails.data.remote.model.fakePokemonTypeDetailsResponseGrass
import dev.skybit.pokedex.main.typedetails.data.repository.PokemonTypeDetailsRepositoryImpl
import dev.skybit.pokedex.main.typedetails.domain.repository.PokemonTypeDetailsRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class PokemonTypeDetailsRepositoryTest {
    private lateinit var pokemonTypeDetailsRemoteDataSource: FakePokemonTypeDetailsRemoteDataSource
    private lateinit var pokemonTypeDetailsLocalDataSource: FakePokemonTypeDetailsLocalDataSource
    private lateinit var resultDtoToPokemonBasicInfoEntityMapper: ResultDtoToPokemonBasicInfoEntityMapper
    private lateinit var sut: PokemonTypeDetailsRepository

    @Before
    fun init() {
        pokemonTypeDetailsRemoteDataSource = FakePokemonTypeDetailsRemoteDataSource()
        pokemonTypeDetailsLocalDataSource = FakePokemonTypeDetailsLocalDataSource()
        resultDtoToPokemonBasicInfoEntityMapper = ResultDtoToPokemonBasicInfoEntityMapper()
        sut = PokemonTypeDetailsRepositoryImpl(
            pokemonTypeDetailsRemoteDataSource,
            pokemonTypeDetailsLocalDataSource,
            resultDtoToPokemonBasicInfoEntityMapper
        )
    }

    @Test
    fun should_successfully_fetch_and_store_pokemon_type_details() = runBlocking {
        // define test data
        val response = fakePokemonTypeDetailsResponseGrass
        pokemonTypeDetailsRemoteDataSource.response = Response.success(response)
        pokemonTypeDetailsRemoteDataSource.shouldReturnError = false

        // trigger action
        sut.populatePokemonTypeDetails(response.id)

        // check assertions
        val expected = response.pokemons?.map {
            resultDtoToPokemonBasicInfoEntityMapper(it.pokemon, response.id, response.name ?: "")
        }

        assertEquals(expected, pokemonTypeDetailsLocalDataSource.fakePokemonBasicInfoStorage)
    }

    @Test(expected = Exception::class)
    fun should_throw_exception_when_fetching_of_pokemon_type_details_failed() = runBlocking {
        // define test data
        pokemonTypeDetailsRemoteDataSource.shouldReturnError = true

        // trigger action
        sut.populatePokemonTypeDetails(1)
    }

    @Test
    fun should_successfully_return_pokemon_type_details() = runBlocking {
        // define test data
        pokemonTypeDetailsLocalDataSource.fakePokemonBasicInfoStorage.addAll(
            listOf(
                fakePokemonBasicInfoEntityGrassOne,
                fakePokemonBasicInfEntityFireOne,
                fakePokemonBasicInfoEntityGrassTwp
            )
        )

        // trigger action
        val result = sut.getPokemonTypeDetails(fakeGrassPokemonTypeId)

        // check assertions
        val expected = listOf(fakePokemonBasicInfoEntityGrassOne, fakePokemonBasicInfoEntityGrassTwp).map {
            it.toDomain()
        }
        assertEquals(expected, result)
    }
}
