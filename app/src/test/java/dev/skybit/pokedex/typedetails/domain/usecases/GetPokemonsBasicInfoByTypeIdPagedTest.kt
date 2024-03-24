package dev.skybit.pokedex.typedetails.domain.usecases

import androidx.paging.testing.asSnapshot
import dev.skybit.pokedex.main.typedetails.domain.model.fakePokemonBasicInfoBulbasaur
import dev.skybit.pokedex.main.typedetails.domain.model.fakePokemonBasicInfoIvysaur
import dev.skybit.pokedex.main.typedetails.domain.repository.FakePokemonTypeDetailsRepository
import dev.skybit.pokedex.main.typedetails.domain.usecases.GetPokemonsBasicInfoByTypeIdPaged
import dev.skybit.pokedex.main.typedetails.domain.usecases.GetPokemonsBasicInfoByTypeIdPagedImpl
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetPokemonsBasicInfoByTypeIdPagedTest {
    private lateinit var pokemonTypeDetailsRepository: FakePokemonTypeDetailsRepository
    private lateinit var sut: GetPokemonsBasicInfoByTypeIdPaged

    @Before
    fun init() {
        pokemonTypeDetailsRepository = FakePokemonTypeDetailsRepository()
        sut = GetPokemonsBasicInfoByTypeIdPagedImpl(pokemonTypeDetailsRepository)
    }

    @Test
    fun should_successfully_get_pokemon_type_details_paged() = runBlocking {
        // define test data
        val typeId = 1
        val expected = listOf(fakePokemonBasicInfoBulbasaur, fakePokemonBasicInfoIvysaur)
        pokemonTypeDetailsRepository.fakePokemonBasicInfoList.addAll(expected)

        // trigger action
        val actual = sut(typeId).asSnapshot()

        // check assertions
        assertEquals(expected, actual)
    }
}
