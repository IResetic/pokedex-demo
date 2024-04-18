package dev.skybit.pokedex.main.pokemon.domain.repository

import dev.skybit.pokedex.main.pokemon.domain.model.PokemonDetails
import dev.skybit.pokedex.main.pokemon.domain.model.fakePokemonDetailsOne
import dev.skybit.pokedex.main.pokemon.domain.model.fakePokemonDetailsTwo

class FakePokemonDetailsRepository : PokemonDetailsRepository {
    var shouldThrowException: Boolean = false
    var exceptionMessage: String? = "An error occurred"
    var fakePokemonDetailsMap = mutableMapOf(
        fakePokemonDetailsOne.id to fakePokemonDetailsOne,
        fakePokemonDetailsTwo.id to fakePokemonDetailsTwo
    )

    override suspend fun populatePokemonDetails(pokemonId: Int) {
        if (shouldThrowException) {
            throw Exception(exceptionMessage)
        }
    }

    override suspend fun getPokemonDetails(pokemonId: Int): PokemonDetails? {
        fakePokemonDetailsMap[pokemonId]?.let { pokemonDetails ->
            return pokemonDetails
        } ?: return null
    }
}
