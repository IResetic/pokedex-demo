package dev.skybit.pokedex.main.pokemon.data.datasources

import dev.skybit.pokedex.main.pokemon.data.local.model.PokemonDetailsEntity

interface PokemonDetailsLocalDataSource {
    suspend fun insertOrUpdatePokemonDetails(pokemonDetails: PokemonDetailsEntity)

    suspend fun getPokemonDetailsById(pokemonId: Int): PokemonDetailsEntity
}
