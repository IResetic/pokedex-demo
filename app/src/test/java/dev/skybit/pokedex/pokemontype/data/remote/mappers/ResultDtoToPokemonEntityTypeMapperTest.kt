package dev.skybit.pokedex.pokemontype.data.remote.mappers

import dev.skybit.pokedex.main.FIRE_POKEMON_TYPE_ID
import dev.skybit.pokedex.main.core.data.remote.model.fakeResultDtoFire
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_FIRE
import dev.skybit.pokedex.main.pokemontypes.data.local.model.PokemonTypeEntity
import dev.skybit.pokedex.main.pokemontypes.data.remote.mappers.ResultDtoToPokemonEntityTypeMapper
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ResultDtoToPokemonEntityTypeMapperTest {
    private lateinit var sut: ResultDtoToPokemonEntityTypeMapper

    @Before
    fun int() {
        sut = ResultDtoToPokemonEntityTypeMapper()
    }

    @Test
    fun successfully_maps_pokemon_type_dto_to_pokemon_type() {
        // define test data
        val testResultDto = fakeResultDtoFire

        // trigger action
        val actual = sut(testResultDto)

        // check assertions
        val expected = PokemonTypeEntity(
            id = FIRE_POKEMON_TYPE_ID,
            name = POKEMON_TYPE_FIRE
        )
        assertEquals(expected, actual)
    }
}
