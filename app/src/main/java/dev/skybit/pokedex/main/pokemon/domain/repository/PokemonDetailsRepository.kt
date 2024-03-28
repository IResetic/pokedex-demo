package dev.skybit.pokedex.main.pokemon.domain.repository

interface PokemonDetailsRepository {
    suspend fun getPokemonDetails(pokemonId: Int)
}
