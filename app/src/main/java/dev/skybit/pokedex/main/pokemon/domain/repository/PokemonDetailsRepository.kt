package dev.skybit.pokedex.main.pokemon.domain.repository

import dev.skybit.pokedex.main.pokemon.domain.model.PokemonDetails
import kotlinx.coroutines.flow.Flow

interface PokemonDetailsRepository {
    suspend fun getPokemonDetails(pokemonId: Int): PokemonDetails

    suspend fun populatePokemonDetails(pokemonId: Int)

    fun getPokemonDetailsById(pokemonId: Int): Flow<PokemonDetails?>
}
