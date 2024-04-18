package dev.skybit.pokedex.main.pokemon.data.datasources

import dev.skybit.pokedex.main.pokemon.data.local.model.PokemonDetailsEntity

class FakePokemonDetailsLocalDataSource : PokemonDetailsLocalDataSource {
    var fakePokemonDetailsEntityMap = mutableMapOf<Int, PokemonDetailsEntity>()

    override suspend fun insertOrUpdatePokemonDetails(pokemonDetails: PokemonDetailsEntity) {
        fakePokemonDetailsEntityMap[pokemonDetails.id] = pokemonDetails
    }

    override suspend fun getPokemonDetailsById(pokemonId: Int): PokemonDetailsEntity {
        return fakePokemonDetailsEntityMap[pokemonId] ?: throw Exception("Pokemon not found")
    }
}
