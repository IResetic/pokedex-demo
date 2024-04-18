package dev.skybit.pokedex.pokemon.domain.usecases

import dev.skybit.pokedex.main.pokemon.domain.usecases.GetPokemonBasicInfo
import dev.skybit.pokedex.main.pokemon.domain.usecases.GetPokemonBasicInfoImpl
import dev.skybit.pokedex.main.typedetails.domain.model.fakePokemonBasicInfoBulbasaur
import dev.skybit.pokedex.main.typedetails.domain.model.fakePokemonBasicInfoIvysaur
import dev.skybit.pokedex.main.typedetails.domain.repository.FakePokemonTypeDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetPokemonBasicInfoTest {
    private lateinit var fakePokemonTypeDetailsRepository: FakePokemonTypeDetailsRepository
    private lateinit var sut: GetPokemonBasicInfo

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun init() {
        Dispatchers.setMain(testDispatcher)
        fakePokemonTypeDetailsRepository = FakePokemonTypeDetailsRepository()
        sut = GetPokemonBasicInfoImpl(fakePokemonTypeDetailsRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun should_successfully_get_basic_pokemon_info() = runBlocking {
        // define test data
        fakePokemonTypeDetailsRepository.fakePokemonBasicInfoList.add(fakePokemonBasicInfoBulbasaur)

        // trigger action
        val actual = sut(fakePokemonBasicInfoBulbasaur.id)

        // check assertions
        assertEquals(fakePokemonBasicInfoBulbasaur, actual)
    }

    @Test(expected = NoSuchElementException::class)
    fun should_throw_exception_if_pokemon_basic_info_is_not_found() = runBlocking {
        // define test data
        fakePokemonTypeDetailsRepository.fakePokemonBasicInfoList.add(fakePokemonBasicInfoBulbasaur)

        // trigger action
        sut(fakePokemonBasicInfoIvysaur.id)
        testDispatcher.scheduler.advanceUntilIdle()
    }
}
