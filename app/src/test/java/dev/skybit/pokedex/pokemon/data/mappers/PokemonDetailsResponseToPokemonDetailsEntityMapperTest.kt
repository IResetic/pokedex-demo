package dev.skybit.pokedex.pokemon.data.mappers

import dev.skybit.pokedex.main.pokemon.data.local.model.PokemonDetailsEntity
import dev.skybit.pokedex.main.pokemon.data.mappers.PokemonDetailsResponseToPokemonDetailsEntityMapper
import dev.skybit.pokedex.main.pokemon.data.remote.model.fakePokemonDetailsResponseOne
import dev.skybit.pokedex.main.pokemon.domain.model.PokemonBaseStats
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class PokemonDetailsResponseToPokemonDetailsEntityMapperTest {
    private lateinit var sut: PokemonDetailsResponseToPokemonDetailsEntityMapper

    @Before
    fun init() {
        sut = PokemonDetailsResponseToPokemonDetailsEntityMapper()
    }

    @Test
    fun should_map_pokemon_details_response_to_pokemon_details_entity() {
        // define test data
        val fakePokemonDetailsResponse = fakePokemonDetailsResponseOne

        // trigger action
        val actual = sut(fakePokemonDetailsResponse)

        // check assertions
        val expected = PokemonDetailsEntity(
            id = fakePokemonDetailsResponse.id,
            name = fakePokemonDetailsResponse.name!!,
            types = fakePokemonDetailsResponse.types?.map { it.type.name }!!,
            heightCm = 70,
            weightKg = 6.9,
            pokemonBasicStats = PokemonBaseStats(
                hp = 45,
                attack = 0,
                defense = 0,
                specialAttack = 0,
                specialDefense = 0,
                speed = 0
            )
        )
        assertEquals(expected, actual)
    }
}
