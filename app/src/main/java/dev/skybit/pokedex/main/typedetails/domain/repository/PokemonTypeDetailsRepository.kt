package dev.skybit.pokedex.main.typedetails.domain.repository

import dev.skybit.pokedex.main.typedetails.domain.model.PokemonBasicInfo

interface PokemonTypeDetailsRepository {
    suspend fun getPokemonTypeDetails(pokemonTypeId: Int): List<PokemonBasicInfo>

    suspend fun fetchPokemonTypeDetails(pokemonTypeId: Int)
}
