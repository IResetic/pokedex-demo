package dev.skybit.pokedex.main.pokemon.data.local.dao

import dev.skybit.pokedex.main.pokemon.data.local.model.PokemonDetailsEntity

class FakePokemonDetailsDao : PokemonDetailsDao {
    val pokemonDetailsStorage = mutableMapOf<Int, PokemonDetailsEntity>()

    override suspend fun insertOrUpdatePokemonDetails(pokemonDetails: PokemonDetailsEntity) {
        pokemonDetailsStorage[pokemonDetails.id] = pokemonDetails
    }

    override suspend fun getPokemonDetailsById(pokemonId: Int): PokemonDetailsEntity {
        return pokemonDetailsStorage[pokemonId] ?: throw Exception("Pokemon with id $pokemonId not found")
    }
}
