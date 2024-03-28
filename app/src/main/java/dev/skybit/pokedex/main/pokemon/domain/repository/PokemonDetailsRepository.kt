package dev.skybit.pokedex.main.pokemon.domain.repository

import dev.skybit.pokedex.main.pokemon.domain.model.PokemonDetails

interface PokemonDetailsRepository {
    suspend fun getPokemonDetails(pokemonId: Int): PokemonDetails
}
